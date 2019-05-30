package tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameOutcomeTest {

    @Test
    void returnsTieFromEnum() {
        var gameOutcome = GameOutcome.TIE.type;

        assertEquals("TIE", gameOutcome);
    }
}