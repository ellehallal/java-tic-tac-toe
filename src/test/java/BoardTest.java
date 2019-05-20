import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void returnsGrid() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);
        assertEquals(board.getGrid(), grid);
    }

    @Test
    void addsMarkToGrid() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);
        board.addMarkToGrid(1, "x", "o");
        assertEquals(grid.getSquare(0), "x");
    }

    @Test
    void returnsTrueIfSquareIsAvailable() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);

        assertEquals
                (board.moveValid(1, "x", "o"), true);
    }

    @Test
    void returnsFalseIfSquareIsNotAvailable() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("x", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);

        assertEquals
                (board.moveValid(1, "x", "o"), false);
    }

    @Test
    void returnsFalsePositionisNotBetween1AndGridSize() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("x", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);

        assertEquals
                (board.moveValid(10, "x", "o"), false);
    }
}