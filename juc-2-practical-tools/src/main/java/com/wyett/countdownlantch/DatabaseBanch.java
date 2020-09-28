package com.wyett.countdownlantch;

import java.util.concurrent.CountDownLatch;

/**
 * @author : wyettLei
 * @date : Created in 2020/9/28 16:08
 * @description: TODO
 */

public class DatabaseBanch implements Runnable{

    private int id;

    public DatabaseBanch(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Thread_" + id + "压测数据库");
    }
}
