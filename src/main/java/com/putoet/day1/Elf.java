package com.putoet.day1;

import org.jetbrains.annotations.NotNull;

import java.util.List;

record Elf(int id, @NotNull List<Integer> foodCalories) {
    public int calories() {
        return foodCalories.stream().mapToInt(i -> i).sum();
    }
}
