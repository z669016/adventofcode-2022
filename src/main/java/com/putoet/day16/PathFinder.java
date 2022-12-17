package com.putoet.day16;

import java.util.*;
import java.util.stream.Collectors;

public class PathFinder {
    public static final int MAX_TICKS = 30;
    public static final int Elephant_TRAINING_TIME = 4;

    public static Map<Valve, Map<Valve, List<Valve>>> paths(Map<String, Valve> valves) {
        final Map<Valve, Map<Valve, List<Valve>>> paths = new HashMap<>();
        final List<Valve> valued = Valves.valuedValves(valves.values());

        for (var from : valves.values()) {
            final Map<Valve, List<Valve>> fromCurrent = new HashMap<>();
            paths.put(from, fromCurrent);
            for (var to : valued) {
                if (!from.name().equals(to.name())) {
                    final Optional<List<Valve>> shortestPath = Valves.shortestPath(from, to);
                    shortestPath.ifPresent(l -> fromCurrent.put(to, l));
                }
            }
        }

        return paths;
    }

    private record State(Valve current, int minutes, int flow, Set<Valve> opened, int distance) {
        public List<State> next(Map<Valve, Map<Valve, List<Valve>>> paths) {
            return paths.get(current).entrySet().stream()
                    .filter(entry -> !opened.contains(entry.getKey())) // not yet open
                    .filter(entry -> minutes - entry.getValue().size() > 0) // can reach it in remaining time
                    .map(entry -> {
                        final var target = entry.getKey();
                        final var distance = entry.getValue().size();

                        final Set<Valve> nextOpened = new TreeSet<>(opened);
                        nextOpened.add(target);
                        int additionalFlow = (minutes - distance) * target.flowRate();
                        return new State(target, minutes - distance, flow + additionalFlow, nextOpened, distance);
                    })
                    .toList();
        }

        public String toString() {
            return "current=%s, minutes=%d, flow=%d, opened=%s".formatted(
                    current.name(),
                    minutes,
                    flow,
                    opened.stream().map(Valve::name).collect(Collectors.joining(", "))
            );
        }

        public String history() {
            return "%s|%s".formatted(
                    current.name(),
                    opened.stream().map(Valve::name).collect(Collectors.joining(","))
            );
        }
    }

    public static int bestPath(Map<String, Valve> valves) {
        final Map<String, State> history = new HashMap<>();
        final var paths = PathFinder.paths(valves);
        final var stack = new ArrayDeque<State>();
        int maxFlow = 0;
        int turns = 0;

        stack.push(new State(valves.get("AA"), MAX_TICKS, 0, Set.of(), 0));
        while (!stack.isEmpty()) {
            turns++;
            final State state = stack.pop();

            final List<State> next = state.next(paths);
            if (!next.isEmpty()) {
                next.stream()
                        .filter(n -> {
                            var key = n.history();
                            if (!history.containsKey(key))
                                history.put(key, n);

                            var max = history.get(key);
                            if (n.flow < max.flow)
                                return false;

                            history.put(key, n);
                            return true;
                        })
                        .forEach(stack::push);
            } else {
                if (state.flow > maxFlow) {
                    maxFlow = state.flow;
                }
            }
        }

        System.out.println("Used " + turns + " turns.");

        return maxFlow;
    }

    record Helper(Valve current, int minutes) {
    }

    record StateElephant(Helper you, Helper elephant, int flow, Set<Valve> opened) {

        public List<StateElephant> next(Map<Valve, Map<Valve, List<Valve>>> paths) {
            final List<StateElephant> next = new ArrayList<>();
            if (elephant.minutes > you.minutes) {
                next.addAll(nextElephant(paths));
                if (!next.isEmpty()) {
                    next.addAll(nextYou(paths));
                }
            } else {
                next.addAll(nextYou(paths));
                if (!next.isEmpty()) {
                    next.addAll(nextElephant(paths));
                }
            }

            return next;
        }

        private List<StateElephant> nextYou(Map<Valve, Map<Valve, List<Valve>>> paths) {

            return paths.get(you.current).entrySet().stream()
                    .filter(entry -> !opened.contains(entry.getKey())) // not yet open
                    .filter(entry -> you.minutes - entry.getValue().size() > 0) // can reach it in remaining time
                    .map(entry -> {
                        final var target = entry.getKey();
                        final var distance = entry.getValue().size();

                        final Set<Valve> nextOpened = new TreeSet<>(opened);
                        nextOpened.add(target);
                        int additionalFlow = (you.minutes - distance) * target.flowRate();
                        return new StateElephant(new Helper(target, you.minutes - distance), elephant, flow + additionalFlow, nextOpened);
                    })
                    //.peek(System.out::println)
                    .toList();
        }

        private List<StateElephant> nextElephant(Map<Valve, Map<Valve, List<Valve>>> paths) {

            return paths.get(elephant.current).entrySet().stream()
                    .filter(entry -> !opened.contains(entry.getKey())) // not yet open
                    .filter(entry -> elephant.minutes - entry.getValue().size() > 0) // can reach it in remaining time
                    .map(entry -> {
                        final var target = entry.getKey();
                        final var distance = entry.getValue().size();

                        final Set<Valve> nextOpened = new TreeSet<>(opened);
                        nextOpened.add(target);
                        int additionalFlow = (elephant.minutes - distance) * target.flowRate();
                        return new StateElephant(you, new Helper(target, elephant.minutes - distance), flow + additionalFlow, nextOpened);
                    })
                    .toList();
        }

        public String history() {
            return "%s|%s".formatted(
                    you.current.name().compareTo(elephant.current.name()) <= 0 ? key(you, elephant) : key(elephant, you),
                    opened.stream().map(Valve::name).collect(Collectors.joining(","))
            );
        }

        private static String key(Helper first, Helper second) {
            return "%s|%s".formatted(
                    first.current.name(),
                    second.current.name()
            );
        }

        public String toString() {
            return "you(%s,%d) elephant(%s,%d) flow=%d, opened=%s".formatted(
                    you.current.name(), you.minutes,
                    elephant.current.name(), elephant.minutes,
                    flow,
                    opened.stream().map(Valve::name).collect(Collectors.joining(", "))
            );
        }

    }

    public static int bestPathWithElephant(Map<String, Valve> valves) {
        final Map<String, StateElephant> history = new HashMap<>();
        final var paths = PathFinder.paths(valves);
        final var stack = new ArrayDeque<StateElephant>();
        int maxFlow = 0;
        long turns = 0;

        final Helper you = new Helper(valves.get("AA"), MAX_TICKS - Elephant_TRAINING_TIME);
        final Helper elephant = new Helper(valves.get("AA"), MAX_TICKS - Elephant_TRAINING_TIME);

        stack.push(new StateElephant(you, elephant, 0, Set.of()));
        while (!stack.isEmpty()) {
            turns++;
            final var state = stack.pop();

            final var next = state.next(paths);
            if (!next.isEmpty()) {
                next.stream()
                        .filter(n -> {
                            var key = n.history();
                            if (!history.containsKey(key))
                                history.put(key, n);

                            var max = history.get(key);
                            if (n.flow < max.flow) //  && n.you.minutes > max.you.minutes)
                                return false;

                            history.put(key, n);
                            return true;
                        })
                        .forEach(stack::push);
            } else {
                if (state.flow > maxFlow) {
                    maxFlow = state.flow;
                }
            }
        }

        System.out.println("Used " + turns + " turns.");
        System.out.println(history.size());

        return maxFlow;
    }
}
