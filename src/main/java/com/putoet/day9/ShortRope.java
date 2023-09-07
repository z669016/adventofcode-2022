package com.putoet.day9;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

class ShortRope extends AbstractRope {
    private final Point head;
    private final Point tail;

    public ShortRope(@NotNull Point head, @NotNull Point tail) {
        if (isNotValid(head, tail)) {
            throw new IllegalStateException("Invalid rope head and tail: " + head + " " + tail);
        }

        this.head = head;
        this.tail = tail;
    }

    @Override
    public Rope move(@NotNull Direction direction) {
        final var newHead = move(head, direction);

        var newTail = tail;
        if (isNotValid(newHead, newTail)) {
            newTail = head;
        }

        return new ShortRope(newHead, newTail);
    }

    @Override
    public Point head() {
        return head;
    }

    @Override
    public Point tail() {
        return tail;
    }

    private static Point move(Point point, Direction direction) {
        return switch (direction) {
            case UP -> point.add(Point.NORTH);
            case DOWN -> point.add(Point.SOUTH);
            case LEFT -> point.add(Point.WEST);
            case RIGHT -> point.add(Point.EAST);
        };
    }

    public static Rope start() {
        return new ShortRope(Point.ORIGIN, Point.ORIGIN);
    }

    public static boolean isNotValid(@NotNull Point head, @NotNull Point tail) {
        return head.x() == tail.x() || head.y() == tail.y() ? head.manhattanDistance(tail) >= 2 : head.manhattanDistance(tail) >= 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortRope shortRope)) return false;
        return head.equals(shortRope.head) && tail.equals(shortRope.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }
}
