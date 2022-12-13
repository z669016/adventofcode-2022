package com.putoet.day13;

import com.putoet.day13.tokenizer.PacketTokenizer;
import com.putoet.day13.tokenizer.Token;

import java.util.*;

public record SignalPacket(List<Object> values) implements Comparable<SignalPacket> {
    public SignalPacket {
        assert values != null;
    }

    public SignalPacket(int value) {
        this(List.of(value));
    }

    public SignalPacket(Object... values) {
        this(Arrays.stream(values).toList());
    }

    public int size() {
        return values.size();
    }

    public Order test(SignalPacket second) {
        return test(this.values, second.values);
    }

    public static Order test(Object first, Object second) {

        if (first instanceof Integer firstInteger && second instanceof Integer secondInteger)
            return Order.from(firstInteger.compareTo(secondInteger));

        if (first instanceof Integer firstInteger && second instanceof List secondList)
            return test(List.of(firstInteger), secondList);

        if (first instanceof List firstList && second instanceof Integer secondInteger)
            return test(firstList, List.of(secondInteger));

        if (first instanceof List firstList && second instanceof List secondList) {
            var current = Order.CONTINUE;
            for (var i = 0; i < Math.min(firstList.size(), secondList.size()); i++) {
                current = test(firstList.get(i), secondList.get(i));
                if (current != Order.CONTINUE)
                    return current;
            }

            if (firstList.size() < secondList.size())
                return Order.IN_ORDER;
            else if (firstList.size() > secondList.size())
                return Order.NOT_IN_ORDER;
            else
                return Order.CONTINUE;
        }

        throw new IllegalStateException("Can't get here!");
    }

    public static SignalPacket from(String line) {
        final PacketTokenizer tokenizer = new PacketTokenizer(line);
        List<Object> values = new ArrayList<>();
        final Stack<List<Object>> stack = new Stack<>();

        while (tokenizer.hasNext()) {
            final Token token = tokenizer.next();
            switch (token.type()) {
                case VALUE -> values.add(token.valueAsInt());
                case START_LIST -> {
                    stack.push(values);
                    values = new ArrayList<>();
                }
                case END_LIST -> {
                    final List<Object> list = values;
                    values = stack.pop();
                    values.add(list);
                }
            }
        }

        return new SignalPacket((List<Object>) values.get(0));
    }

    @Override
    public int compareTo(SignalPacket second) {
        assert second != null;
        return this.test(second) == Order.IN_ORDER ? -1 : +1;
    }
}
