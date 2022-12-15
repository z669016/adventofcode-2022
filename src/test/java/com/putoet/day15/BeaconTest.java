package com.putoet.day15;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeaconTest {
    private static final List<String> input = ResourceLines.list("/day15.txt");

    @Test
    void from() {
        assertEquals(6, Beacon.from(input).size());
    }
}