package com.putoet.day14;

import com.putoet.grid.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public record Line(List<Point> edges) {
    public Line {
        assert edges != null;
        assert !edges.isEmpty();
    }

    public Iterator<Line> parts() {
        return new Iterator<>() {
            int offset = 0;

            @Override
            public boolean hasNext() {
                return offset < edges.size() - 1;
            }

            @Override
            public Line next() {
                if (!hasNext())
                    throw new IllegalStateException("No more elements");

                offset++;
                return new Line(List.of(edges.get(offset - 1), edges.get(offset)));
            }
        };
    }

    public int minX() {
        return edges.stream()
                .mapToInt(Point::x)
                .min()
                .orElseThrow();
    }

    public int maxX() {
        return edges.stream()
                .mapToInt(Point::x)
                .max()
                .orElseThrow();
    }
    public int minY() {
        return edges.stream()
                .mapToInt(Point::y)
                .min()
                .orElseThrow();
    }

    public int maxY() {
        return edges.stream()
                .mapToInt(Point::y)
                .max()
                .orElseThrow();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(edges.get(0).x()).append(",").append(edges.get(0).y());

        for (var i = 1; i < edges.size(); i++) {
            sb.append(" -> ").append(edges.get(i).x()).append(",").append(edges.get(i).y());
        }

        return sb.toString();
    }

    public static Line from(String input) {
        assert input != null;

        final List<Point> edges = new ArrayList<>();
        final String[] split = input.split(" -> ");
        for (var p : split) {
            edges.add(asPoint(p));
        }

        return new Line(edges);
    }

    private static Point asPoint(String point) {
        assert point != null;
        point = point.trim();
        final String[] split = point.split(",");

        assert split.length == 2;

        return Point.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
}
