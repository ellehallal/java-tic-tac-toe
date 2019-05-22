import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
