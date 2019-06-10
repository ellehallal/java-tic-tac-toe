package tictactoe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardFactoryTest {
    @Test
    void createsAnInstanceOfBoardWithSquares() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

        var newBoard = BoardFactory.newBoard(squares);

        assertThat(newBoard).isInstanceOf(Board.class);
        assertEquals(squares, newBoard.getGrid().getSquares());
    }
}