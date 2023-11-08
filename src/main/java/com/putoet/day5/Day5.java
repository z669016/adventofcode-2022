package com.putoet.day5;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {
    static final Crane crateMover9000 = Crates::take;
    static final Crane crateMover9001 = (stack, count) -> stack.take(count).reverse();

    record Input(List<Instruction> instructions, List<String> stacks) {}

    public static void main(String[] args) {
        final var input = init(ResourceLines.list("/day5.txt"));

        Timer.run(() -> {
            final Cargo cargo = new Cargo(input.stacks().stream().map(Crates::new).toList());
            System.out.println("Top crates after processing are: " + move(input.instructions(), cargo, crateMover9000));
        });

        Timer.run(() -> {
            final Cargo cargo = new Cargo(input.stacks().stream().map(Crates::new).toList());
            System.out.println("Top crates after processing with the 9001 crane are: " + move(input.instructions(), cargo, crateMover9001));
        });
    }

    static Input init(List<String> input) {
        final var instructions = new ArrayList<Instruction>();
        List<String> stacks = List.of();

        var layout = true;
        final var stack = new ArrayList<String>();
        for (var line : input) {
            if (layout) {
                if (!line.isEmpty())
                    stack.add(line);

                if (line.isEmpty()) {
                    stacks = createStacks(stack);
                    layout = false;
                }
            } else {
                instructions.add(Instruction.of(line));
            }
        }

        return new Input(instructions, stacks);
    }

    static List<String> createStacks(@NotNull List<String> stack) {
        final var size = stack.size() - 1;
        final var columnCount = columnCount(stack, size);
        return columns(stack, size, columnCount);
    }

    static List<String> columns(@NotNull List<String> stack, int size, int columnCount) {
        final var columns = new StringBuilder[columnCount];
        for (var i = 0; i < columnCount; i++)
            columns[i] = new StringBuilder();

        for (var lineNumber = 0; lineNumber < size; lineNumber++) {
            final String line = stack.get(lineNumber);
            for (var column = 0; column < columnCount; column++) {
                if (line.length() > 4 * column) {
                    final char c = line.charAt(4 * column + 1);
                    if (c != ' ')
                        columns[column].append(c);
                }
            }
        }

        return Arrays.stream(columns)
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString)
                .toList();
    }

    private static int columnCount(List<String> stack, int size) {
        final String columnsLine = stack.get(size);
        return Integer.parseInt(columnsLine.substring(columnsLine.lastIndexOf(' ') + 1));
    }

    static String move(List<Instruction> instructions, Cargo cargo, Crane crane) {
        instructions.forEach(instruction -> cargo.add(instruction.to(), cargo.take(instruction.from(), instruction.count(), crane)));
        return cargo.top();
    }

}
