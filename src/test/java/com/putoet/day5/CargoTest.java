package com.putoet.day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CargoTest {

    @Test
    void top() {
        final Crates one = new Crates("MZP");
        final Crates two = new Crates();
        final Crates three = new Crates("ABCD");
        final Cargo cargo = new Cargo(List.of(one, two, three));
        assertEquals("P D", cargo.top());
    }
}