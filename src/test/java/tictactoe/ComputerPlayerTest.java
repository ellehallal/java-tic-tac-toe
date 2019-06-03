package tictactoe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ComputerPlayerTest {

    @Test
    void returnsAnAvailableMove() {
        var squares = Arrays.asList("1", "2", "3", "4", "x", "x", "x", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var computerPlayer = new ComputerPlayer(display, "o");

        var availableMoves = board.availableSquares("x", "o");
        var move = computerPlayer.chooseMove(board, "x");

        assertFalse(availableMoves.contains(move));
    }


    @Test
    void returnsAvailableSquare9WhenOtherSquaresAreTaken() {
        var squares = Arrays.asList("x", "x", "x", "x", "x", "x", "x", "x", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var computerPlayer = new ComputerPlayer(display, "o");

        var move = computerPlayer.chooseMove(board, "x");

        assertEquals(9, move);
    }

    @Test
    void returnsPlayersMark() {
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var computerPlayer = new ComputerPlayer(display, "o");

        var playersMark = computerPlayer.getMark();

        assertEquals("o", playersMark);
    }
}