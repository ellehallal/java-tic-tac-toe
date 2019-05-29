import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerFactoryTest {


    @Test
    void createsAHumanPlayer() {
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var playerFactory = new PlayerFactory(display, inputValidator);
        var newPlayer = playerFactory.createNewPlayer("h", "x");

        var isHumanPlayer = newPlayer.getClass().equals(HumanPlayer.class);

        assertTrue(isHumanPlayer);
    }

    @Test
    void createsAComputerPlayer() {
        var consoleWriter = new ConsoleWriter();
        var display = new Display(consoleWriter);
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var playerFactory = new PlayerFactory(display, inputValidator);
        var newPlayer = playerFactory.createNewPlayer("c", "x");

        var isComputerPlayer = newPlayer.getClass().equals(ComputerPlayer.class);

        assertTrue(isComputerPlayer);
    }
}