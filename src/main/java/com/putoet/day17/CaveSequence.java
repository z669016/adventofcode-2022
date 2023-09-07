package com.putoet.day17;

import org.jetbrains.annotations.NotNull;

import java.util.*;

class CaveSequence extends Cave{
    private final int from;
    private final int size;

    private Sequence sequence;

    public record Sequence(int start, List<Integer> list) {}

    public CaveSequence(@NotNull Push push, int from, int size) {
        super(push);
        this.from = from;
        this.size = size;
    }

    public Sequence sequence() {
        return sequence;
    }

    @Override
    public void run() {
        final var list = new ArrayList<Integer>();
        var start = -1;

        for (var i = 0; i < from + size; i++) {
            var value = drop(rocks().get(), push());
            if (i == from - 1)
                start = highestRock();

            if (i >= from)
                list.add(value);
        }

        sequence = new Sequence(start, list);
    }
}
