package com.putoet.day15;

import com.putoet.day.Day;
import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 extends Day {
    private final Set<Sensor> sensors;
    private final Set<Beacon> beacons;

    public Day15() {
        final List<String> input = ResourceLines.list("/day15.txt");
        beacons = Beacon.from(input);
        sensors = Sensor.from(input, beacons);
    }

    public static void main(String[] args) {
        final var day = new Day15();
        day.challenge();
    }

    @Override
    public void part1() {
        final int row = 2_000_000;
        final long count = usedPositionsInRangeForRow(row);

        System.out.println("The number of positions that cannot contain a beacon at row " + row + " is: " + count);
    }

    public long usedPositionsInRangeForRow(int row) {
        final var combined = usedRanges(row);
        long count = combined.stream()
                .mapToInt(Range::count)
                .sum();

        count -= Sensor.atRow(row, sensors).size();
        count -= Beacon.atRow(row, beacons).size();

        return count;
    }

    private Set<Range> usedRanges(int row) {
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

    @Override
    public void part2() {
        final long min = 0;
        final long max = 4_000_000;
        System.out.println("The distress beacon tuning frequency is: " + tuningFrequencies(min, max));
    }

    public long tuningFrequencies(long min, long max) {
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

        for (int y = miny; y <= maxy; y++) {
            final var used = usedRanges(y).stream()
                    .sorted(Comparator.comparing(Range::lower))
                    .toList();
            if (used.size() > 1) {
                return new Beacon(Point.of(used.get(0).upper() + 1, y)).tuningFrequency();
            }
        }

        throw new IllegalStateException("Could not find beacon location");
    }
}
