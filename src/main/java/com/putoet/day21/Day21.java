package com.putoet.day21;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.OptionalLong;
import java.util.function.Supplier;

public class Day21 extends Day {
    private final List<String> input = ResourceLines.list("/day21.txt");

    public Day21(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        final var day = new Day21(args);
        day.challenge();
    }

    @Override
    public void part1() {
        final var values = Values.from(input);
        System.out.println("The monkey named root will yell " + values.get(Values.ROOT));
    }

    @Override
    public void part2() {
        final var values = Values.from(input);
        values.setOperation(Values.HUMN, new Value(Values.HUMN));

        final var root = (Operation) values.getOperation(Values.ROOT);
        values.setOperation(Values.ROOT, new Operation(values, root.left(), Operator.EQUALS, root.right()));
        System.out.println("The value of humn is " + findHumn(values, 1, values.getOperation(Values.ROOT)));
    }

    public static String findHumn(Values values, long parentValue, Supplier<String> node) {
        if (node instanceof Value)
            return String.valueOf(parentValue);

        final Operation operation = (Operation) node;
        final OptionalLong left = Values.tryParse(values.get(operation.left()));
        final OptionalLong right = Values.tryParse(values.get(operation.right()));

        if (left.isPresent()) {
            parentValue = switch (operation.operator()) {
                case PLUS -> parentValue - left.orElseThrow();
                case MINUS -> left.orElseThrow() - parentValue;
                case DIVIDE -> left.orElseThrow() / parentValue;
                case PRODUCT -> parentValue / left.orElseThrow();
                case EQUALS -> left.orElseThrow();
            };
        } else {
            parentValue = switch (operation.operator()) {
                case PLUS -> parentValue - right.orElseThrow();
                case MINUS -> parentValue + right.orElseThrow();
                case DIVIDE -> parentValue * right.orElseThrow();
                case PRODUCT -> parentValue / right.orElseThrow();
                case EQUALS -> right.orElseThrow();
            };
        }


        node = left.isPresent() ? values.getOperation(operation.right()) : values.getOperation(operation.left());
        return findHumn(values, parentValue, node);
    }
}
