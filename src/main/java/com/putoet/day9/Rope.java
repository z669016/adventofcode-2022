package com.putoet.day9;

import com.putoet.grid.Point;

import java.util.List;

public interface Rope {
    List<Rope> move(List<Instruction> instructions);

    List<Rope> move(Instruction instruction);

    Rope move(Direction direction);

    Point head();

    Point tail();
}
