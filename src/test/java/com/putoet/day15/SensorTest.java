package com.putoet.day15;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensorTest {
    private static final List<String> input = ResourceLines.list("/day15.txt");

    @Test
    void from() {
        final Set<Beacon> beacons = Beacon.from(input);
        assertEquals(14, Sensor.from(input, beacons).size());
    }
}