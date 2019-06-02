package tictactoe;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void togglesTheCurrentPlayerAfterASuccessfulMove() {
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        game.playMove();

        assertEquals("o", game.currentPlayersMark());
        game.playMove();
        assertEquals("x", game.currentPlayersMark());
    }

    @Test
    void returnsCurrentPlayersMark() {
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var currentPlayersMark = game.currentPlayersMark();

        assertEquals("x", currentPlayersMark);
    }

    @Test
    void returnsOpponentsMark() {
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var opponentsMark = game.opponentsMark();

        assertEquals("o", opponentsMark);
    }

    @Test
    void addsCurrentPlayersMarkToTheBoard() {
        var squares = Arrays.asList("x", "o", "x", "o", "x", "o", "o", "x", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        game.playMove();

        assertEquals("x", grid.getSquare(8));
    }

    @Test
    void returnsTrueWhenBoardIsFull() {
        var squares = Arrays.asList("x", "o", "x", "o", "x", "o", "o", "x", "o");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var isBoardFull = game.over();

        assertTrue(isBoardFull);
    }

    @Test
    void returnsTrueWhenAPlayerHasAWinningLine() {
        var squares = Arrays.asList("x", "x", "x", "4", "5", "o", "o", "o", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var isBoardFull = game.over();

        assertTrue(isBoardFull);
    }

    @Test
    void returnsFalseWhenBoardIsNotFullAndNoPlayersHaveAWinningLine() {
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var isBoardFull = game.over();

        assertFalse(isBoardFull);
    }

    @Test
    void returnsTieWhenNeitherPlayerHasWon() {
        var squares = Arrays.asList("x", "o", "x", "x", "o", "o", "o", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var gameOutcome = game.outcome();

        assertEquals(GameOutcome.tie.toString(), gameOutcome);
    }

    @Test
    void returnsWinningPlayersMark() {
        var squares = Arrays.asList("x", "x", "x", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var gameOutcome = game.outcome();

        assertEquals("x", gameOutcome);
    }

    @Test
    void returnsCurrentPlayersType() {
        var squares = Arrays.asList("x", "x", "x", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var player1 = new FakePlayer("x");
        var player2 = new FakePlayer("o");
        var game = new Game(board, player1, player2);

        var playerType = game.currentPlayersType();

        assertEquals("fake", playerType);
    }

    @Test
    void returnsOpponentPlayersType() {
        var squares = Arrays.asList("x", "x", "x", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var player1 = new FakePlayer("x");
        var player2 = new ComputerPlayer(display, "o");
        var game = new Game(board, player1, player2);

        var playerType = game.opponentsType();

        assertEquals("computer", playerType);
    }

    @Test
    void saveGameIsTrueWhenUserInputIsSave() {
        var simulatedInput = "save" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var squares = Arrays.asList("x", "x", "x", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var player1 = new HumanPlayer("x", inputValidator);
        var player2 = new HumanPlayer("o", inputValidator);
        var game = new Game(board, player1, player2);

        game.playMove();

        assertTrue(game.saveGame);
    }
}