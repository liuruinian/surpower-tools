package io.github.liuruinian.loadbalancer;

import java.util.*;

/**
 * weight random Load Balancer
 *
 * @param <T>
 *         target class
 * @param <C>
 *         choose reference object
 * @author liuruinian
 */
public class WeightRandomLoadBalancer<T, C> extends AbstractLoadBalancer<T, C> {

    // ~ Fields ----------------------------------------------------------------

    private final Map<T, Integer> weightMap = new HashMap<>();

    // ~ Constructors ----------------------------------------------------------

    /**
     * instantiation Load Balancer with CopyOnWriteArrayList
     */
    public WeightRandomLoadBalancer() {
        super();
    }

    /**
     * instantiation Load Balancer with appoint list
     *
     * @param targetList
     *         target object list
     */
    public WeightRandomLoadBalancer(List<TargetWrapper<T>> targetList) {
        super(targetList);
    }

    // ~ Methods ---------------------------------------------------------------

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

    @Override
    protected T choose0(List<TargetWrapper<T>> activeTargetList, C chooseReferenceObject) {
        List<TargetWrapper<T>> wrappers = new ArrayList<>();

        for (TargetWrapper<T> wrapper : activeTargetList) {
            Integer weight = weightMap.getOrDefault(wrapper.getTarget(), MIN_WEIGHT);
            for (int i = 0; i < weight; i++) {
                wrappers.add(wrapper);
            }
        }

        TargetWrapper<T> targetWrapper = wrappers.get(new Random().nextInt(wrappers.size()));

        return targetWrapper == null ? null : targetWrapper.getTarget();
    }
}
