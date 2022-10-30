package io.github.liuruinian.loadbalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WeightRoundRobinLoadBalancer<T, C> extends AbstractLoadBalancer<T, C> {

    // ~ Fields --------------------------------------------------------------------

    private final Map<T, Integer>   weightMap = new HashMap<>();

    private final Lock              lock = new ReentrantLock();

    private int                     position = 0;

    // ~ Constructors --------------------------------------------------------------

    public WeightRoundRobinLoadBalancer() {
    }

    public WeightRoundRobinLoadBalancer(List<TargetWrapper<T>> targetList) {
        super(targetList);
    }

    // ~ Methods -------------------------------------------------------------------

    @Override
    protected T choose0(List<TargetWrapper<T>> activeTargetList, C chooseReferenceObject) {
        List<TargetWrapper<T>> wrappers = new ArrayList<>();

        for (TargetWrapper<T> wrapper : activeTargetList) {
            Integer weight = weightMap.getOrDefault(wrapper.getTarget(), MIN_WEIGHT);
            for (int i = 0; i < weight; i++) {
                wrappers.add(wrapper);
            }
        }

        lock.lock();
        try {
            if (position > wrappers.size()) {
                position = 0;
            }

            TargetWrapper<T> targetWrapper = wrappers.get(position);
            position++;

            return targetWrapper == null ? null : targetWrapper.getTarget();
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void afterAddTargetWrapper(TargetWrapper<T> targetWrapper) {
        weightMap.put(targetWrapper.getTarget(), MIN_WEIGHT);
    }

    @Override
    protected void afterRemoveTargetWrapper(TargetWrapper<T> wrapper) {
        weightMap.remove(wrapper.getTarget());
    }

    @Override
    public void setWeight(T target, int weight) {
        if (target == null) {
            return;
        }

        weightMap.put(target, Math.max(weight, MIN_WEIGHT));
    }
}
