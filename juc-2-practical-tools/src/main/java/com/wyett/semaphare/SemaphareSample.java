package com.wyett.semaphare;

import java.util.concurrent.Semaphore;

/**
 * @author : wyettLei
 * @date : Created in 2020/9/28 15:25
 * @description: 每次有两个线程执行，并且2个线程释放后，才能执行其他线程；
 */

public class SemaphareSample {


    static class Task extends Thread {
        Semaphore semaphore;
        String tname;

        public Task(Semaphore semaphore, String tname) {
            this.semaphore = semaphore;
            this.setName(tname);
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " acquire at " + System.currentTimeMillis());
                sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " release at " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for(int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore, "thread_" + i)).start();
        }
    }
}
