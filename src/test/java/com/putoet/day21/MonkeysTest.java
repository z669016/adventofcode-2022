package com.putoet.day21;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonkeysTest {

    @Test
    void get() {
        final Monkeys monkeys = Monkeys.from(ResourceLines.list("/day21.txt"));
        assertEquals(152, monkeys.get());
    }
}