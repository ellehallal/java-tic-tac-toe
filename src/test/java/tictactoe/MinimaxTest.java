package tictactoe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimaxTest {


    @Test
    void returns3AsTheBestMove() {
        var squares = Arrays.asList("x", "x", "3", "4", "o", "6", "o", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var minimax = new Minimax();
        var expectedMove = 3;

        var bestMove = minimax.chooseMove(board, "x", "o");

        assertEquals(expectedMove, bestMove);
    }

    @Test
    void returns3AsTheBestMoveToBlockOpponent() {
        var squares = Arrays.asList("1", "x", "3", "4", "5", "x", "o", "o", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var minimax = new Minimax();
        var expectedMove = 3;

        var bestMove = minimax.chooseMove(board, "x", "o");

        assertEquals(expectedMove, bestMove);
    }

    @Test
    void returns9AsTheBestMoveToWin() {
        var squares = Arrays.asList("1", "2", "3", "o", "o", "6", "x", "x", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var minimax = new Minimax();
        var expectedMove = 9;

        var bestMove = minimax.chooseMove(board, "x", "o");

        assertEquals(expectedMove, bestMove);
    }

    @Test
    void returns7AsTheBestMoveToBlockOpponent() {
        var squares = Arrays.asList("o", "2", "3", "4", "x", "o", "7", "8", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var minimax = new Minimax();
        var expectedMove = 7;

        var bestMove = minimax.chooseMove(board, "x", "o");

        assertEquals(expectedMove, bestMove);
    }

    @Test
    void returns9AsTheBestMoveToBlockOpponent() {
        var squares = Arrays.asList("x", "o", "3", "o", "o", "x", "x", "x", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var minimax = new Minimax();
        var expectedMove = 9;

        var bestMove = minimax.chooseMove(board, "x", "o");

        assertEquals(expectedMove, bestMove);
    }

    @Test
    void returns5AsTheBestMove() {
        var squares = Arrays.asList("x", "o", "x", "o", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var minimax = new Minimax();
        var expectedMove = 5;

        var bestMove = minimax.chooseMove(board, "x", "o");

        assertEquals(expectedMove, bestMove);
    }

}