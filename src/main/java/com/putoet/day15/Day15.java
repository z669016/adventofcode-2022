package com.putoet.day15;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {
    public static void main(String[] args) {
        final var input = ResourceLines.list("/day15.txt");
        final var beacons = Beacon.of(input);
        final var sensors = Sensor.of(input, beacons);

        Timer.run(() -> {
            final var count = 2_000_000;
            System.out.println("The number of positions that cannot contain a beacon at row " + count + " is: " +
                               usedPositionsInRangeForRow(beacons, sensors, count));
        });

        Timer.run(() -> {
            final var min = 0L;
            final var max = 4_000_000L;
            System.out.println("The distress beacon tuning frequency is: " + tuningFrequencies(beacons, sensors, min, max));
        });
    }

    static long usedPositionsInRangeForRow(Set<Beacon> beacons, Set<Sensor> sensors, int row) {
        final var combined = usedRanges(sensors, row);
        long count = combined.stream()
                .mapToInt(Range::count)
                .sum();

        count -= Sensor.atRow(row, sensors).size();
        count -= Beacon.atRow(row, beacons).size();

        return count;
    }

    static Set<Range> usedRanges(Set<Sensor> sensors, int row) {
        final var ranges = sensors.stream()
                .map(sensor -> sensor.rangeForRow(row))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        Set<Range> combined = new HashSet<>();
        for (var range : ranges)
            combined = Range.combine(combined, range);
        return combined;
    }


    static long tuningFrequencies(Set<Beacon> beacons, Set<Sensor> sensors, long min, long max) {
        final var miny = beacons.stream()
                .filter(beacon -> beacon.location().x() >= min && beacon.location().x() <= max)
                .filter(beacon -> beacon.location().y() >= min && beacon.location().y() <= max)
                .mapToInt(beacon -> beacon.location().y())
                .min()
                .orElseThrow();
        final var maxy = beacons.stream()
                .filter(beacon -> beacon.location().x() >= min && beacon.location().x() <= max)
                .filter(beacon -> beacon.location().y() >= min && beacon.location().y() <= max)
                .mapToInt(beacon -> beacon.location().y())
                .max()
                .orElseThrow();

        for (var y = miny; y <= maxy; y++) {
            final var used = usedRanges(sensors, y).stream()
                    .sorted(Comparator.comparing(Range::lower))
                    .toList();
            if (used.size() > 1) {
                return new Beacon(Point.of(used.get(0).upper() + 1, y)).tuningFrequency();
            }
        }

        throw new IllegalStateException("Could not find beacon location");
    }
}
