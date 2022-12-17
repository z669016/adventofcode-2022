package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ValvesTest {
    private final List<String> input = ResourceLines.list("/day16.txt");

    private Map<String,Valve> valves;

    @BeforeEach
    void setup() {
        valves = Valves.from(input);
    }

    @Test
    void from() {
        assertEquals("AA", valves.get("AA").name());
        assertEquals(10, valves.size());
    }

    @Test
    void valuedValves() {
        final var valued = Valves.valuedValves(valves.values());
        assertEquals(6, valued.size());
        assertEquals(Set.of("BB", "CC", "DD", "EE", "HH", "JJ"), valued.stream().map(Valve::name).collect(Collectors.toSet()));
    }

    @Test
    void shortestPath() {
        final Optional<List<Valve>> fromAAtoHH = Valves.shortestPath(valves.get("AA"), valves.get("HH"));
        assertTrue(fromAAtoHH.isPresent());
        assertEquals(List.of(valves.get("AA"),
                valves.get("DD"),
                valves.get("EE"),
                valves.get("FF"),
                valves.get("GG"),
                valves.get("HH")
                ), fromAAtoHH.get());
    }
}