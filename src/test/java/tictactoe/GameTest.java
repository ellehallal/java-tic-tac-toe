package tictactoe;

import org.junit.jupiter.api.Test;

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

        assertEquals("TIE", gameOutcome);
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
}