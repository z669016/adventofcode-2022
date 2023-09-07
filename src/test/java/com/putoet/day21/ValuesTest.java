package com.putoet.day21;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValuesTest {

    @Test
    void get() {
        var values = Values.of(ResourceLines.list("/day21.txt"));
        assertEquals("152", values.get(Values.ROOT));

        final var humn = values.getOperation(Values.HUMN);
        assertEquals("5", humn.get());

        values = Values.of(ResourceLines.list("/day21.txt"));
        values.setOperation(Values.HUMN, () -> Values.HUMN);
        assertEquals("(((4 + (2 * (humn - 3))) / 4) + 150)", values.get(Values.ROOT));

        values = Values.of(ResourceLines.list("/day21.txt"));
        values.setOperation(Values.HUMN, () -> "5");
        assertEquals("152", values.get(Values.ROOT));
    }
}