package tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidPlayerMarksTest {
    @Test
    void returnsRegexForDigitFromEnum() {
        var mark = InvalidPlayerMarks.DIGIT.mark;

        assertEquals("\\d+", mark);
    }

    @Test
    void returnsWhitespaceFromEnum() {
        var mark = InvalidPlayerMarks.WHITESPACE.mark;

        assertEquals(" ", mark);
    }

    @Test
    void returnsEmptyStringFromEnum() {
        var mark = InvalidPlayerMarks.EMPTY.mark;

        assertEquals("", mark);
    }

}