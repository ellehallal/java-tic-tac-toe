import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayTest {

    @Test
    void displaysTheGrid() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        var squaresOutput =
            "1 | 2 | 3\n---------\n4 | 5 | 6\n---------\n7 | 8 | 9\n";
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        display.showGrid(grid);

        assertEquals(squaresOutput, output.toString());
    }

    @Test
    void displaysMakeMoveMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.makeMoveMessage();

        assertEquals("Choose a position from 1 - 9:\n", output.toString());
    }

    @Test
    void displaysInvalidMoveMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.invalidMoveMessage("x");

        assertEquals("Invalid move. Please try again, x.\n", output.toString());
    }

    @Test
    void displaysTieMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.outcomeMessage("tie");

        assertEquals("It's a tie!\n", output.toString());
    }

    @Test
    void displaysWinnerMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var winnersMark = "x";

        display.outcomeMessage(winnersMark);

        assertEquals("x is the winner!\n", output.toString());
    }

    @Test
    void displaysPlayerTurnMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.playerTurnMessage("x");

        assertEquals("x, it's your turn.\n", output.toString());
    }
}