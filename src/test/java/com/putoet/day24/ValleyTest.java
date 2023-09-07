package com.putoet.day24;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ValleyTest {
    @Test
    void from() {
        final var map = """
                #.#####
                #.....#
                #>....#
                #.....#
                #...v.#
                #.....#
                #####.#""";
        final var valley = Valley.of(Arrays.stream(map.split("\n")).toList());
        assertEquals(map, valley.toString());
    }

    @Test
    void next() {
        final var map = """
                #.#####
                #.....#
                #>....#
                #.....#
                #...v.#
                #.....#
                #####.#""";
        var valley = Valley.of(Arrays.stream(map.split("\n")).toList());

        valley = valley.next();
        assertEquals("""
                #.#####
                #.....#
                #.>...#
                #.....#
                #.....#
                #...v.#
                #####.#""", valley.toString());

        valley = valley.next();
        assertEquals("""
                #.#####
                #...v.#
                #..>..#
                #.....#
                #.....#
                #.....#
                #####.#""", valley.toString());

        valley = valley.next();
        assertEquals("""
                #.#####
                #.....#
                #...2.#
                #.....#
                #.....#
                #.....#
                #####.#""", valley.toString());

        valley = valley.next();
        assertEquals("""
                #.#####
                #.....#
                #....>#
                #...v.#
                #.....#
                #.....#
                #####.#""", valley.toString());

        valley = valley.next();
        assertEquals("""
                #.#####
                #.....#
                #>....#
                #.....#
                #...v.#
                #.....#
                #####.#""", valley.toString());

        assertEquals("""
                #E#####
                #.....#
                #>....#
                #.....#
                #...v.#
                #.....#
                #####.#""", valley.toString(Point.of(1,0)));

        assertEquals("""
                #.#####
                #.....#
                #>....#
                #.....#
                #...v.#
                #.....#
                #####E#""", valley.toString(Point.of(5,6)));
    }
}