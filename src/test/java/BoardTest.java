import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void returnsGrid() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);

        assertEquals(grid, board.getGrid());
    }

    @Test
    void addsMarkToSpecifiedSquare() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);

        board.takeSquare(1, "x");

        assertEquals("x", grid.getSquare(0));
    }

    @Test
    void returnsTrueIfSquareIsAvailable() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var isMoveValid = board.moveValid(1, "x", "o");

        assertTrue(isMoveValid);
    }

    @Test
    void returnsFalseIfSquareIsNotAvailable() {
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var isMoveValid = board.moveValid(0, "x", "o");

        assertFalse(isMoveValid);
    }

    @Test
    void returnsFalseIfPositionIsNotBetween1AndGridSize() {
        var squares = Arrays.asList("x", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var isMoveValid = board.moveValid(10, "x", "o");

        assertFalse(isMoveValid);
    }


    @Test
    void returnsTrueIfMarkHasAWinningLine() {
        var squares = Arrays.asList("x", "2", "3", "4", "x", "6", "7", "8", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var isWinningLine = board.winningLine("x");

        assertTrue(isWinningLine);
    }

    @Test
    void returnsFalseIfMarkDoesNotHaveAWinningLine() {
        var squares = Arrays.asList("x", "2", "3", "4", "o", "o", "7", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var isWinningLine = board.winningLine("x");

        assertFalse(isWinningLine);
    }

    @Test
    void returnsTrueIfEitherMarkHasAWinningLine() {
        var squares = Arrays.asList("x", "x", "x", "4", "o", "o", "7", "8", "o");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var winningLineExists = board.winningLineExists("x", "o");

        assertTrue(winningLineExists);
    }

    @Test
    void returnsFalseIfNeitherMarkHasAWinningLine() {
        var squares = Arrays.asList("x", "o", "x", "4", "o", "o", "7", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var winningLineExists = board.winningLineExists("x", "o");

        assertFalse(winningLineExists);
    }

    @Test
    void returnsTrueWhenAllSquaresAreTaken() {
        var squares = Arrays.asList("x", "o", "x", "o", "o", "o", "x", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var full = board.full("x", "o");

        assertTrue(full);
    }

    @Test
    void returnsFalseWhenAllSquaresAreNotTaken() {
        var squares = Arrays.asList("x", "o", "x", "4", "o", "o", "7", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var full = board.full("x", "o");

        assertFalse(full);
    }

    @Test
    void returnsTrueWhenWinningLineExistsOrBoardIsFull() {
        var squares = Arrays.asList("x", "o", "x", "4", "o", "o", "x", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var complete = board.complete("x", "o");

        assertTrue(complete);
    }

    @Test
    void returnsFalseBoardIsNotFullOrWinningLineDoesNotExist() {
        var squares = Arrays.asList("x", "o", "x", "4", "o", "o", "7", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var complete = board.complete("x", "o");

        assertFalse(complete);
    }

    @Test
    void returnsAvailableSquaresOnTheGrid() {
        var squares = Arrays.asList("x", "o", "x", "4", "o", "o", "7", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var expectedSquares = new ArrayList<>(Arrays.asList("4", "7"));

        var availableSquares = board.availableSquares("x", "o");

        assertEquals(expectedSquares, availableSquares);
    }
}