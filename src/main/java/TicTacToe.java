import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var scanner = new Scanner(System.in);
        var inputValidator = new InputValidator(scanner);
        var player1 = new HumanPlayer(display, "x", inputValidator);
        var player2 = new HumanPlayer(display, "o", inputValidator);
        var game = new Game(board, player1, player2);
        var controller = new Controller(game, display);

        controller.playGame();
    }
}