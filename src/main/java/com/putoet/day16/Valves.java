package com.putoet.day16;

import java.util.*;

public class Valves {
    public static Map<String,Valve> from(List<String> input) {
        final Map<String,Valve> valves = new HashMap<>();
        input.forEach(line -> Valve.from(line, valves));

        return valves;
    }

    private record State(Valve valve, State prev) {
        List<Valve> path() {
            final List<Valve> list = new ArrayList<>();
            State state = this;
            while (state != null) {
                list.add(state.valve);
                state = state.prev;
            }

            final List<Valve> path = new ArrayList<>();
            for (var i = list.size() - 1; i >= 0; i--) {
                path.add(list.get(i));
            }
            return path;
        }

        int steps() {
            return path().size() - 1;
        }
    }

    public static Optional<List<Valve>> shortestPath(Valve from, Valve to) {
        final Set<String> history = new HashSet<>();
        final Queue<State> queue = new LinkedList<>();

        history.add(from.name());
        queue.offer(new State(from, null));
        while (!queue.isEmpty()) {
            final State current = queue.poll();
            if (current.valve.name().equals(to.name()))
                return Optional.of(current.path());

            current.valve.tunnels().stream()
                    .filter(v -> history.add(v.name()))
                    .forEach(v -> queue.offer(new State(v, current)));
        }

        return Optional.empty();
    }

    public static List<Valve> valuedValves(Collection<Valve> valves) {
        return valves.stream()
                .filter(v -> v.flowRate() > 0)
                .toList();
    }
}
