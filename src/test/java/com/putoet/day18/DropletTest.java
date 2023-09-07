package com.putoet.day18;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DropletTest {
    private static final List<String> input = ResourceLines.list("/day18.txt");

    @Test
    void size() {
        assertEquals(13, Droplet.of(input).size());
    }

    @Test
    void connected() {
        assertEquals(10, Droplet.of(List.of("1,1,1", "2,1,1")).connected());
        assertEquals(64, Droplet.of(input).connected());
    }

    @Test
    void exterior() {
        assertEquals(58, Droplet.of(input).exterior());
    }
}