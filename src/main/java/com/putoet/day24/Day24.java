package com.putoet.day24;

import com.putoet.day.Day;
import com.putoet.grid.Point;
import org.javatuples.Triplet;

public class Day24 extends Day {
    private Triplet<Valley, Point,Integer> endPoint;

    public Day24(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        final var day = new Day24(args);
        day.challenge();
    }

    @Override
    public void part1() {
        endPoint = PathFinder.solve().orElseThrow();
        System.out.println("The fewest number of minutes required to avoid the blizzards and reach the goal is "
                           + endPoint.getValue2());
    }

    @Override
    public void part2() {
        System.out.println("Made it to the end in " + endPoint.getValue2() + " minutes.");
        var startingPoint = PathFinder.solve(endPoint, state -> state.getValue0().in().equals(state.getValue1())).orElseThrow();
        System.out.println("Made it back to the beginning in " + startingPoint.getValue2() + " minutes.");
        endPoint = PathFinder.solve(startingPoint, state -> state.getValue0().out().equals(state.getValue1())).orElseThrow();
        System.out.println("Made it back to the end again in " + endPoint.getValue2() + " minutes.");
    }
}
