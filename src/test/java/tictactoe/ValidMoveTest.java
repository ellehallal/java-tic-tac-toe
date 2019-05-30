package tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidMoveTest {
    @Test
    void returnsRegexForDigitFromEnum() {
        var move = ValidMove.DIGIT.move;

        assertEquals("\\d+", move);
    }

}