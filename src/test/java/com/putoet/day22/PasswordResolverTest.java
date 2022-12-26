package com.putoet.day22;

import com.putoet.grid.GridUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordResolverTest {

    @Test
    void resolve() {
        final char[][] grid = GridUtils.of(Day22.boardInput());
        final StrangeBoard board = new StrangeBoard(grid);
        assertEquals(6032, PasswordResolver.resolve(board, new BoardStrategy(grid), Day22.pathInput()));
    }
}