package com.wyett.countdownlantch;

import java.util.concurrent.CountDownLatch;

/**
 * @author : wyettLei
 * @date : Created in 2020/9/28 16:21
 * @description: TODO
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int count_thread = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(count_thread);

        //System.out.println("压测开始:" + System.currentTimeMillis());
        long startTime = System.currentTimeMillis();
        while(count_thread > 0) {
            new Thread(new DatabaseBanch(count_thread)).start();
            countDownLatch.countDown();
            count_thread--;
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("压测耗时:" + (endTime - startTime));

    }
}
