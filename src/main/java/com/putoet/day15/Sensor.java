package com.putoet.day15;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

record Sensor(@NotNull Point location, @NotNull Beacon beacon) {
    public static Set<Sensor> of(@NotNull List<String> input, @NotNull Set<Beacon> beacons) {
        return input.stream()
                .map(line -> of(line, beacons))
                .collect(Collectors.toSet());
    }

    private static final Pattern LINE_PATTERN =
            Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");

    public static Sensor of(@NotNull String line, @NotNull Set<Beacon> beacons) {
        final var matches = LINE_PATTERN.matcher(line);
        if (!matches.matches())
            throw new IllegalArgumentException("Invalid sensor data: " + line);

        final var sx = Integer.parseInt(matches.group(1));
        final var sy = Integer.parseInt(matches.group(2));
        final var beaconLocation = Point.of(
                Integer.parseInt(matches.group(3)),
                Integer.parseInt(matches.group(4))
                );

        return new Sensor(Point.of(sx, sy), beacons.stream()
                .filter(beacon -> beacon.location().equals(beaconLocation))
                .findFirst()
                .orElseThrow()
        );
    }

    public Optional<Range> rangeForRow(int row) {
        final var reach = location.manhattanDistance(beacon.location());
        int distance = location.manhattanDistance(Point.of(location.x(), row));
        if (distance > reach)
            return Optional.empty();

        return Optional.of(new Range(location.x() - (reach - distance), location.x() + (reach - distance)));
    }

    public static Set<Sensor> atRow(int row, @NotNull Set<Sensor> sensors) {
        return sensors.stream()
                .filter(sensor -> sensor.location.y() == row)
                .collect(Collectors.toSet());
    }
}
