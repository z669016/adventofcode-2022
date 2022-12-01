package com.putoet.day1;

import java.util.List;

public record Elf(int id, List<Integer> foodCalories) {
    public int calories() {
        return foodCalories.stream().mapToInt(i -> i).sum();
    }
}
