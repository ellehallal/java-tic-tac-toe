package tictactoe;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameSaverTest {

    Connection connectionSetup() {
        Database database = new Database();
        return database.connect();
    }

    GameSaver gameSaversetup() {
        return new GameSaver(connectionSetup());
    }

    void clearTable() {
        var query = "DELETE FROM saved_games";

        try {
            var conn = connectionSetup();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Table cleared successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void savesGameToDatabase() {
        clearTable();
        var gameSaver = gameSaversetup();
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        gameSaver.saveGame("testgame", game);
        var doesGameExistInDatabase = gameSaver.isGameNameInDatabase("testgame");

        assertTrue(doesGameExistInDatabase);
    }

    @Test
    void savesGameStateMinusSquaresToAnArrayList() {
        var gameSaver = gameSaversetup();
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var gameState = gameSaver.getGameState("cool game", game);

        assertEquals(gameState.get(0), "cool game");
        assertEquals(gameState.get(1), "x");
        assertEquals(gameState.get(2), "fake");
        assertEquals(gameState.get(3), "o");
        assertEquals(gameState.get(4), "fake");
    }

    @Test
    void savesSquaresAsAStringArray() {
        var gameSaver = gameSaversetup();
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var squaresArray = gameSaver.getSquares(game);
        var expectedSquare = squaresArray[0];

        assertEquals(expectedSquare, "x");
    }
}