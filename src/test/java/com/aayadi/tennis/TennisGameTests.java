package com.aayadi.tennis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TennisGameTests {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void example_given_in_the_kata() {
        TennisGame.play("ABABAA");

        assertEquals(
                """
                Player A : 15 / Player B : 0
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A wins the game
                """.replaceAll("\\R", "\n"),
                output.toString().replaceAll("\\R", "\n")
        );
    }

    @Test
    void player_a_wins_without_deuce() {
        TennisGame.play("AAAA");

        assertEquals(
                """
                Player A : 15 / Player B : 0
                Player A : 30 / Player B : 0
                Player A : 40 / Player B : 0
                Player A wins the game
                """.replaceAll("\\R", "\n"),
                output.toString().replaceAll("\\R", "\n")
        );
    }

    @Test
    void player_b_wins_without_deuce() {
        TennisGame.play("BBBB");

        assertEquals(
                """
                        Player A : 0 / Player B : 15
                        Player A : 0 / Player B : 30
                        Player A : 0 / Player B : 40
                        Player B wins the game
                        """.replaceAll("\\R", "\n"),
                output.toString().replaceAll("\\R", "\n")
        );
    }

    @Test
    void deuce_and_advantage_then_back_to_deuce() {
        TennisGame.play("ABABABAB");

        assertEquals(
                """
                Player A : 15 / Player B : 0
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A : 40 / Player B : 40
                Deuce
                Advantage Player A
                Player A : 40 / Player B : 40
                Deuce
                """.replaceAll("\\R", "\n"),
                output.toString().replaceAll("\\R", "\n")
        );
    }

    @Test
    void advantage_then_win() {
        TennisGame.play("ABABABABBB");

        assertEquals(
                """
                Player A : 15 / Player B : 0
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A : 40 / Player B : 40
                Deuce
                Advantage Player A
                Player A : 40 / Player B : 40
                Deuce
                Advantage Player B
                Player B wins the game
                """.replaceAll("\\R", "\n"),
                output.toString().replaceAll("\\R", "\n")
        );
    }

    @Test
    void invalid_character_throws_exception() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> TennisGame.play("ABX"));

        assertTrue(exception.getMessage().contains("Invalid character"));
    }
}
