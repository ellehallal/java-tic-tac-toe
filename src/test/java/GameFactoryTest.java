import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class GameFactoryTest {
    @Test
    void createsANewInstanceOfGame() {
        var simulatedInput = "h" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "c" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var display = new Display(new ConsoleWriter());
        var inputValidator = new InputValidator(bufferedReader, display);
        var playerFactory = new PlayerFactory(display, inputValidator);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var gameFactory = new GameFactory(inputValidator, playerFactory, board);

        var newGame = gameFactory.createGame();

        assertThat(newGame).isInstanceOf(Game.class);
    }

}