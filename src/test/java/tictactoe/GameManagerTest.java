package tictactoe;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameManagerTest {

    Connection databaseConnection() {
        var database = new Database();
        return database.connect();
    }

    void addGameToDatabase() {
        var gameSaver = new GameSaver(databaseConnection());

        var squares = Arrays.asList("x", "x", "x", "4", "5", "6", "7", "o", "o");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        gameSaver.saveGame("best game", game);
    }

    void clearTable() {
        var query = "DELETE FROM saved_games";

        try {
            var connection = databaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Table cleared successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void returnsANewGameWhenInputIsStart() throws SQLException {
        var simulatedInput = "start" + System.getProperty("line.separator")
                + "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "computer" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var database = new Database();
        var connection = database.connect();
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var playerFactory = new PlayerFactory(display, inputValidator, new Minimax());
        var gameFactory = new GameFactory(playerFactory);
        var gameLoader = new GameLoader(connection);
        var gameSaver = new GameSaver(connection);
        var expectedSquares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

        var game = GameManager.newOrExistingGame(inputValidator, gameFactory, gameLoader, gameSaver);

        assertThat(game).isInstanceOf(Game.class);
        assertEquals(game.board.getGrid().getSquares(), expectedSquares);
    }

    @Test
    void returnsAnExistingGameWhenInputIsExisting() throws SQLException {
        clearTable();
        addGameToDatabase();
        var simulatedInput = "existing" + System.getProperty("line.separator")
                + "best game" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var database = new Database();
        var connection = database.connect();
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var playerFactory = new PlayerFactory(display, inputValidator, new Minimax());
        var gameFactory = new GameFactory(playerFactory);
        var gameLoader = new GameLoader(connection);
        var gameSaver = new GameSaver(connection);
        var expectedSquares = Arrays.asList("x", "x", "x", "4", "5", "6", "7", "o", "o");

        var game = GameManager.newOrExistingGame(inputValidator, gameFactory, gameLoader, gameSaver);

        assertThat(game).isInstanceOf(Game.class);
        assertEquals(game.board.getGrid().getSquares(), expectedSquares);
    }
}