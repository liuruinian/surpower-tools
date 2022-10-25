package io.github.liuruinian.loadbalancer;

import java.util.List;
import java.util.Random;

public class RandomLoadBalancer<T, C> extends AbstractLoadBalancer<T, C> {

    // ~ Constructors ---------------------------------------------------------

    public RandomLoadBalancer() {
        super();
    }

    public RandomLoadBalancer(List<TargetWrapper<T>> targetList) {
        super(targetList);
    }

    // ~ Methods --------------------------------------------------------------

    @Override
    protected T choose0(List<TargetWrapper<T>> activeTargetList, C chooseReferenceObject) {
        TargetWrapper<T> targetWrapper = activeTargetList.get(new Random().nextInt(activeTargetList.size()));

        return targetWrapper == null ? null : targetWrapper.getTarget();
    }
}
