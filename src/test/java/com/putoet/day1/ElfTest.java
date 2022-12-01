package com.putoet.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {
    @Test
    void calories() {
        final List<Integer> foodCalories = List.of(1, 2, 3, 4, 5);
        assertEquals(15, new Elf(0, foodCalories).calories());
    }
}