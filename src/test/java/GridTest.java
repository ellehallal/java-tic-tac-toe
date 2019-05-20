import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void returnsGrid() {
        var squares = new ArrayList<>(Arrays.asList
                ("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        var grid = new Grid(squares);

        assertEquals(grid.getSquares(), squares);
    }

    @Test
    void returnsStringInSquare() {
        var squares = new ArrayList<>(Arrays.asList
                ("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        var grid = new Grid(squares);
        var square = "1";

        assertEquals(grid.getSquare(0), square);
    }

    @Test
    void replacesDataInSquare() {
        var squares = new ArrayList<>(Arrays.asList
                ("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        var grid = new Grid(squares);
        var data = "data";

        grid.setSquare(0, data);

        assertEquals(grid.getSquare(0), data);
    }

    @Test
    void returnsNumberOfSquares() {
        var squares = new ArrayList<>(Arrays.asList
                ("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        var grid = new Grid(squares);

        assertEquals(grid.getSize(), 9);
    }
}