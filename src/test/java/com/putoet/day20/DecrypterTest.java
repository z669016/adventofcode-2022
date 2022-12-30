package com.putoet.day20;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DecrypterTest {

    @Test
    void mix() {
        final Decrypter decrypter = Decrypter.from(ResourceLines.list("/day20.txt", Integer::parseInt));
        decrypter.mix();
    }

    @Test
    void groveCoordinates() {
        final Decrypter decrypter = Decrypter.from(ResourceLines.list("/day20.txt", Integer::parseInt));
        decrypter.mix();
        assertEquals(List.of(4L, -3L, 2L), decrypter.groveCoordinates());
    }

    @Test
    void groveCoordinates2() {
        final Decrypter decrypter = Decrypter.from(ResourceLines.list("/day20.txt", Integer::parseInt), 811589153L);
        decrypter.mix(10);
        assertEquals(List.of(811589153L, 2434767459L, -1623178306L), decrypter.groveCoordinates());
    }
}