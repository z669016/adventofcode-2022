package com.putoet.day15;

import com.putoet.grid.Point;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public record Sensor(Point location, Beacon beacon) {
    public Sensor {
        assert location != null;
        assert beacon != null;
    }

    public static Set<Sensor> from(List<String> input, Set<Beacon> beacons) {
        assert input!= null;

        return input.stream()
                .map(line -> from(line, beacons))
                .collect(Collectors.toSet());
    }

    private static final Pattern LINE_PATTERN =
            Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");

    public static Sensor from(String line, Set<Beacon> beacons) {
        assert line != null;

        final var matches = LINE_PATTERN.matcher(line);
        if (!matches.matches())
            throw new IllegalArgumentException("Invalid sensor data: " + line);

        final int sx = Integer.parseInt(matches.group(1));
        final int sy = Integer.parseInt(matches.group(2));
        final Point beaconLocation = Point.of(
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
        final int reach = location.manhattanDistance(beacon.location());
        int distance = location.manhattanDistance(Point.of(location.x(), row));
        if (distance > reach)
            return Optional.empty();

        return Optional.of(new Range(location.x() - (reach - distance), location.x() + (reach - distance)));
    }

    public static Set<Sensor> atRow(int row, Set<Sensor> sensors) {
        return sensors.stream()
                .filter(sensor -> sensor.location.y() == row)
                .collect(Collectors.toSet());
    }
}
