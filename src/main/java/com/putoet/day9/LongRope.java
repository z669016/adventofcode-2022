package com.putoet.day9;

import com.putoet.grid.Point;

import java.util.ArrayList;
import java.util.List;

public class LongRope extends AbstractRope implements Rope {
    private final List<Point> points;

    public LongRope(List<Point> points) {
        this.points = points;
    }

    @Override
    public Rope move(Direction direction) {
        final List<Point> newPoints = new ArrayList<>(points);

        final Rope rope = new ShortRope(points.get(0), points.get(1)).move(direction);
        newPoints.set(0, rope.head());
        newPoints.set(1, rope.tail());

        int idx = 2;
        while (idx < newPoints.size() && ShortRope.isNotValid(newPoints.get(idx - 1), newPoints.get(idx))) {
            final Point first = newPoints.get(idx - 1);
            Point second = newPoints.get(idx); // point to move, when applicable

            if (Math.abs(first.x() - second.x()) > 1 && Math.abs(first.y() - second.y()) > 1) {
                // move along x and y
                second = Point.of(first.x() > second.x() ? first.x() - 1 : first.x() + 1, first.y() > second.y() ? first.y() - 1 : first.y() + 1);
            } else if (Math.abs(first.x() - second.x()) > 1) { // move second along the x axe
                second = Point.of(first.x() > second.x() ? first.x() - 1 : first.x() + 1, first.y());
            } else {
                second = Point.of(first.x(), first.y() > second.y() ? first.y() - 1 : first.y() + 1);
            }

            newPoints.set(idx, second);
            idx++;
        }

        return new LongRope(newPoints);
    }

    @Override
    public Point head() {
        return points.get(0);
    }

    @Override
    public Point tail() {
        return points.get(points.size() - 1);
    }

    public static LongRope start(int size) {
        final List<Point> points = new ArrayList<>();
        while (size-- > 0)
            points.add(Point.ORIGIN);

        return new LongRope(points);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LongRope[head=");
        sb.append(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            sb.append(", ");
            sb.append(i);
            sb.append("=");
            sb.append(points.get(i));
        }
        sb.append("]");

        return sb.toString();
    }
}
