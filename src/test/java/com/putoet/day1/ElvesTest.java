package com.putoet.day1;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ElvesTest {
    private static final List<Elf> elves = Elves.from(ResourceLines.list("/day1.txt"));

    @Test
    void from() {
        assertEquals(5, elves.size());
    }

    @Test
    void mostCalories() {
        final Optional<Elf> most = Elves.mostCalories(elves);
        assertTrue(most.isPresent());
        assertEquals(24000, most.get().calories());
    }

    @Test
    void topThreeMostCalories() {
        final List<Elf> topThree = Elves.topThreeMostCalories(elves);
        assertEquals(45000, Elves.calories(topThree));
    }
}