package com.putoet.day5;

import org.jetbrains.annotations.NotNull;

interface Crane {
    Crates take(@NotNull Crates stack, int count);
}
