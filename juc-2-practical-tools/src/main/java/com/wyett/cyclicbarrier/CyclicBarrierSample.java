package com.wyett.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : wyettLei
 * @date : Created in 2020/9/28 17:03
 * @description: TODO
 */

public class CyclicBarrierSample implements Runnable {
    private int count;
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierSample(int count, CyclicBarrier cyclicBarrier) {
        this.count = count;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            count--;
            System.out.println("thread " + count + "准备好");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(11, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有任务到达栅栏");
            }
        });

        for(int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierSample(i, cyclicBarrier)).start();
        }

        cyclicBarrier.await();
        System.out.println("所有任务执行完成");
    }
}
