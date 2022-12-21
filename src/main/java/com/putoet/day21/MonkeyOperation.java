package com.putoet.day21;

import java.util.Optional;
import java.util.function.Supplier;

public interface MonkeyOperation extends Supplier<Long> {
    default Optional<String> left() { return Optional.empty(); }
    default Optional<String> right() { return Optional.empty(); }
}
