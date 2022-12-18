package com.putoet.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Valve implements Comparable<Valve> {
    private final String name;
    private int flowRate = 0;
    private final List<Valve> tunnels = new ArrayList<>();

    public Valve(String name) {
        this.name = name;
    }

    public Valve(String name, int flowRate) {
        this.name = name;
        this.flowRate = flowRate;
    }

    public String name() {
        return name;
    }

    public int flowRate() {
        return flowRate;
    }

    public Valve flowRate(int flowRate) {
        this.flowRate = flowRate;
        return this;
    }

    public void connect(Valve next) {
        this.tunnels.add(next);
    }

    public List<Valve> tunnels() {
        return tunnels;
    }

    public String toString() {
        return "Valve %s has flow rate=%d; tunnel%s lead%s to valve%s %s"
                .formatted(name,
                        flowRate,
                        tunnels.size() > 1 ? "s" : "",
                        tunnels.size() > 1 ? "" : "s",
                        tunnels.size() > 1 ? "s" : "",
                        listAsString(tunnels));
    }

    private static String listAsString(List<Valve> valves) {
        assert valves != null;

        return valves.stream().map(Valve::name).collect(Collectors.joining(", "));
    }

    private static final Pattern VALVE_PATTERN = Pattern.compile("Valve ([A-Z]+) has flow rate=(\\d+); tunnels? leads? to valves? ([A-Z ,]+)");

    public static Valve from(String line, Map<String,Valve> valves) {
        assert line != null;
        assert valves != null;

        final Matcher matcher = VALVE_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid Valves spec: " + line);

        final String name = matcher.group(1);
        final int flowRate = Integer.parseInt(matcher.group(2));
        final String[] next = matcher.group(3).split(", ");
        final Valve valve = valves.computeIfAbsent(name, n -> new Valve(n, flowRate)).flowRate(flowRate);

        for (var n : next) {
            valve.connect(valves.computeIfAbsent(n, Valve::new));
        }

        return valve;
    }

    @Override
    public int compareTo(Valve other) {
        return name.compareTo(other.name);
    }
}
