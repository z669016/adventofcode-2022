package com.putoet.day24;

import com.putoet.grid.Point;
import com.putoet.utils.Timer;
import org.javatuples.Triplet;

public class Day24 {
    public static void main(String[] args) {
        final var endPoint = Timer.run(Day24::part1);
        Timer.run(() -> part2(endPoint));
    }

    static Triplet<Valley, Point, Integer> part1() {
        final var endPoint = PathFinder.solve().orElseThrow();
        System.out.println("The fewest number of minutes required to avoid the blizzards and reach the goal is "
                           + endPoint.getValue2());

        return endPoint;
    }

    static void part2(Triplet<Valley, Point, Integer> endPoint) {
        System.out.println("Made it to the end in " + endPoint.getValue2() + " minutes.");
        var startingPoint = PathFinder.solve(endPoint, state -> state.getValue0().in().equals(state.getValue1())).orElseThrow();
        System.out.println("Made it back to the beginning in " + startingPoint.getValue2() + " minutes.");
        endPoint = PathFinder.solve(startingPoint, state -> state.getValue0().out().equals(state.getValue1())).orElseThrow();
        System.out.println("Made it back to the end again in " + endPoint.getValue2() + " minutes.");
    }
}
