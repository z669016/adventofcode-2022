package com.putoet.day17;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class CaveAnalytics extends Cave{
    private static final int BLOCK_SIZE = 12;
    private final Map<String, List<Integer>> signatures = new HashMap<>();
    private final Queue<Integer> block = new LinkedList<>();

    public record Analytics(@NotNull String key, int startRepeat, int repeatBlockSize) {}
    private Analytics analytics;

    public CaveAnalytics(@NotNull Push push) {
        super(push);
    }

    public Analytics analytics() {
        return analytics;
    }

    @Override
    public void run() {
        for (var i = 0; i < maxRocks(); i++) {
            final var block = signatureDrop(rocks().get(), push());

            if (block.isPresent()) {
                var key = block.stream().map(String::valueOf).collect(Collectors.joining(","));
                final var values = signatures.computeIfAbsent(key, k -> new ArrayList<>());
                values.add(i);
            }
        }

        analytics = signatures.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(e -> new Analytics(e.getKey(), e.getValue().get(0), e.getValue().get(1) - e.getValue().get(0)))
                .min(Comparator.comparing(Analytics::startRepeat))
                .orElseThrow();
    }

    public Optional<Queue<Integer>> signatureDrop(@NotNull Rock rock, @NotNull Push push) {
        block.offer(drop(rock, push));
        if (block.size() > BLOCK_SIZE)
            block.poll();

        return block.size() == BLOCK_SIZE ? Optional.of(block) : Optional.empty();
    }
}
