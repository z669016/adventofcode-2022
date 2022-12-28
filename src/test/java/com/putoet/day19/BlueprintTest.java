package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintTest {
    final Map<Integer,Blueprint> blueprints = Blueprint.from(ResourceLines.list("/day19.txt"));

    @Test
    void from() {
        assertEquals(2, blueprints.size());
    }

    @Test
    void max() {
        final var max = blueprints.values().stream()
                .map(Blueprint::max)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        assertEquals(33, max.stream().mapToInt(BlueprintState::qualityLevel).sum());
    }

}