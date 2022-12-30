package com.putoet.day16;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Valve(String id, int flowRate, List<String> routes) {
    private static final Pattern VALVE_PATTERN = Pattern.compile("Valve ([A-Z]+) has flow rate=(\\d+); tunnels? leads? to valves? ([A-Z ,]+)");

    public static Valve from(String line) {
        assert line != null;

        final Matcher matcher = VALVE_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid Valves spec: " + line);

        final String id = matcher.group(1);
        final int flowRate = Integer.parseInt(matcher.group(2));
        final String[] next = matcher.group(3).split(", ");

        return new Valve(id, flowRate, Arrays.stream(next).toList());
    }
}
