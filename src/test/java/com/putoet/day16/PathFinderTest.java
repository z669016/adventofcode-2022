package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {
    private final List<String> input = ResourceLines.list("/day16.txt");

    private Map<String,Valve> valves;

    @BeforeEach
    void setup() {
        valves = Valves.from(input);
    }

    @Test
    void paths() {
        final var paths = PathFinder.paths(valves);
        assertEquals(10, paths.size());
        paths.forEach((key, value) -> assertEquals(key.flowRate() > 0 ? 5 : 6, value.size()));
    }

    @Test
    void bestPath() {
        assertEquals(1651, PathFinder.bestPath(valves));
    }

    @Test
    void bestPathWithElephant() {
        assertEquals(1707, PathFinder.bestPathWithElephant(valves));
    }
}