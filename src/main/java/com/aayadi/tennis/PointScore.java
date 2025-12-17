package com.aayadi.tennis;

public enum PointScore {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40");

    private final String score;

    PointScore(String score) {
        this.score = score;
    }

    PointScore next() {
        return switch (this) {
            case LOVE -> FIFTEEN;
            case FIFTEEN -> THIRTY;
            case THIRTY -> FORTY;
            case FORTY -> throw new IllegalStateException("No score after FORTY");
        };
    }

    String show() {
        return score;
    }
}
