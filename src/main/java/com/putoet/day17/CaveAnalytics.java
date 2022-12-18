package com.putoet.day17;

import org.javatuples.Triplet;

import java.util.*;
import java.util.stream.Collectors;

public class CaveAnalytics extends Cave{
    private static final int BLOCK_SIZE = 12;
    private final Map<String, List<Integer>> signatures = new HashMap<>();
    private final Queue<Integer> block = new LinkedList<>();

    public record Analytics(String key, int startRepeat, int repeatBlockSize) {}
    private Analytics analytics;

    public CaveAnalytics(Push push) {
        super(push);
    }

    public Analytics analytics() {
        return analytics;
    }

    @Override
    public void run() {
        for (int i = 0; i < maxRocks(); i++) {
            final var block = signatureDrop(rocks().get(), push());

            if (block.isPresent()) {
                var key = block.stream().map(String::valueOf).collect(Collectors.joining(","));
                final var values = signatures.computeIfAbsent(key, k -> new ArrayList<>());
                values.add(i);
            }
        }

        var triplet = signatures.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(e -> Triplet.with(e.getKey(), e.getValue().get(0), e.getValue().get(1) - e.getValue().get(0)))
                .min(Comparator.comparing(Triplet::getValue1))
                .orElseThrow();

        analytics = new Analytics(triplet.getValue0(), triplet.getValue1(), triplet.getValue2());
    }

    public Optional<Queue<Integer>> signatureDrop(Rock rock, Push push) {
        block.offer(drop(rock, push));
        if (block.size() > BLOCK_SIZE)
            block.poll();

        return block.size() == BLOCK_SIZE ? Optional.of(block) : Optional.empty();
    }
}
