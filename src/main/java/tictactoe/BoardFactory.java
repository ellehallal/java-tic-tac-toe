package tictactoe;

import java.util.List;

public class BoardFactory {

    public static Board newBoard(List squares) {
        var grid = new Grid(squares);
        return new Board(grid);
    }
}
