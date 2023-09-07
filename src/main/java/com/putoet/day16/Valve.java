package com.putoet.day16;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

record Valve(@NotNull String id, int flowRate, @NotNull List<String> routes) {
    private static final Pattern VALVE_PATTERN =
            Pattern.compile("Valve ([A-Z]+) has flow rate=(\\d+); tunnels? leads? to valves? ([A-Z ,]+)");

    public static Valve from(@NotNull String line) {
        final var matcher = VALVE_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid Valves spec: " + line);

        final var id = matcher.group(1);
        final var flowRate = Integer.parseInt(matcher.group(2));
        final var next = matcher.group(3).split(", ");

        return new Valve(id, flowRate, Arrays.stream(next).toList());
    }
}
