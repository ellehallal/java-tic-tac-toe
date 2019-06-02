package tictactoe;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InputValidatorTest {
    Database database = new Database();
    Connection connection = database.connect();
    GameSaver gameSaver = new GameSaver(connection);

    void clearTable() {
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

    void addGameNamedExcellentGameToTable() {
        var squares = Arrays.asList("x", "x", "3", "4", "5", "6", "7", "o", "o");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);
        gameSaver.saveGame("excellent game", game);
    }

    @Test
    void returnsUsersInputWhenItContainsNumbersOnly() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var simulatedInput = "33";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var move = inputValidator.validateMove(grid, "x", true);

        assertEquals(33, move);
    }

    @Test
    void returnsMinus2WhenUsersInputIsSave() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var simulatedInput = "save";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var move = inputValidator.validateMove(grid, "x", true);

        assertEquals(-2, move);
    }

    @Test
    void returnsZeroWhenUsersInputDoesNotContainNumbersOnly() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        var simulatedInput = "33other*$&£*$43) []";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var move = inputValidator.validateMove(grid, "x", true);

        assertEquals(0, move);
    }

    @Test
    void returnsUserInputIfItIsh() {
        var simulatedInput = "human" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertEquals(PlayerTypes.human.toString(), playerSelection);
    }

    @Test
    void returnsUserInputIfItIsc() {
        var simulatedInput = "computer" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertEquals(PlayerTypes.computer.toString(), playerSelection);
    }

    @Test
    void ifInputIsNothOrcItIsNotReturned() {
        var simulatedInput = "3" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertNotEquals("3", playerSelection);
        assertEquals(PlayerTypes.human.toString(), playerSelection);
    }

    @Test
    void returnsUserInputIfItIsUnbeatable() {
        var simulatedInput = "unbeatable" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertEquals(PlayerTypes.unbeatable.toString(), playerSelection);
    }

    @Test
    void ifInputIsNotUnbeatableItIsNotReturned() {
        var simulatedInput = "3" + System.getProperty("line.separator")
                + "unbeatable" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertNotEquals("3", playerSelection);
        assertEquals(PlayerTypes.unbeatable.toString(), playerSelection);
    }

    @Test
    void returnsPlayersMarkIfInputIsNotADigit() {
        var simulatedInput = "7" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "");

        assertEquals("o", playerSelection);
    }

    @Test
    void returnsPlayersMarkIfInputIsNotAnEmptyString() {
        var simulatedInput = "" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "");

        assertEquals("o", playerSelection);
    }

    @Test
    void returnsPlayersMarkIfInputIsNotWhitespace() {
        var simulatedInput = " " + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "");

        assertEquals("o", playerSelection);
    }

    @Test
    void returnsPlayersMarkIfInputIsTheSameAsOtherPlayersMark() {
        var simulatedInput = "x" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "x");

        assertEquals("o", playerSelection);
    }

    @Test
    void returnsInputIfGameNameDoesNotExistInTable() {

        var simulatedInput = "bad game" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var gameName = inputValidator.validateGameNameSelection(gameSaver);

        assertEquals("bad game", gameName);
    }

    @Test
    void promptsForInputAgainIfGameNameExistsInTable() {
        addGameNamedExcellentGameToTable();

        var simulatedInput = "excellent game" + System.getProperty("line.separator") +
                "bye" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var expectedOutput = "An saved game called 'excellent game' already exists.\n" +
                "Please try another name.\n";

        inputValidator.validateGameNameSelection(gameSaver);
        var outputString = output.toString();

        assertThat(outputString).contains(expectedOutput);
        clearTable();
    }
}