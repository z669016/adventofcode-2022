package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ValvesTest {

    private Valves valves;

    @BeforeEach
    void setup() {
        valves = new Valves(ResourceLines.stream("/day16.txt").map(Valve::from).toList());
    }

    @Test
    void irrelevantValves() {
        assertEquals(Set.of("FF", "GG", "II"),
                Valves.irrelevantValves(valves.valves()).stream().map(Valve::id).collect(Collectors.toSet()));
    }

    @Test
    void relevantValves() {
        assertEquals(Set.of("AA", "BB", "CC", "DD", "EE", "HH", "JJ"),
                Valves.relevantValves(valves.valves()).stream().map(Valve::id).collect(Collectors.toSet()));
    }

    @Test
    void maximumPressure() {
        assertEquals(1651, valves.maximumPressure());
    }

    @Test
    void maximumPressureWithHelp() {
        assertEquals(1707, valves.maximumPressureWithHelp());
    }
}