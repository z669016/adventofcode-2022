package com.putoet.day2;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void of() {
        final List<String> input = ResourceLines.list("/day2.txt");
        final int score = input.stream().map(Game::of).mapToInt(Game::score).sum();
        assertEquals(15, score);
    }

    @Test
    void of2() {
        final List<String> input = ResourceLines.list("/day2.txt");
        final int score = input.stream().map(Game::of2).mapToInt(Game::score).sum();
        assertEquals(12, score);
    }
}