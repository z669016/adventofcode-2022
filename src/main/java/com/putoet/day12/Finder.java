package com.putoet.day12;

import com.putoet.grid.Point;

import java.util.*;

public class Finder {
    public record Node(Point at, Node prev) {
        public Node {
            assert at != null;
        }

        public List<Point> list() {
            final List<Point> list = new ArrayList<>();
            Node current = this;
            while (current != null) {
                list.add(current.at);
                current = current.prev;
            }

            return list;
        }
    }

    public static List<Point> solve(HeightMap heights) {
        return solve(heights, heights.start());
    }

    public static List<Point> solve(HeightMap heights, Point start) {
        final Set<Point> history = new HashSet<>();
        final Queue<Node> queue = new LinkedList<>();
        final Point end = heights.end();

        queue.offer(new Node(start, null));
        while (!queue.isEmpty()) {
            final Node current = queue.poll();

            if (current.at().equals(end))
                return current.list();

            heights.next(current.at).stream()
                    .filter(history::add)
                    .forEach(p -> queue.offer(new Node(p, current)));
        }

        return List.of();
    }
}
