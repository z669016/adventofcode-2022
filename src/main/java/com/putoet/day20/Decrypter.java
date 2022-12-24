package com.putoet.day20;

import java.util.List;
import java.util.stream.Stream;

public class Decrypter {
    private final Value[] values;
    private final int zero;

    private Decrypter(Value[] values) {
        this.values = values;
        this.zero = findZero(values);
    }

    private static int findZero(Value[] values) {
        for (int i = 0; i < values.length; i++)
            if (values[i].get() == 0)
                return i;

        throw new IllegalStateException("No 0 in the list!");
    }

    public static Decrypter from(List<Integer> input) {
        return from(input, 1L);
    }

    public static Decrypter from(List<Integer> input, long key) {
        final Value[] values = new Value[input.size()];
        for (int i = 0; i < input.size(); i++) {
            values[i] = new Value(i, input.get(i) * key);
        }

        for (int i = 0; i < values.length; i++) {
            values[i].next(i < values.length - 1 ? values[i+1] : values[0]);
            values[i].prev(i > 0 ? values[i-1] : values[values.length - 1]);
        }

        return new Decrypter(values);
    }

    public void mix() {
        mix(1);
    }

    public void mix(int times) {
        while ( times-- > 0) {
            for (final Value current : values) {
                if (current.get() == 0)
                    continue;

                long move = current.get() % (values.length - 1);
                for (int m = 0; m < Math.abs(move); m++) {
                    final Value next = current.next();
                    final Value prev = current.prev();

                    if (move > 0) {
                        current.next(next.next());
                        current.prev(next);

                        next.next().prev(current);

                        next.next(current);
                        next.prev(prev);

                        prev.next(next);
                    } else {
                        current.next(prev);
                        current.prev(prev.prev());

                        prev.prev().next(current);

                        prev.next(next);
                        prev.prev(current);

                        next.prev(prev);
                    }
                }
            }
        }
    }

    public List<Long> groveCoordinates() {
        return Stream.of(1000, 2000, 3000)
                .map(i -> i % values.length)
                .map(offset -> {
                    Value current = values[zero];
                    while (offset-- > 0) {
                        current = current.next();
                    }
                    return current;
                })
                .map(Value::get)
                .toList();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(values[0].get());

        Value current = values[0];
        while (!current.next().equals(values[0])) {
            current = current.next();
            sb.append(",").append(current.get());
        }

        return sb.toString();
    }
}
