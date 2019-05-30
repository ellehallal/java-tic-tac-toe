package tictactoe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;

class UnbeatableComputerPlayerTest {

    @Test
    void returnsAnAvailableMoveAsAString() {
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var minimax = new Minimax();
        var uComputerPlayer = new UnbeatableComputerPlayer(display, "o", minimax);

        var availableMoves = board.availableSquares("x", "o");
        var move = uComputerPlayer.chooseMove(board, "x");

        assertFalse(availableMoves.contains(move));
    }
}