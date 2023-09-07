package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day16 {
    public static void main(String[] args) {
        final var finder = new Valves(ResourceLines.stream("/day16.txt").map(Valve::from).toList());

        Timer.run(() -> System.out.println("The most pressure you can release is " + finder.maximumPressure()));
        Timer.run(() ->
                System.out.println("The most pressure you can release collaborating with the elephant is " +
                                   finder.maximumPressureWithHelp())
        );
    }
}
