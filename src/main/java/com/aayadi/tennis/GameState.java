package com.aayadi.tennis;

public class GameState {
    private PointScore scoreA = PointScore.LOVE;
    private PointScore scoreB = PointScore.LOVE;

    private Player advantage;
    private Player winner;

    void pointWonBy(Player player) {
        if (winner != null) {
            return;
        }

        if (isDeuce()) {
            handleDeuce(player);
            return;
        }

        if (hasAdvantage()) {
            handleAdvantage(player);
            return;
        }

        incrementScore(player);
    }

    private void incrementScore(Player player) {
        PointScore score = getScore(player);

        if (score == PointScore.FORTY) {
            winner = player;
        } else {
            setScore(player, score.next());
        }
    }

    private void handleDeuce(Player player) {
        advantage = player;
    }

    private void handleAdvantage(Player player) {
        if (advantage == player) {
            winner = player;
        } else {
            advantage = null; // back to deuce
        }
    }

    boolean isDeuce() {
        return scoreA == PointScore.FORTY
                && scoreB == PointScore.FORTY
                && advantage == null;
    }

    boolean hasAdvantage() {
        return advantage != null;
    }

    boolean isFinished() {
        return winner != null;
    }

    void printScore() {
        if (winner != null) {
            return;
        }

        if (isDeuce()) {
            System.out.printf(
                    "Player A : %s / Player B : %s%n",
                    scoreA.show(),
                    scoreB.show()
            );
            System.out.println("Deuce");
            return;
        }

        if (hasAdvantage()) {
            System.out.println("Advantage Player " + advantage);
            return;
        }

        System.out.printf(
                "Player A : %s / Player B : %s%n",
                scoreA.show(),
                scoreB.show()
        );
    }

    void printWinner() {
        System.out.println("Player " + winner + " wins the game");
    }

    private PointScore getScore(Player player) {
        return player == Player.A ? scoreA : scoreB;
    }

    private void setScore(Player player, PointScore score) {
        if (player == Player.A) {
            scoreA = score;
        } else {
            scoreB = score;
        }
    }
}
