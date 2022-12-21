package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintTest {

    @Test
    void from() {
        final Map<Integer,Blueprint> blueprints = Blueprint.from(ResourceLines.list("/day19.txt"));
        assertEquals(2, blueprints.size());
    }
}