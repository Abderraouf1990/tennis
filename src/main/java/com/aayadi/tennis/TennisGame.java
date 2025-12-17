package com.aayadi.tennis;

public class TennisGame {

    public static void getScore(Player player1, Player player2) {
        if (player1.score < 30 ) {
            player1.score += 15;
        } else if (player1.score == 30 ) {
            player1.score = 40;
        } else if (player1.score == 40 && player2.score < 40) {
            player1.hasWin = true;
        } else if (player1.score == 40 && player2.score == 40) {
            player1.score = 50; // Advantage
        } else if (player1.score == 50) {
            player1.hasWin = true;
        } else if (player2.score == 50) {
            player2.score = 40; // Back to deuce
        }
    }
    public static void play(String result) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        for (char point : result.toCharArray()) {
            if (point == 'A') {
                getScore(player1, player2);
            } else if (point == 'B') {
                getScore(player2, player1);
            } else {
                throw new IllegalArgumentException("Invalid character: " + point);
            }

            showResult(player1, player2);

            if (player1.hasWin || player2.hasWin) {
                break;
            }
        }


    }

    private static void showResult(Player player1, Player player2) {

        if (player1.hasWin) {
            System.out.println("Player A wins the game");
        } else if (player2.hasWin) {
            System.out.println("Player B wins the game");
        } else if (player1.score == 50) {
            System.out.println("Advantage Player A");
        } else if (player2.score == 50) {
            System.out.println("Advantage Player B");
        } else {
            System.out.println("Player A : " + player1.score + " / Player B : " + player2.score);
        }

        if (player1.score == 40 && player2.score == 40) {
            System.out.println("Deuce");
        }

    }
}
