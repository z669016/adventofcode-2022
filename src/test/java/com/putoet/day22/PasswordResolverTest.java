package com.putoet.day22;

import com.putoet.grid.GridUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordResolverTest {

    @Test
    void resolve() {
        final var grid = GridUtils.of(Day22.boardInput());
        final var board = new StrangeBoard(grid);
        assertEquals(6032, PasswordResolver.resolve(board, new BoardStrategy(grid), Day22.pathInput()));
    }
}