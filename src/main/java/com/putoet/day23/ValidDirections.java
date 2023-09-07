package com.putoet.day23;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

class ValidDirections implements Supplier<List<ValidDirection>> {
    private final List<ValidDirection> order;

    public ValidDirections() {
        order = new LinkedList<>();
        order.add(ValidDirection.EAST);
        order.add(ValidDirection.NORTH);
        order.add(ValidDirection.SOUTH);
        order.add(ValidDirection.WEST);
    }

    @Override
    public List<ValidDirection> get() {
        order.add(order.remove(0));
        return order;
    }
}
