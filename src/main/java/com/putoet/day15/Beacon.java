package com.putoet.day15;

import com.putoet.grid.Point;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public record Beacon(Point location) {
    public Beacon {
        assert location != null;
    }

    public long tuningFrequency() {
        return 4_000_000L * location.x() + location.y();
    }

    public static Set<Beacon> from(List<String> input) {
        assert input!= null;

        return input.stream()
                .map(Beacon::from)
                .collect(Collectors.toSet());
    }

    public static Set<Beacon> atRow(int row, Set<Beacon> beacons) {
        return beacons.stream()
                .filter(beacon -> beacon.location().y() == row)
                .collect(Collectors.toSet());
    }

    private static final Pattern LINE_PATTERN =
            Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
    public static Beacon from(String line) {
        assert line != null;

        final var matches = LINE_PATTERN.matcher(line);
        if (!matches.matches())
            throw new IllegalArgumentException("Invalid sensor data: " + line);

        final int bx = Integer.parseInt(matches.group(3));
        final int by = Integer.parseInt(matches.group(4));

        return new Beacon(Point.of(bx, by));
    }
}
