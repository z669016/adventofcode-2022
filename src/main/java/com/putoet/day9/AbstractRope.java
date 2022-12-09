package com.putoet.day9;

import com.putoet.grid.Point;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRope implements Rope {
    @Override
    public List<Rope> move(List<Instruction> instructions) {
        final List<Rope> ropes = new ArrayList<>();
        ropes.add(this);

        Rope rope = this;
        for (var instruction : instructions) {
            List<Rope> toAdd = rope.move(instruction);
            ropes.addAll(toAdd);
            rope = ropes.get(ropes.size() - 1);
        }

        return ropes;
    }

    @Override
    public List<Rope> move(Instruction instruction) {
        final List<Rope> ropes = new ArrayList<>();
        Rope rope = this;
        for (var i = 0; i < instruction.distance(); i++) {
            rope = rope.move(instruction.direction());
            ropes.add(rope);
        }

        return ropes;
    }

    @Override
    public abstract Rope move(Direction direction);

    @Override
    public abstract Point head();

    @Override
    public abstract Point tail();
}
