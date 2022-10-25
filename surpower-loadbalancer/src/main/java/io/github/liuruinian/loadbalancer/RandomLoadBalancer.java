package io.github.liuruinian.loadbalancer;

import java.util.List;
import java.util.Random;

/**
 * random load balancer
 *
 * @param <T>
 *         target class
 * @param <C>
 *         choose reference object
 *
 * @author liuruinian
 */
public class RandomLoadBalancer<T, C> extends AbstractLoadBalancer<T, C> {

    // ~ Constructors ---------------------------------------------------------

    /**
     * instantiation Load Balancer with CopyOnWriteArrayList
     */
    public RandomLoadBalancer() {
        super();
    }

    /**
     * instantiation Load Balancer with appoint list
     *
     * @param targetList
     *         target object list
     */
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
