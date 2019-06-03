import tictactoe.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TicTacToe {
    public static void main(String[] args) {
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(inputValidator, playerFactory, board);

        var controller = new Controller(gameFactory, display);

        controller.playGame();
    }
}