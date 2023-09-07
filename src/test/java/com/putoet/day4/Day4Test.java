package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {
    private static final List<RangePair> rangePairs = ResourceLines.list("/day4.txt", RangePair::of);


    @Test
    void containingPairs() {
        assertEquals(2, Day4.containingPairs(rangePairs));
    }

    @Test
    void overlappingPairs() {
        assertEquals(4, Day4.overlappingPairs(rangePairs));
    }
}