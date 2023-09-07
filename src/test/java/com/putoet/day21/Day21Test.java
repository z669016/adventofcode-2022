package com.putoet.day21;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day21Test {

    @Test
    void findHumn() {
        final var values = Values.of(ResourceLines.list("/day21.txt"));
        values.setOperation(Values.HUMN, new Value(Values.HUMN));
        final var root = (Operation) values.getOperation(Values.ROOT);
        values.setOperation(Values.ROOT, new Operation(values, root.left(), Operator.EQUALS, root.right()));

        assertEquals("301", Day21.findHumn(values, 1, values.getOperation(Values.ROOT)));
    }
}