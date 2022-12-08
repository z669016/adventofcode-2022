package com.putoet.day8;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForrestTest {

    private Forrest forrest;

    @BeforeEach
    void init() {
        forrest = new Forrest(GridUtils.of(ResourceLines.list("/day8.txt")));
    }

    @Test
    void visibleTrees() {
        assertEquals(21, forrest.visibleTrees());
    }

    @Test
    void highestScenicScore() {
        assertEquals(8, forrest.highestScenicScore());
    }
}