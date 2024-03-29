package com.putoet.day23;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ElvesTest {

    @Test
    void of() {
        final var elves = Elves.of(ResourceLines.list("/day23.txt"));
        assertEquals("""
                ....#..
                ..###.#
                #...#.#
                .#...##
                #.###..
                ##.#.##
                .#..#..""", elves.toString());
    }

    @Test
    void smallMove() {
        final var input = Arrays.stream("""
                .....
                ..##.
                ..#..
                .....
                ..##.
                .....""".split("\n")).toList();

        final var validDirections = new ValidDirections();
        final var elves = Elves.of(input);

        elves.move(validDirections.get());
        assertEquals("""
                ##
                ..
                #.
                .#
                #.""", elves.toString());

        elves.move(validDirections.get());
        assertEquals("""
                .##.
                #...
                ...#
                ....
                .#..""", elves.toString());

        elves.move(validDirections.get());
        assertEquals("""
                ..#..
                ....#
                #....
                ....#
                .....
                ..#..""", elves.toString());
    }

    @Test
    void move() {
        final var validDirections = new ValidDirections();
        final var elves = Elves.of(ResourceLines.list("/day23.txt"));

        elves.move(validDirections.get());
        assertEquals("""
                .....#...
                ...#...#.
                .#..#.#..
                .....#..#
                ..#.#.##.
                #..#.#...
                #.#.#.##.
                .........
                ..#..#...""", elves.toString());

        elves.move(validDirections.get());
        assertEquals("""
                ......#....
                ...#.....#.
                ..#..#.#...
                ......#...#
                ..#..#.#...
                #...#.#.#..
                ...........
                .#.#.#.##..
                ...#..#....""", elves.toString());

        for (var i = 3; i <= 10; i++)
            elves.move(validDirections.get());

        assertEquals("""
                ......#.....
                ..........#.
                .#.#..#.....
                .....#......
                ..#.....#..#
                #......##...
                ....##......
                .#........#.
                ...#.#..#...
                ............
                ...#..#..#..""", elves.toString());

        assertEquals(110L, elves.emptyGrounds());
    }

    @Test
    void noMoves() {
        final var validDirections = new ValidDirections();
        final var elves = Elves.of(ResourceLines.list("/day23.txt"));

        int i = 1;
        while (elves.move(validDirections.get()) != 0)
            i++;

        assertEquals(20, i);
    }
}