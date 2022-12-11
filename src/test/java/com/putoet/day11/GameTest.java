package com.putoet.day11;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private List<Monkey> monkeys;

    @BeforeEach
    void setup() {
        monkeys = Game.monkeys(ResourceLines.list("/day11.txt"));
    }

    @Test
    void rounds() {
        Game.rounds(monkeys, 1);

        assertEquals(List.of(20L, 23L, 27L, 26L), monkeys.get(0).items());
        assertEquals(List.of(2080L, 25L, 167L, 207L, 401L, 1046L), monkeys.get(1).items());
        assertTrue(monkeys.get(2).items().isEmpty());
        assertTrue(monkeys.get(3).items().isEmpty());
    }

    @Test
    void rounds20() {
        Game.rounds(monkeys, 20);

        assertEquals(List.of(10L, 12L, 14L, 26L, 34L), monkeys.get(0).items());
        assertEquals(List.of(245L, 93L, 53L, 199L, 115L), monkeys.get(1).items());
        assertTrue(monkeys.get(2).items().isEmpty());
        assertTrue(monkeys.get(3).items().isEmpty());

        assertEquals(101L, monkeys.get(0).inspected());
        assertEquals(95L, monkeys.get(1).inspected());
        assertEquals(7L, monkeys.get(2).inspected());
        assertEquals(105L, monkeys.get(3).inspected());

        assertEquals(10605L, Game.monkeyBusiness(monkeys));
    }

    @Test
    void rounds10000() {
        monkeys = Game.monkeys(ResourceLines.list("/day11-x.txt"), value -> value);

        for (var monkey : monkeys) {
            monkey.items(List.of());
        }

        Monkey.verbose = true;
        monkeys.get(0).items(List.of(73L));
        Game.rounds(monkeys, 200);

        // assertEquals(2713310158L, Game.monkeyBusiness(monkeys));
    }
}