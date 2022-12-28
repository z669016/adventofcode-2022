package com.putoet.day25;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnafuTest {

    @Test
    void asDecimal() {
        assertEquals(1747, Snafu.asDecimal("1=-0-2"));
        assertEquals(906, Snafu.asDecimal("12111"));
        assertEquals(198, Snafu.asDecimal("2=0="));
        assertEquals(11, Snafu.asDecimal("21"));
        assertEquals(201, Snafu.asDecimal("2=01"));
        assertEquals(31, Snafu.asDecimal("111"));
        assertEquals(1257, Snafu.asDecimal("20012"));
        assertEquals(32, Snafu.asDecimal("112"));
        assertEquals(353, Snafu.asDecimal("1=-1="));
        assertEquals(107, Snafu.asDecimal("1-12"));
        assertEquals(7, Snafu.asDecimal("12"));
        assertEquals(3, Snafu.asDecimal("1="));
        assertEquals(37, Snafu.asDecimal("122"));
    }

    @Test
    void asSnafu() {
        assertEquals("1=-0-2", Snafu.from(1747).toString());
        assertEquals("12111", Snafu.from(906).toString());
        assertEquals("2=0=", Snafu.from(198).toString());
        assertEquals("21", Snafu.from(11).toString());
        assertEquals("2=01", Snafu.from(201).toString());
        assertEquals("111", Snafu.from(31).toString());
        assertEquals("20012", Snafu.from(1257).toString());
        assertEquals("112", Snafu.from(32).toString());
        assertEquals("1=-1=", Snafu.from(353).toString());
        assertEquals("1-12", Snafu.from(107).toString());
        assertEquals("12", Snafu.from(7).toString());
        assertEquals("1=", Snafu.from(3).toString());
        assertEquals("122", Snafu.from(37).toString());
    }

    @Test
    void sum() {
        final var sum = ResourceLines.stream("/day25.txt")
                .map(Snafu::new)
                .mapToLong(Snafu::asDecimal)
                .sum();

        assertEquals("2=-1=0", Snafu.from(sum).toString());
    }
}