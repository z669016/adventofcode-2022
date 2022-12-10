package com.putoet.day10;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day10 extends Day {
    private final List<Instruction> program;

    public Day10(String[] args) {
        super(args);
        program = Compiler.compile(ResourceLines.list("/day10.txt"));
    }

    public static void main(String[] args) {
        final var day = new Day10(args);
        day.challenge();
    }

    @Override
    public void part1() {
        final Device device = new Device();
        final List<SignalStrength> signalStrengths = device.process(program, cycle -> cycle >= 220);
        System.out.println("The sum of the first six signal strengths is " +
                           signalStrengths.stream().mapToLong(SignalStrength::strength).sum());
    }

    @Override
    public void part2() {
        final Device device = new Device();
        device.process(program, cycle -> cycle >= 240);
        System.out.println(device.crt());
    }
}
