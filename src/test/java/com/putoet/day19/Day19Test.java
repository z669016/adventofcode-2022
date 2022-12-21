package com.putoet.day19;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Day19Test {
    private static final Day19 day19 = new Day19(null);

    @Test
    void max() {
        final var max = day19.blueprints().stream()
                .map(day19::max)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        assertEquals(33, max.stream().mapToInt(BlueprintState::qualityLevel).sum());
    }
}