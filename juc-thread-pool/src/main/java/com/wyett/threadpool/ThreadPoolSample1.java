package com.wyett.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : wyettLei
 * @date : Created in 2020/10/14 15:52
 * @description: TODO
 */

public class ThreadPoolSample1 {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,10, 1000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10)
        );

        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程" + Thread.currentThread().getName() + "执行时间" + System.currentTimeMillis());
                }
            }, i);
        }

        threadPoolExecutor.shutdown();
        threadPoolExecutor.shutdownNow();
    }
}
