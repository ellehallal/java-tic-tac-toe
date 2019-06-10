package tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    @BeforeEach
    void clearTable() {
        var database = new Database();
        var query = "DELETE FROM saved_games";

        try {
            var conn = database.connect();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Table cleared successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void displaysWinnerMessageWhenAPlayerHasWon() throws SQLException {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        var simulatedInput = "start" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator")
                + "1" + System.getProperty("line.separator")
                + "9" + System.getProperty("line.separator")
                + "2" + System.getProperty("line.separator")
                + "8" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(playerFactory);
        var database = new Database();
        var connection = database.connect();
        var gameSaver = new GameSaver(connection);
        var gameLoader = new GameLoader(connection);

        var controller = new Controller(gameFactory, display, gameSaver, gameLoader, inputValidator);
        var expectedOutput = "x is the winner!\n";

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    void displaysTieMessageWhenGameIsATie() throws SQLException {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        var simulatedInput = "start" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator")
                + "1" + System.getProperty("line.separator")
                + "2" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator")
                + "5" + System.getProperty("line.separator")
                + "4" + System.getProperty("line.separator")
                + "6" + System.getProperty("line.separator")
                + "8" + System.getProperty("line.separator")
                + "7" + System.getProperty("line.separator")
                + "9" + System.getProperty("line.separator")
                + "6" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(playerFactory);
        var database = new Database();
        var connection = database.connect();
        var gameSaver = new GameSaver(connection);
        var gameLoader = new GameLoader(connection);

        var controller = new Controller(gameFactory, display, gameSaver, gameLoader, inputValidator);
        var expectedOutput = "It's a tie!\n";

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    void displaysSavedGameMessagesWhenUserEntersSave() throws SQLException {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        var simulatedInput = "start" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator")
                + "save" + System.getProperty("line.separator")
                + "cool game" + System.getProperty("line.separator")
                + "no" + System.getProperty("line.separator");
        ;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(playerFactory);
        var database = new Database();
        var connection = database.connect();
        var gameSaver = new GameSaver(connection);
        var gameLoader = new GameLoader(connection);
        var controller = new Controller(gameFactory, display, gameSaver, gameLoader, inputValidator);
        var expectedOutput1 = "Please wait, saving 'cool game'...\n";
        var expectedOutput2 = "Your game is saved!\n";
        System.out.println(output.toString());

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput1));
        assertTrue(output.toString().contains(expectedOutput2));
    }

    @Test
    void displaysContinuePlayingGameMessageAfterGameIsSaved() throws SQLException {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var simulatedInput = "start" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator")
                + "3" + System.getProperty("line.separator")
                + "save" + System.getProperty("line.separator")
                + "cool game" + System.getProperty("line.separator")
                + "no" + System.getProperty("line.separator");
        ;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(playerFactory);
        var database = new Database();
        var connection = database.connect();
        var gameSaver = new GameSaver(connection);
        var gameLoader = new GameLoader(connection);
        var controller = new Controller(gameFactory, display, gameSaver, gameLoader, inputValidator);

        var expectedOutput = "Resume the current game? Yes or No: \n";
        System.out.println(output.toString());

        controller.playGame();

        assertTrue(output.toString().contains(expectedOutput));
    }
}