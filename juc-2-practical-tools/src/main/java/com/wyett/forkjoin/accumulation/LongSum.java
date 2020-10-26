package com.wyett.forkjoin.accumulation;

import java.util.concurrent.RecursiveTask;

/**
 * @author : wyettLei
 * @date : Created in 2020/10/10 15:35
 * @description: TODO
 */

public class LongSum extends RecursiveTask<Long> {


    private final int BOUND_OF_HIGH_LOW = 1000;

    private int[] arr;
    private int st;
    private int en;

    public LongSum(int[] arr, int st, int en) {
        this.arr = arr;
        this.st = st;
        this.en = en;
    }

    @Override
    public Long compute() {
        Long sum = 0L;
        if(st - en <= BOUND_OF_HIGH_LOW) {
            for(int i = st; i < en; i++) {
                 sum += arr[i];
            }
            return sum;
        } else {
            int mid = (st + en)/2;
            LongSum sthalf = new LongSum(arr, 0, mid);
            LongSum enhalf = new LongSum(arr, mid + 1, en);
            sthalf.fork();
            enhalf.fork();
            Long sthalfVal = sthalf.join();
            Long enhalfVal = enhalf.join();
            return sthalfVal + enhalfVal;
        }
    }
}
