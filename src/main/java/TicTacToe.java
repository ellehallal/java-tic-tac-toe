import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToe {
    public static void main (String[] args) {
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var squares = new ArrayList<String>
                (Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        var grid = new Grid(squares);
        var board = new Board(grid);

        display.show_grid(board.getGrid());
    }
}
