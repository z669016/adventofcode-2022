package com.putoet.day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CargoTest {

    @Test
    void top() {
        final var one = new Crates("MZP");
        final var two = new Crates();
        final var three = new Crates("ABCD");
        final var cargo = new Cargo(List.of(one, two, three));
        assertEquals("P D", cargo.top());
    }
}