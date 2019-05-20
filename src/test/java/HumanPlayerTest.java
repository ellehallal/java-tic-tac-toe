import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    @Test
    void returnsMoveWhenValid() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var input = "1";
        var scanner = new Scanner(input);
        var humanPlayer = new HumanPlayer(display, "x", scanner);

        assertEquals(humanPlayer.chooseMove(board, "o"), 1);

    }

    @Test
    void returnsSecondInputWhenFirstIsTakenOnTheBoard() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("x", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);

        var simulatedInput = "1" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        var scanner = new Scanner(System.in);
        var humanPlayer = new HumanPlayer(display, "x", scanner);

        assertEquals(humanPlayer.chooseMove(board, "o"), 3);

    }

    @Test
    void returnsSecondInputWhenFirstIsNotWithinGridSize() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("x", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);

        var simulatedInput = "10" + System.getProperty("line.separator")
                + "9" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        var scanner = new Scanner(System.in);
        var humanPlayer = new HumanPlayer(display, "x", scanner);

        assertEquals(humanPlayer.chooseMove(board, "o"), 9);

    }

    @Test
    void returnsSecondInputWhenFirstIsNotANumber() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("x", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);

        var simulatedUserInput = "c" + System.getProperty("line.separator")
                + "7" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        var scanner = new Scanner(System.in);
        var humanPlayer = new HumanPlayer(display, "x", scanner);

        assertEquals(humanPlayer.chooseMove(board, "o"), 7);

    }

}