import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    @Test
    void displaysWinnerMessageWhenAPlayerHasWon() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("x", "x", "x", "4", "5", "6", "7", "o", "o");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);
        var controller = new Controller(game, display);
        var expectedOutput = "x is the winner!\n";

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    void displaysTieMessageWhenGameIsATie() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("x", "o", "x", "x", "o", "o", "o", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);
        var controller = new Controller(game, display);
        var expectedOutput = "It's a tie!\n";

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput));
    }

}