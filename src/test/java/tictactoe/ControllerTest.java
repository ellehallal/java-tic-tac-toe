package tictactoe;

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

        var simulatedInput = "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(inputValidator, playerFactory, board);
        var database = new Database();
        var connection = database.connect();
        var gameSaver = new GameSaver(connection);

        var controller = new Controller(gameFactory, display, gameSaver, inputValidator);
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

        var simulatedInput = "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "computer" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(inputValidator, playerFactory, board);
        var database = new Database();
        var connection = database.connect();
        var gameSaver = new GameSaver(connection);

        var controller = new Controller(gameFactory, display, gameSaver, inputValidator);
        var expectedOutput = "It's a tie!\n";

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    void displaysSavedGameMessagesWhenUserEntersSave() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var simulatedInput = "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator")
                + "save" + System.getProperty("line.separator")
                + "cool game" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(inputValidator, playerFactory, board);
        var database = new Database();
        var connection = database.connect();
        var gameSaver = new GameSaver(connection);

        var controller = new Controller(gameFactory, display, gameSaver, inputValidator);
        var expectedOutput1 = "Please wait, saving 'cool game'...\n";
        var expectedOutput2 = "Your game is saved!\n";
        System.out.println(output.toString());

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput1));
        assertTrue(output.toString().contains(expectedOutput2));
    }

}