package io.github.liuruinian.loadbalancer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestLoadBalancer {

    /**
     * test random load balancer
     */
    @Test
    public void testRandomLoadBalancer() {
        List<TargetWrapper<String>> wrapperList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TargetWrapper<String> targetWrapper = TargetWrapper.of("TW" + i);
            targetWrapper.setActive(true);
            wrapperList.add(targetWrapper);
        }

        RandomLoadBalancer<String, Object> loadBalancer = new RandomLoadBalancer<>(wrapperList);

        for (int i = 0; i < 20; i++) {
            String choose = loadBalancer.choose();
            System.out.println("random load balancer choose: " + choose);
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * test round robin load balancer
     */
    @Test
    public void testRoundRobinLoadBalancer() {
        List<TargetWrapper<String>> wrapperList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TargetWrapper<String> targetWrapper = TargetWrapper.of("TW" + i);
            targetWrapper.setActive(true);
            wrapperList.add(targetWrapper);
        }

        RoundRobinLoadBalancer<String, Object> loadBalancer = new RoundRobinLoadBalancer<>(wrapperList);

        for (int i = 0; i < 20; i++) {
            String choose = loadBalancer.choose();
            System.out.println("round robin load balancer choose: " + choose);
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
