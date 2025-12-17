package com.aayadi.tennis;

import java.util.Objects;

public class TennisGame {

    public static void play(String sequence) {
        Objects.requireNonNull(sequence, "Input sequence must not be null");

        GameState game = new GameState();

        for (char c : sequence.toCharArray()) {
            Player winner = Player.fromChar(c);
            game.pointWonBy(winner);
            game.printScore();

            if (game.isFinished()) {
                game.printWinner();
                return;
            }
        }
    }
}
