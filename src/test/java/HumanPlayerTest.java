import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    @Test
    void choose_move() {
        var grid = new Grid
                (new ArrayList<>(Arrays.asList
                        ("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        var board = new Board(grid);
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var input = "1";
        var scanner = new Scanner(input);
        var humanPlayer = new HumanPlayer(display, "x", scanner);

        assertEquals(humanPlayer.choose_move(board), 1);

    }
}