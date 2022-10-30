package io.github.liuruinian.loadbalancer;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * round robin load balancer
 *
 * @param <T>
 *         target class
 * @param <C>
 *         choose reference object
 * @author liuruinian
 */
public class RoundRobinLoadBalancer<T, C> extends AbstractLoadBalancer<T, C> {

    // ~ Fields --------------------------------------------------------------

    private final Lock      lock = new ReentrantLock();

    private int             position = 0;


    // ~ Constructors --------------------------------------------------------

    /**
     * instantiation Load Balancer with CopyOnWriteArrayList
     */
    public RoundRobinLoadBalancer() {
        super();
    }

    /**
     * instantiation Load Balancer with appoint list
     *
     * @param targetList
     *         target object list
     */
    public RoundRobinLoadBalancer(List<TargetWrapper<T>> targetList) {
        super(targetList);
    }

    @Override
    protected T choose0(List<TargetWrapper<T>> activeTargetList, C chooseReferenceObject) {
        lock.lock();
        try {
            if (position >= activeTargetList.size()) {
                position = 0;
            }

            TargetWrapper<T> wrapper = activeTargetList.get(position);
            T target = wrapper.getTarget();
            position++;

            return target;
        } finally {
            lock.unlock();
        }
    }
}
