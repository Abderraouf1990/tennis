package com.aayadi.tennis;

public enum Player {
    A, B;

    static Player fromChar(char c) {
        return switch (c) {
            case 'A' -> A;
            case 'B' -> B;
            default -> throw new IllegalArgumentException(
                    "Invalid character: " + c + " (expected A or B)"
            );
        };
    }
}
