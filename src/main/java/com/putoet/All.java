package com.putoet;

import com.putoet.utils.Timer;

import java.util.stream.IntStream;

public class All {
    public static void main(String[] args) {
        System.out.println("Java version: " + System.getProperty("java.version"));
        Timer.run(() -> IntStream.range(1, 25).forEach(day -> Timer.run(() -> run(day))));
    }

    public static void run(int day) {
        try {
            final Class<?> clazz = Class.forName("com.putoet.day" + day + ".Day" + day);
            System.out.println("Run " + clazz.getName());
            clazz.getMethod("main", String[].class).invoke(null, new Object[] {new String[] {"Day" + day}});
        } catch (Exception exc) {
            System.out.println("Unable to run day " + day + ": " + exc.getMessage());
        }
    }
}
