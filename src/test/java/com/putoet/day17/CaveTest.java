package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaveTest {
    private static final String input = ResourceLines.line("/day17.txt");

    private Push push;
    private Rocks rocks;

    @BeforeEach
    void setup() {
        push = new Push(input);
        rocks = new Rocks();
    }

    @Test
    void drop() {
        final Cave cave = new Cave(push);

        cave.drop(rocks.get(), push);
        assertEquals(1, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(4, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(6, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(7, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(9, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(10, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(13, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(15, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(17, cave.highestRock());
        cave.drop(rocks.get(), push);
        assertEquals(17, cave.highestRock());

        System.out.println(cave);
    }

    @Test
    void run() {
        final Cave cave = new Cave(push);
        cave.run();

        assertEquals(3068, cave.highestRock());
    }
}