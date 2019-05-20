import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {

    @Test
    void displaysTheGrid() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        var squaresOutput =
                "1 | 2 | 3\n---------\n4 | 5 | 6\n---------\n7 | 8 | 9\n";
        var squares = new ArrayList<>(Arrays.asList
                ("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        var grid = new Grid(squares);

        display.grid(grid);
        assertEquals(squaresOutput, output.toString());
    }

    @Test
    void displaysMakeMoveMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.make_move();
        assertEquals("Choose a position from 1 - 9:\n", output.toString());
    }

    @Test
    void displaysInvalidMoveMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var expected = "Invalid move. Please try again.\n";

        display.invalid_move();
        assertEquals(expected, output.toString());
    }
}