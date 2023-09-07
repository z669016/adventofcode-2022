package com.putoet.day9;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ShortRopeTest {
    private static final List<Instruction> instructions = ResourceLines.stream("/day9.txt")
            .map(Instruction::of)
            .toList();

    @Test
    void move() {
        final var ropes = ShortRope.start().move(instructions);
        assertEquals(new ShortRope(Point.of(2, 2), Point.of(1, 2)), ropes.get(ropes.size() - 1));
        assertEquals(13, ropes.stream().map(Rope::tail).collect(Collectors.toSet()).size());
    }

    @Test
    void moves() {
        final var instructions = List.of(
                new Instruction(Direction.RIGHT, 1),
                new Instruction(Direction.UP, 1),
                new Instruction(Direction.LEFT, 2),
                new Instruction(Direction.DOWN, 2),
                new Instruction(Direction.RIGHT, 2),
                new Instruction(Direction.UP, 1),
                new Instruction(Direction.LEFT, 1)
        );
        var ropes = ShortRope.start().move(instructions);
        assertEquals(11, ropes.size());
        assertEquals(ShortRope.start(), ropes.get(0));
        assertEquals(ShortRope.start(), ropes.get(10));

        var rope = new ShortRope(Point.of(1, 1), Point.ORIGIN);
        ropes = rope.move(new Instruction(Direction.UP, 1));
        assertEquals(new ShortRope(Point.of(1, 2), Point.of(1,1)), ropes.get(0));
        ropes = rope.move(new Instruction(Direction.RIGHT, 1));
        assertEquals(new ShortRope(Point.of(2, 1), Point.of(1,1)), ropes.get(0));

        rope = new ShortRope(Point.of(-1, -1), Point.ORIGIN);
        ropes = rope.move(new Instruction(Direction.DOWN, 1));
        assertEquals(new ShortRope(Point.of(-1, -2), Point.of(-1,-1)), ropes.get(0));
        ropes = rope.move(new Instruction(Direction.LEFT, 1));
        assertEquals(new ShortRope(Point.of(-2, -1), Point.of(-1,-1)), ropes.get(0));

        rope = new ShortRope(Point.of(1, -1), Point.ORIGIN);
        ropes = rope.move(new Instruction(Direction.DOWN, 1));
        assertEquals(new ShortRope(Point.of(1, -2), Point.of(1,-1)), ropes.get(0));
        ropes = rope.move(new Instruction(Direction.RIGHT, 1));
        assertEquals(new ShortRope(Point.of(2, -1), Point.of(1,-1)), ropes.get(0));

        rope = new ShortRope(Point.of(-1, 1), Point.ORIGIN);
        ropes = rope.move(new Instruction(Direction.UP, 1));
        assertEquals(new ShortRope(Point.of(-1, 2), Point.of(-1,1)), ropes.get(0));
        ropes = rope.move(new Instruction(Direction.LEFT, 1));
        assertEquals(new ShortRope(Point.of(-2, 1), Point.of(-1,1)), ropes.get(0));
    }
}