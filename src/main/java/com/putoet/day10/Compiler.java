package com.putoet.day10;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.IntStream;

class Compiler {
    public static List<Instruction> compile(@NotNull List<String> program) {
        return IntStream.range(0, program.size())
                .mapToObj(line -> compile(line, program.get(line)))
                .toList();
    }

    public static Instruction compile(int line, @NotNull String programLine) {
        if ("noop".equals(programLine)) {
            return cpu -> {
                cpu.cycle();
                return 1;
            };
        }

        if (programLine.startsWith("addx")) {
            return cpu -> {
                cpu.cycle();
                cpu.cycle();
                cpu.x(cpu.x() + Integer.parseInt(programLine.split(" ")[1]));
                return 1;
            };
        }

        throw new IllegalArgumentException("Invalid instruction '" + programLine + "' at line " + line);
    }
}
