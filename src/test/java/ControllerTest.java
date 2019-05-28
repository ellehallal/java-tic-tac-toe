import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    @Test
    void displaysWinnerMessageWhenAPlayerHasWon() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("x", "x", "3", "4", "5", "6", "7", "o", "o");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var simulatedInput = "h" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "h" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var playerFactory = new PlayerFactory(display, inputValidator);
        var gameFactory = new GameFactory(inputValidator, playerFactory, board);

        var controller = new Controller(gameFactory, display);
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

        var simulatedInput = "h" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "c" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var playerFactory = new PlayerFactory(display, inputValidator);
        var gameFactory = new GameFactory(inputValidator, playerFactory, board);

        var controller = new Controller(gameFactory, display);
        var expectedOutput = "It's a tie!\n";

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput));
    }

}