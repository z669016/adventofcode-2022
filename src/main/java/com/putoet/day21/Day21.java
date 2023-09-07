package com.putoet.day21;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;
import java.util.function.Supplier;

public class Day21 {
    public static void main(String[] args) {
        final List<String> input = ResourceLines.list("/day21.txt");

        Timer.run(() -> part1(input));
        Timer.run(() -> part2(input));
    }

    static void part1(List<String> input) {
        final var values = Values.of(input);
        System.out.println("The monkey named root will yell " + values.get(Values.ROOT));
    }

    static void part2(List<String> input) {
        final var values = Values.of(input);
        values.setOperation(Values.HUMN, new Value(Values.HUMN));

        final var root = (Operation) values.getOperation(Values.ROOT);
        values.setOperation(Values.ROOT, new Operation(values, root.left(), Operator.EQUALS, root.right()));
        System.out.println("The value of humn is " + findHumn(values, 1, values.getOperation(Values.ROOT)));
    }

    static String findHumn(Values values, long parentValue, Supplier<String> node) {
        if (node instanceof Value)
            return String.valueOf(parentValue);

        final var operation = (Operation) node;
        final var left = Values.tryParse(values.get(operation.left()));
        final var right = Values.tryParse(values.get(operation.right()));

        if (left.isPresent()) {
            parentValue = switch (operation.operator()) {
                case PLUS -> parentValue - left.getAsLong();
                case MINUS -> left.getAsLong() - parentValue;
                case DIVIDE -> left.getAsLong() / parentValue;
                case PRODUCT -> parentValue / left.getAsLong();
                case EQUALS -> left.getAsLong();
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
