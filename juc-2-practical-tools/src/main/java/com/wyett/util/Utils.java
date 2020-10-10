package com.wyett.util;

import java.util.Random;

/**
 * @author : wyettLei
 * @date : Created in 2020/10/10 16:04
 * @description: TODO
 */

public class Utils {

    public static int[] buildRandomIntArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }

    public static int[] buildRandomIntArray() {
        int size = new Random().nextInt(100);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextInt(100);
        }
        return array;
    }
}
