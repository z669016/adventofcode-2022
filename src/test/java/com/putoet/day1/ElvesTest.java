package com.putoet.day1;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElvesTest {
    private static final List<Elf> elves = Elves.of(ResourceLines.list("/day1.txt"));

    @Test
    void from() {
        assertEquals(5, elves.size());
    }

    @Test
    void mostCalories() {
        final var most = Elves.mostCalories(elves);
        assertTrue(most.isPresent());
        assertEquals(24000, most.get().calories());
    }

    @Test
    void topThreeMostCalories() {
        assertEquals(45000, Elves.topThreeMostCalories(elves));
    }
}