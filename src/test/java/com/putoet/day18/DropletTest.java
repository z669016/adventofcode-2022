package com.putoet.day18;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DropletTest {
    private static final List<String> input = ResourceLines.list("/day18.txt");

    @Test
    void size() {
        assertEquals(13, Droplet.from(input).size());
    }

    @Test
    void connected() {
        assertEquals(10, Droplet.from(List.of("1,1,1", "2,1,1")).connected());
        assertEquals(64, Droplet.from(input).connected());
    }

    @Test
    void exterior() {
        assertEquals(58, Droplet.from(input).exterior());
    }
}