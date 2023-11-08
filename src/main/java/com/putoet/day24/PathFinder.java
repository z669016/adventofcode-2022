package com.putoet.day24;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

class PathFinder {
    private static ValleyRoute init() {
        final var valley = Valley.of(ResourceLines.list("/day24.txt"));
        return new ValleyRoute(valley, valley.in(), 0);
    }

    public static ValleyRoutes next(ValleyRoute state) {
        final var valley = state.valley().next();
        final var location = state.point();

        final List<Point> next = Stream.of(Blizzard.NORTH, Blizzard.EAST, Blizzard.SOUTH, Blizzard.WEST, Point.ORIGIN)
                .map(location::add)
                .filter(valley::contains)
                .filter(valley::isOpen)
                .toList();

        return new ValleyRoutes(valley, next, state.steps() + 1);
    }

    public static boolean found(ValleyRoute state) {
        return state.valley().out().equals(state.point());
    }

    public static Optional<ValleyRoute> solve() {
        return solve(init(), PathFinder::found);
    }

    public static Optional<ValleyRoute> solve(ValleyRoute init, Predicate<ValleyRoute> found) {
        final Set<String> history = new HashSet<>();
        final Deque<ValleyRoute> queue = new LinkedList<>();
        queue.offer(init);

        while (!queue.isEmpty()) {
            final var state = queue.poll();
            if (found.test(state))
                return Optional.of(state);

            final var next = next(state);
            next.points().stream()
                    .filter(p -> history.add(next.valley().toString(p)))
                    .forEach(p -> queue.offer(new ValleyRoute(next.valley(), p, next.steps())));
        }

        return Optional.empty();
    }
}
