package com.putoet.day16;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class Routes {
    // https://www.baeldung.com/cs/floyd-warshall-shortest-path
    // https://www.baeldung.com/cs/dijkstra-vs-floyd-warshall
    public static Map<Route, Integer> shortestPaths(Collection<Valve> valves) {
        final Map<Route, Integer> costs = new HashMap<>();

        // create a map for every possible route, and irrelevant valves must be in, as
        // the shortest route from/to a valuable valve may run through an irrelevant valve
        for (Valve valve : valves) {
            valve.routes().forEach(target -> costs.put(new Route(valve.id(), target), 1));
        }

        // find all shortest routes between two points, possibly via another point
        for (Valve via : valves) {
            for (Valve from : valves) {
                for (Valve to : valves) {
                    if (from == to || from == via || to == via) {
                        continue;
                    }

                    final Route route1 = new Route(from.id(), via.id());
                    final Route route2 = new Route(via.id(), to.id());

                    if (costs.containsKey(route1) && costs.containsKey(route2)) {
                        final Route directRoute = new Route(from.id(), to.id());

                        var viaCosts = costs.get(route1) + costs.get(route2);
                        if (viaCosts < costs.getOrDefault(directRoute, Integer.MAX_VALUE)) {
                            costs.put(directRoute, viaCosts);
                        }
                    }
                }
            }
        }

        // all valves with a flow of 0 don't matter, and
        // all routes from/to a valve with flow 0 don't matter
        final var irrelevantValves = Valves.irrelevantValves(valves).stream().map(Valve::id).toList();
        final var irrelevantRoutes = costs.keySet().stream()
                .filter(route -> irrelevantValves.contains(route.from()) || irrelevantValves.contains(route.to()))
                .toList();

        // remove all irrelevant routes from the costs map to reduce search overhead
        irrelevantRoutes.forEach(costs::remove);

        return costs;
    }
}
