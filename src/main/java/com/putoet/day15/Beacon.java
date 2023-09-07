package com.putoet.day15;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

record Beacon(@NotNull Point location) {
    public long tuningFrequency() {
        return 4_000_000L * location.x() + location.y();
    }

    public static Set<Beacon> of(@NotNull List<String> input) {
        return input.stream()
                .map(Beacon::of)
                .collect(Collectors.toSet());
    }

    public static Set<Beacon> atRow(int row, @NotNull Set<Beacon> beacons) {
        return beacons.stream()
                .filter(beacon -> beacon.location().y() == row)
                .collect(Collectors.toSet());
    }

    private static final Pattern LINE_PATTERN =
            Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
    public static Beacon of(@NotNull String line) {
        final var matches = LINE_PATTERN.matcher(line);
        if (!matches.matches())
            throw new IllegalArgumentException("Invalid sensor data: " + line);

        final var bx = Integer.parseInt(matches.group(3));
        final var by = Integer.parseInt(matches.group(4));

        return new Beacon(Point.of(bx, by));
    }
}
