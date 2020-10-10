package com.wyett.forkjoin.accumulation;

import com.wyett.util.Utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author : wyettLei
 * @date : Created in 2020/10/10 16:00
 * @description: TODO
 */

public class SumMain {
    private static final int NUM_CPU = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long longSum = 0;
        System.out.println("空闲线程" + NUM_CPU);

        // 顺序加
        int[] array = Utils.buildRandomIntArray(20000000);
        long start = System.currentTimeMillis();
        System.out.println(start);
        System.out.println("顺序累加-----------" + start);
        longSum = seqSum(array);
        System.out.println("累加结果:" + longSum);
        long end = System.currentTimeMillis();
        System.out.println("顺序累加-----------" + end);
        //System.out.println("顺序累加耗时:" + (System.currentTimeMillis() - start));
        System.out.println("顺序累加耗时:" + (end - start));

        // forkjoin
        start = System.currentTimeMillis();
        System.out.println("forkjoin-----------" + start);
        LongSum longSum1 = new LongSum(array, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool(NUM_CPU);
        ForkJoinTask<Long> fjt = forkJoinPool.submit(longSum1);
        System.out.println("forkjoin结果:" + fjt.get());
        end = System.currentTimeMillis();
        System.out.println("forkjoin-----------" + end);
        //System.out.println("forkjoin耗时:" + (System.currentTimeMillis() - start));
        System.out.println("forkjoin耗时:" + (end - start));
    }

    private static Long seqSum(int[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }
}
