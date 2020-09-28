package com.wyett.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author : wyettLei
 * @date : Created in 2020/9/28 17:29
 * @description: 当一个线程运行到exchange()方法时会阻塞，另一个线程运行到exchange()时，二者
 * 交换数据，然后执行后面的程序
 */

public class ExchangerSample {
    public static void main(String[] args) {
        final Exchanger<Integer> exchanger = new Exchanger<>();

        for(int i = 0; i < 10; i++) {
            final int num = i;
            new Thread(){
                @Override
                public void run() {
                    System.out.println("thread " + Thread.currentThread().getName() + "的值是" + num);

                    try {
                        Integer exchange = exchanger.exchange(num);
                        sleep(1000);
                        System.out.println("thread " + Thread.currentThread().getName() + "原值是" + num + "现值是" + exchange);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
