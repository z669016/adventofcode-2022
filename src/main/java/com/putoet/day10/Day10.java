package com.putoet.day10;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day10 extends Day {
    public static void main(String[] args) {
        final var program = Compiler.compile(ResourceLines.list("/day10.txt"));

        Timer.run(() -> part1(program));
        Timer.run(() -> part2(program));
    }

    static void part1(List<Instruction> program) {
        final var device = new Device();
        final var signalStrengths = device.process(program, cycle -> cycle >= 220);
        System.out.println("The sum of the first six signal strengths is " +
                           signalStrengths.stream().mapToLong(SignalStrength::strength).sum());
    }

    static void part2(List<Instruction> program) {
        final var device = new Device();
        device.process(program, cycle -> cycle >= 240);
        System.out.println(device.crt());
    }
}
