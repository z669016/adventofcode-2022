package com.putoet.day9;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractRope implements Rope {
    @Override
    public List<Rope> move(@NotNull List<Instruction> instructions) {
        final var ropes = new ArrayList<Rope>();
        ropes.add(this);

        Rope rope = this;
        for (var instruction : instructions) {
            final var toAdd = rope.move(instruction);
            ropes.addAll(toAdd);
            rope = ropes.get(ropes.size() - 1);
        }

        return ropes;
    }

    @Override
    public List<Rope> move(@NotNull Instruction instruction) {
        final var ropes = new ArrayList<Rope>();
        Rope rope = this;
        for (var i = 0; i < instruction.distance(); i++) {
            rope = rope.move(instruction.direction());
            ropes.add(rope);
        }

        return ropes;
    }

    @Override
    public abstract Rope move(@NotNull Direction direction);

    @Override
    public abstract Point head();

    @Override
    public abstract Point tail();
}
