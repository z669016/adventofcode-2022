package com.putoet.day2;

import org.jetbrains.annotations.NotNull;

record Game(@NotNull RPS player1, @NotNull RPS player2) {
    public int score() {
        if (player1 == player2)
            return 3 + player2.score();

        if (player2 == player1.losesFrom())
            return 6 + player2.score();

        return player2().score();
    }

    public static Game of(@NotNull String line) {
        final var split = line.trim().split(" ");
        assert split.length == 2;

        return new Game(RPS.of(split[0]), RPS.of(split[1]));
    }

    public static Game of2(@NotNull String line) {
        final var split = line.trim().split(" ");
        assert split.length == 2;

        final RPS player1 = RPS.of(split[0]);
        final RPS player2 = RPS.of2(split[1], player1);

        return new Game(player1, player2);
    }
}
