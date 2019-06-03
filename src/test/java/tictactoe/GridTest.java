package tictactoe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GridTest {

    @Test
    void returnsGrid() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        assertEquals(squares, grid.getSquares());
    }

    @Test
    void returnsStringInSquare() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var square = "1";

        assertEquals(square, grid.getSquare(0));
    }

    @Test
    void replacesDataInSquare() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var data = "data";

        grid.setSquare(0, data);

        assertEquals(data, grid.getSquare(0));
    }

    @Test
    void returnsNumberOfSquares() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        assertEquals(9, grid.getSize());
    }

    @Test
    void returnsACopyOfGrid() {
        var squares = Arrays.asList("x", "o", "x", "4", "o", "o", "7", "x", "x");
        var grid = new Grid(squares);
        var board = new Board(grid);

        var copyOfGrid = grid.copyGrid();

        assertNotEquals(grid, copyOfGrid);
        assertEquals(grid.getSquares(), copyOfGrid.getSquares());

        assertEquals(grid.getSquares(), copyOfGrid.getSquares());
    }
}
