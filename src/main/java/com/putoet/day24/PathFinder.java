package com.putoet.day24;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.javatuples.Triplet;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PathFinder {
    private static Triplet<Valley, Point, Integer> init() {
        final var valley = Valley.of(ResourceLines.list("/day24.txt"));
        return Triplet.with(valley, valley.in(), 0);
    }

    public static Triplet<Valley, List<Point>, Integer> next(Triplet<Valley,Point,Integer> state) {
        final var valley = state.getValue0().next();
        final var location = state.getValue1();

        final List<Point> next = Stream.of(Blizzard.NORTH, Blizzard.EAST, Blizzard.SOUTH, Blizzard.WEST, Point.ORIGIN)
                .map(location::add)
                .filter(valley::contains)
                .filter(valley::isOpen)
                .toList();

        return Triplet.with(valley, next, state.getValue2() + 1);
    }

    public static boolean found(Triplet<Valley,Point,Integer> state) {
        return state.getValue0().out().equals(state.getValue1());
    }

    public static Optional<Triplet<Valley,Point,Integer>> solve() {
        return solve(init(), PathFinder::found);
    }

    public static Optional<Triplet<Valley,Point,Integer>> solve(Triplet<Valley,Point,Integer> init,
                                                                Predicate<Triplet<Valley,Point,Integer>> found) {
        final Set<String> history = new HashSet<>();
        final Deque<Triplet<Valley,Point,Integer>> queue = new LinkedList<>();
        queue.offer(init);

        while (!queue.isEmpty()) {
            final var state = queue.poll();
            if (found.test(state))
                return Optional.of(state);

            final var next = next(state);
            next.getValue1().stream()
                    .filter(p -> history.add(next.getValue0().toString(p)))
                    .forEach(p -> queue.offer(Triplet.with(next.getValue0(), p, next.getValue2())));
        }

        return Optional.empty();
    }
}
