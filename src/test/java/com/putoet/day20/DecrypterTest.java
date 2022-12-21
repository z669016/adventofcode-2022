package com.putoet.day20;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DecrypterTest {
    private static final List<String> list = ResourceLines.list("/day20.txt");
    private static final int[] input = list.stream()
            .mapToInt(Integer::parseInt)
            .toArray();

    @Test
    void mix() {
        final Decrypter decrypter = new Decrypter(input);
        final int[] expected = {1, 2, -3, 4, 0, 3, -2};
        assertArrayEquals(expected, decrypter.mix().numbers());
    }

    @Test
    void groveCoordinates() {
        final Decrypter decrypter = new Decrypter(input);
        assertEquals(List.of(4, -3, 2), decrypter.mix().groveCoordinates());
    }
}