public class TicTacToe {
    public static void main (String[] args) {
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var board = new Board();

        display.show_squares(board.getSquares());
    }
}
