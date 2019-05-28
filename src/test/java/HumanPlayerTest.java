import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanPlayerTest {

    @Test
    void returnsMoveWhenValid() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var humanPlayer = new HumanPlayer(display, "x", inputValidator);

        var move = humanPlayer.chooseMove(board, "o");

        assertEquals(1, move);
    }

    @Test
    void returnsSecondInputWhenFirstIsTakenOnTheBoard() {
        var simulatedInput = "1" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        var scanner = new Scanner(System.in);
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var humanPlayer = new HumanPlayer(display, "x", inputValidator);

        var move = humanPlayer.chooseMove(board, "o");

        assertEquals(3, move);
    }

    @Test
    void returnsSecondInputWhenFirstIsNotWithinGridSize() {
        var simulatedInput = "10" + System.getProperty("line.separator")
                + "9" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        var scanner = new Scanner(System.in);
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var humanPlayer = new HumanPlayer(display, "x", inputValidator);

        var move = humanPlayer.chooseMove(board, "o");

        assertEquals(9, move);
    }

    @Test
    void returnsSecondInputWhenFirstIsNotANumber() {
        var simulatedUserInput = "c" + System.getProperty("line.separator")
                + "7" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        var scanner = new Scanner(System.in);
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var humanPlayer = new HumanPlayer(display, "x", inputValidator);

        var move = humanPlayer.chooseMove(board, "o");

        assertEquals(7, move);
    }
}