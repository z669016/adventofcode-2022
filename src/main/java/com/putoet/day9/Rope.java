package com.putoet.day9;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;

interface Rope {
    List<Rope> move(@NotNull List<Instruction> instructions);

    List<Rope> move(@NotNull Instruction instruction);

    Rope move(@NotNull Direction direction);

    Point head();

    Point tail();
}
