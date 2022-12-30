package com.putoet.day24;

import com.putoet.grid.Point;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ValvesTest {

    @Test
    void solve() {
        final Optional<Triplet<Valley, Point,Integer>> count = PathFinder.solve();
        assertEquals(18, count.orElseThrow().getValue2());
    }
}