package com.putoet.day10;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {
    private List<Instruction> program;

    @BeforeEach
    void setup() {
        program = Compiler.compile(ResourceLines.list("/day10.txt"));
    }

    @Test
    void process() {
        final var device = new Device();
        final var signalStrengths = device.process(program, cycle -> cycle >= 220);
        assertEquals(13140L, signalStrengths.stream().mapToLong(SignalStrength::strength).sum());
    }
}