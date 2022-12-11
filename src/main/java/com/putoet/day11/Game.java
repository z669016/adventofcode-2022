package com.putoet.day11;

import com.putoet.math.Factors;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    public static void rounds(List<Monkey> monkeys, int count, Function<Long,Long> bored) {
        assert monkeys != null;
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

    public static long monkeyBusiness(List<Monkey> monkeys) {
        assert monkeys != null;
        assert monkeys.size() > 1;

        List<Monkey> ordered = monkeys.stream()
                .sorted(Comparator.comparing(Monkey::inspected).reversed())
                .toList();

        return ordered.get(0).inspected() * ordered.get(1).inspected();
    }

    public static long lcm(List<Monkey> monkeys) {
        long lcm = 1L;
        for (var monkey : monkeys)
            lcm = Factors.lcm(lcm, monkey.divisor());

        return lcm;
    }

    public static List<Monkey> monkeys(List<String> input) {
        final List<Monkey> monkeys = new ArrayList<>();
        final List<Pair<Integer, Integer>> next = new ArrayList<>();

//        Monkey 0:
//          Starting items: 79, 98
//          Operation: new = old *|+ 19
//          Test: divisible by 23
//          If true: throw to monkey 2
//          If false: throw to monkey 3

        for (var i = 0; i < input.size(); i += 7) {
            final Monkey monkey = new Monkey(
                    parseId(input.get(i)),
                    parseItems(input.get(i + 1)),
                    parseOperation(input.get(i + 2)),
                    parseTest(input.get(i + 3))
            );
            monkeys.add(monkey);
            next.add(Pair.with(
                    parseIfTrue(input.get(i + 4)),
                    parseIfFalse(input.get(i + 5))
            ));
        }

        for (var i = 0; i < monkeys.size(); i++) {
            var pair = next.get(i);
            monkeys.get(i)
                    .next(true, monkeys.get(pair.getValue0()))
                    .next(false, monkeys.get(pair.getValue1()));
        }

        return monkeys;
    }

    private static final Pattern ID_PATTERN = Pattern.compile("Monkey (\\d+):");

    public static int parseId(String line) {
        assert line != null;

        final Matcher matcher = ID_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid id line: " + line);

        return Integer.parseInt(matcher.group(1));
    }

    public static List<Long> parseItems(String line) {
        assert line != null;

        line = line.trim();
        if (!line.startsWith("Starting items: "))
            throw new IllegalArgumentException("Invalid items line: " + line);

        final String[] split = line.substring(16).split(", ");
        return Arrays.stream(split).map(Long::parseLong).toList();
    }

    private static final Pattern OPERATION_PATTERN = Pattern.compile("Operation: new = old ([+|*]) (\\d+|old)");

    public static Function<Long, Long> parseOperation(String line) {
        assert line != null;

        line = line.trim();
        final Matcher matcher = OPERATION_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid operation line: " + line);

        return "+".equals(matcher.group(1)) ?
                value -> value + ("old".equals(matcher.group(2)) ? value : Long.parseLong(matcher.group(2))):
                value -> value * ("old".equals(matcher.group(2)) ? value : Long.parseLong(matcher.group(2)));
    }

    private static final Pattern TEST_PATTERN = Pattern.compile("Test: divisible by (\\d+)");

    public static int parseTest(String line) {
        assert line != null;

        line = line.trim();
        final Matcher matcher = TEST_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid test line: " + line);

        return Integer.parseInt(matcher.group(1));
    }

    private static final Pattern IFTRUE_PATTERN = Pattern.compile("If true: throw to monkey (\\d+)");

    public static int parseIfTrue(String line) {
        assert line != null;

        line = line.trim();
        final Matcher matcher = IFTRUE_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid if-true line: " + line);

        return Integer.parseInt(matcher.group(1));
    }

    private static final Pattern IFFALSE_PATTERN = Pattern.compile("If false: throw to monkey (\\d+)");

    public static int parseIfFalse(String line) {
        assert line != null;

        line = line.trim();
        final Matcher matcher = IFFALSE_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid if-false line: " + line);

        return Integer.parseInt(matcher.group(1));
    }
}
