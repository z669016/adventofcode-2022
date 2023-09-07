package com.putoet.day3;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {
    private static final List<Rucksack> rucksacks = ResourceLines.list("/day3.txt", Rucksack::of);


    @Test
    void part1() {
        assertEquals(157, Day3.sharedPrioritySum(rucksacks));
    }

    @Test
    void part2() {
        assertEquals(70, Day3.batchPrioritySum(rucksacks));
    }
}