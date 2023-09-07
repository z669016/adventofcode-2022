package com.putoet.day12;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class Finder {
    public record Node(@NotNull Point at, Node prev) {
        public List<Point> list() {
            final var list = new ArrayList<Point>();
            Node current = this;
            while (current != null) {
                list.add(current.at);
                current = current.prev;
            }

            return list;
        }
    }

    public static List<Point> solve(@NotNull HeightMap heights) {
        return solve(heights, heights.start());
    }

    public static List<Point> solve(@NotNull HeightMap heights, @NotNull Point start) {
        final var history = new HashSet<Point>();
        final var queue = new LinkedList<Node>();
        final var end = heights.end();

        queue.offer(new Node(start, null));
        while (!queue.isEmpty()) {
            final var current = queue.poll();

            if (current.at().equals(end))
                return current.list();

            heights.next(current.at).stream()
                    .filter(history::add)
                    .forEach(p -> queue.offer(new Node(p, current)));
        }

        return List.of();
    }
}
