package com.putoet.day;

import com.putoet.day24.Valley;
import com.putoet.grid.Point;
import org.apache.commons.lang3.time.StopWatch;
import org.javatuples.Triplet;

public abstract class Day {
    protected final String[] args;

    protected Day() {
        this.args = null;
    }

    protected Day(String[] args) {
        this.args = args;
    }

    public void challenge() {
        System.out.println("Running solution for " + name());
        final StopWatch part1Timer = StopWatch.createStarted();
        part1();
        part1Timer.stop();
        System.out.println("Part one ran for " + part1Timer.getTime() + " ms");

        final StopWatch part2Timer = StopWatch.createStarted();
        part2();
        part2Timer.stop();
        System.out.println("Part two ran for " + part2Timer.getTime() + " ms");
        System.out.println("Running the entire challenge took " + (part1Timer.getTime() + part2Timer.getTime()) + " ms");
    }

    private String name() {
        final String[] name = this.getClass().getName().split("\\.");
        return name[name.length - 1];
    }

    public Triplet<Valley, Point, Integer> part1() {
        System.out.println("Not yet implemented");
        return null;
    }

    public void part2() {
        System.out.println("Not yet implemented");
    }
}
