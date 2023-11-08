package com.putoet.day24;

import com.putoet.utils.Timer;

public class Day24 {
    public static void main(String[] args) {
        final var endPoint = Timer.run(Day24::part1);
        Timer.run(() -> part2(endPoint));
    }

    static ValleyRoute part1() {
        final var route = PathFinder.solve().orElseThrow();
        System.out.println("The fewest number of minutes required to avoid the blizzards and reach the goal is "
                           + route.steps());

        return route;
    }

    static void part2(ValleyRoute route) {
        System.out.println("Made it to the end in " + route.steps() + " minutes.");
        final var startingPoint = PathFinder.solve(route, state -> state.valley().in().equals(state.point())).orElseThrow();
        System.out.println("Made it back to the beginning in " + startingPoint.steps() + " minutes.");
        route = PathFinder.solve(startingPoint, state -> state.valley().out().equals(state.point())).orElseThrow();
        System.out.println("Made it back to the end again in " + route.steps() + " minutes.");
    }
}
