package com.putoet.day15;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {
    private static final List<String> input = ResourceLines.list("/day15.txt");
    private static final Set<Beacon> beacons = Beacon.of(input);
    private static final Set<Sensor> sensors = Sensor.of(input, beacons);


    @Test
    void usedPositionsInRangeForRow() {
        assertEquals(26, Day15.usedPositionsInRangeForRow(beacons, sensors, 10));
    }

    @Test
    void tuningFrequencies() {
        assertEquals(56000011, Day15.tuningFrequencies(beacons, sensors, 0, 20));
    }
}