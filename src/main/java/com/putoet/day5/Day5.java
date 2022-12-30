package com.putoet.day5;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 extends Day {
    private final List<Instruction> instructions = new ArrayList<>();
    private List<String> stacks;

    protected static final Crane crateMover9000 = Crates::take;
    protected static final Crane crateMover9001 = (stack, count) -> stack.take(count).reverse();


    public Day5() {
        boolean layout = true;
        final var input = ResourceLines.list("/day5.txt");
        final List<String> stack = new ArrayList<>();
        for (var line : input) {
            if (layout) {
                if (line.length() > 0)
                    stack.add(line);

                if (line.length() == 0) {
                    stacks = createStacks(stack);
                    layout = false;
                }
            } else {
                instructions.add(Instruction.of(line));
            }
        }
    }

    private List<String> createStacks(List<String> stack) {
        final int size = stack.size() - 1;
        final int columnCount = columnCount(stack, size);
        return columns(stack, size, columnCount);
    }

    private static List<String> columns(List<String> stack, int size, int columnCount) {
        final StringBuilder[] columns = new StringBuilder[columnCount];
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

    public static void main(String[] args) {
        final var day = new Day5();
        day.challenge();
    }

    public Cargo initCargo() {
        return new Cargo(stacks.stream().map(Crates::new).toList());
    }

    @Override
    public void part1() {
        final Cargo cargo = initCargo();
        System.out.println("Top crates after processing are: " + move(cargo, crateMover9000));
    }

    public String move(Cargo cargo, Crane crane) {
        instructions.forEach(instruction -> cargo.add(instruction.to(), cargo.take(instruction.from(), instruction.count(), crane)));
        return cargo.top();
    }

    @Override
    public void part2() {
        final Cargo cargo = initCargo();
        System.out.println("Top crates after processing with the 9001 crane are: " + move(cargo, crateMover9001));
    }
}
