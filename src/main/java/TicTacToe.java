import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var squares = new ArrayList<String>
                (Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        var grid = new Grid(squares);
        var board = new Board(grid);
        var scanner = new Scanner(System.in);
        var humanPlayer = new HumanPlayer(display, "x", scanner);

        display.grid(board.getGrid());
        var move = humanPlayer.choose_move(board);
        board.addMarkToGrid(move, humanPlayer.getMark());
        display.grid(board.getGrid());
    }
}
