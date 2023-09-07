package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintTest {
    final Map<Integer,Blueprint> blueprints = Blueprint.of(ResourceLines.list("/day19.txt"));

    @Test
    void from() {
        assertEquals(2, blueprints.size());
    }

    @Test
    void max24() {
        final var max = blueprints.values().stream()
                .parallel()
                .map(Blueprint::max)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        assertEquals(33, max.stream().mapToInt(BlueprintState::qualityLevel).sum());
    }

    @Test
    void max32() {
        var max = blueprints.get(2).max(32).orElseThrow();
        assertEquals(62, max.prod().geode());

        max = blueprints.get(1).max(32).orElseThrow();
        assertEquals(56, max.prod().geode());
    }
}