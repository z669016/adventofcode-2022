package com.putoet.day11;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

class Game {
    public static void rounds(@NotNull List<Monkey> monkeys, int count, @NotNull Function<Long,Long> bored) {
        assert count > 0;

        while (count-- > 0) {
            round(monkeys, bored);
        }
    }

    private static void round(List<Monkey> monkeys, Function<Long,Long> bored) {
        for (var monkey : monkeys) {
            monkey.round(bored);
        }
    }

    public static long monkeyBusiness(@NotNull List<Monkey> monkeys) {
        assert monkeys.size() > 1;

        final var ordered = monkeys.stream()
                .sorted(Comparator.comparing(Monkey::inspected).reversed())
                .toList();

        return ordered.get(0).inspected() * ordered.get(1).inspected();
    }

    public static long lcm(@NotNull List<Monkey> monkeys) {
        var lcm = 1L;
        for (var monkey : monkeys)
            lcm = ArithmeticUtils.lcm(lcm, monkey.divisor());

        return lcm;
    }

    public static List<Monkey> monkeys(@NotNull List<String> input) {
        final var monkeys = new ArrayList<Monkey>();
        final var nextTrue = new ArrayList<Integer>();
        final var nextFalse = new ArrayList<Integer>();

//        Monkey 0:
//          Starting items: 79, 98
//          Operation: new = old *|+ 19
//          Test: divisible by 23
//          If true: throw to monkey 2
//          If false: throw to monkey 3

        for (var i = 0; i < input.size(); i += 7) {
            final var monkey = new Monkey(
                    parseId(input.get(i)),
                    parseItems(input.get(i + 1)),
                    parseOperation(input.get(i + 2)),
                    parseTest(input.get(i + 3))
            );
            monkeys.add(monkey);
            nextTrue.add(parseIfTrue(input.get(i + 4)));
            nextFalse.add(parseIfFalse(input.get(i + 5)));
        }

        for (var i = 0; i < monkeys.size(); i++) {
            monkeys.get(i)
                    .next(true, monkeys.get(nextTrue.get(i)))
                    .next(false, monkeys.get(nextFalse.get(i)));
        }

        return monkeys;
    }

    private static final Pattern ID_PATTERN = Pattern.compile("Monkey (\\d+):");
    public static int parseId(@NotNull String line) {
        final var matcher = ID_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid id line: " + line);

        return Integer.parseInt(matcher.group(1));
    }

    public static List<Long> parseItems(@NotNull String line) {
        line = line.trim();
        if (!line.startsWith("Starting items: "))
            throw new IllegalArgumentException("Invalid items line: " + line);

        final var split = line.substring(16).split(", ");
        return Arrays.stream(split).map(Long::parseLong).toList();
    }

    private static final Pattern OPERATION_PATTERN = Pattern.compile("Operation: new = old ([+|*]) (\\d+|old)");
    public static Function<Long, Long> parseOperation(@NotNull String line) {
        line = line.trim();
        final var matcher = OPERATION_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid operation line: " + line);

        return "+".equals(matcher.group(1)) ?
                value -> value + ("old".equals(matcher.group(2)) ? value : Long.parseLong(matcher.group(2))):
                value -> value * ("old".equals(matcher.group(2)) ? value : Long.parseLong(matcher.group(2)));
    }

    private static final Pattern TEST_PATTERN = Pattern.compile("Test: divisible by (\\d+)");
    public static int parseTest(@NotNull String line) {
        line = line.trim();
        final var matcher = TEST_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid test line: " + line);

        return Integer.parseInt(matcher.group(1));
    }

    private static final Pattern IFTRUE_PATTERN = Pattern.compile("If true: throw to monkey (\\d+)");
    public static int parseIfTrue(@NotNull String line) {
        line = line.trim();
        final var matcher = IFTRUE_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid if-true line: " + line);

        return Integer.parseInt(matcher.group(1));
    }

    private static final Pattern IFFALSE_PATTERN = Pattern.compile("If false: throw to monkey (\\d+)");
    public static int parseIfFalse(@NotNull String line) {
        line = line.trim();
        final var matcher = IFFALSE_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid if-false line: " + line);

        return Integer.parseInt(matcher.group(1));
    }
}
