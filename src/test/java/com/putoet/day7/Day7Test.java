package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {
    private static final Directory root = Day7.parseLog(ResourceLines.list("/day7.txt"));

    @Test
    void totalSize() {
        assertEquals(95437, Day7.totalSize(root));
    }

    @Test
    void feeUp() {
        assertEquals(8381165, Day7.freeUp(root));
    }

    @Test
    void smallestToDelete() {
        assertEquals(24933642, Day7.smallestToDelete(root).orElseThrow().size());
    }
}