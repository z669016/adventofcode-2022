package com.putoet.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {

    @Test
    void of() {
        assertEquals(new Instruction(3, 1, 2), Instruction.of("move 3 from 1 to 2"));
    }
}