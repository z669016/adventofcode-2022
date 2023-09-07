package com.putoet.day16;

import org.jetbrains.annotations.NotNull;
import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.stream.Collectors;

class Valves {
    public static final String STARTING_VALVE = "AA";

    private final Collection<Valve> valves;
    private final Map<String, Valve> valveMap;
    private final Map<Route, Integer> costs;

    public Valves(@NotNull Collection<Valve> valves) {
        this.valves = valves;
        this.valveMap = relevantValves(valves).stream().collect(Collectors.toMap(Valve::id, v -> v));
        this.costs = Routes.shortestPaths(valves);
    }

    public Collection<Valve> valves() {
        return valves;
    }

    public static List<Valve> irrelevantValves(@NotNull Collection<Valve> valves) {
        return valves.stream()
                .filter(valve -> valve.flowRate() == 0 && !valve.id().equals(STARTING_VALVE))
                .toList();
    }

    public static List<Valve> relevantValves(@NotNull Collection<Valve> valves) {
        return valves.stream()
                .filter(valve -> valve.flowRate() != 0 || valve.id().equals(STARTING_VALVE))
                .toList();
    }

    public int maximumPressure() {
        return maximumPressure(State.init(30, Set.of())).totalFlow;
    }

    private State maximumPressure(State initialState) {
        final var queue = new LinkedList<State>();
        queue.offer(initialState);

        State max = null;
        while (!queue.isEmpty()) {
            var state = queue.poll();

            final var newStates = costs.keySet().stream().filter(route -> route.from().equals(state.valveId))
                    .filter(route -> !state.visited.contains(route.to()))
                    .filter(route -> costs.get(route) + 1 <= state.minutes)
                    .map(route -> state.moveTo(route.to(), costs.get(route), valveMap.get(route.to()).flowRate()))
                    .toList();

            if (newStates.isEmpty()) {
                if (max == null || state.totalFlow > max.totalFlow) {
                    max = state;
                }
            }
            queue.addAll(newStates);
        }

        return max;
    }

    public int maximumPressureWithHelp() {
        final var targets = valveMap.keySet().stream()
                .filter(id -> !id.equals(STARTING_VALVE))
                .collect(Collectors.toSet());

        // find the max for any combination of valves visited by me/elephant
        // (each taking half of the possible combinations)
        return Generator.combination(targets)
                .simple(targets.size() / 2)
                .stream()
                .parallel()
                .mapToInt(toVisit -> {
                    var othersToVisit = targets.stream()
                            .filter(v -> !toVisit.contains(v))
                            .collect(Collectors.toSet());

                    var myValves = maximumPressure(State.init(26, toVisit));
                    var elephantValves = maximumPressure(State.init(26, othersToVisit));
                    return myValves.totalFlow + elephantValves.totalFlow;

                })
                .max()
                .orElse(0);
    }


    private record State(int minutes, String valveId, int totalFlow, Set<String> visited) {

        static State init(int minutes, Collection<String> visited) {
            return new State(minutes, STARTING_VALVE, 0, new HashSet<>(visited));
        }

        State moveTo(String moveTo, int costs, int additionalFlow) {
            var visited = new HashSet<>(this.visited);
            visited.add(valveId);
            return new State(
                    minutes - costs - 1,
                    moveTo,
                    totalFlow + additionalFlow * (minutes - costs - 1),
                    visited
            );
        }
    }
}