package com.putoet.day2;

public record Game(RPS player1, RPS player2) {
    public Game {
        assert player1 != null;
        assert player2 != null;
    }

    public int score() {
        if (player1 == player2)
            return 3 + player2.score();

        if (player2 == player1.losesFrom())
            return 6 + player2.score();

        return player2().score();
    }

    public static Game of(String line) {
        assert line != null;

        final String[] split = line.trim().split(" ");
        assert split.length == 2;

        return new Game(RPS.of(split[0]), RPS.of(split[1]));
    }

    public static Game of2(String line) {
        assert line != null;

        final String[] split = line.trim().split(" ");
        assert split.length == 2;

        final RPS player1 = RPS.of(split[0]);
        final RPS player2 = RPS.of2(split[1], player1);

        return new Game(player1, player2);
    }

}
