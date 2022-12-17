package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ValveTest {
    private final List<String> input = ResourceLines.list("/day16.txt");

    @Test
    void from() {
        final Map<String,Valve> valves = new HashMap<>();
        for (var line : input) {
            assertEquals(line, Valve.from(line, valves).toString());
        }

        assertEquals(10, valves.size());
    }
}