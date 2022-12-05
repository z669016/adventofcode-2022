package com.putoet.day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CargoTest {

    @Test
    void move() {
        final CrateStack one = new CrateStack("MZP");
        final CrateStack two = new CrateStack("ABCD");
        final Cargo cargo = new Cargo(List.of(one, two));
        cargo.move(1, 2, 2);
        assertEquals("M\nABCDPZ", cargo.toString());
    }

    @Test
    void top() {
        final CrateStack one = new CrateStack("MZP");
        final CrateStack two = new CrateStack();
        final CrateStack three = new CrateStack("ABCD");
        final Cargo cargo = new Cargo(List.of(one, two, three));
        assertEquals("P D", cargo.top());
    }
}