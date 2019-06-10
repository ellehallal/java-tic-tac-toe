package tictactoe;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameLoaderTest {

    Connection databaseConnection() {
        var database = new Database();
        return database.connect();
    }

    GameLoader gameLoaderSetup(Connection connection) {
        return new GameLoader(connection);
    }

    void addGameToDatabase(Connection connection) {
        var gameSaver = new GameSaver(connection);

        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        gameSaver.saveGame("game loader test", game);
    }

    void clearTable(Connection connection) {
        var query = "DELETE FROM saved_games";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Table cleared successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void selectEntryFromDatabase() throws SQLException {
        clearTable(databaseConnection());
        addGameToDatabase(databaseConnection());
        var gameLoader = gameLoaderSetup(databaseConnection());
        var expectedSquares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");

        gameLoader.getGame("game loader test");

        assertEquals("x", gameLoader.getPlayerInformation(0));
        assertEquals("fake", gameLoader.getPlayerInformation(1));
        assertEquals("o", gameLoader.getPlayerInformation(2));
        assertEquals("fake", gameLoader.getPlayerInformation(3));
        assertEquals(expectedSquares, gameLoader.getSquares());
    }
}