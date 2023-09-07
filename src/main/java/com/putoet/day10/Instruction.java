package com.putoet.day10;

import org.jetbrains.annotations.NotNull;

interface Instruction {
    int exec(@NotNull Device device);
}
