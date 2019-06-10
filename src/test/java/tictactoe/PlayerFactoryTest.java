package tictactoe;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerFactoryTest {


    @Test
    void createsAHumanPlayer() {
        var display = new Display(new ConsoleWriter());
        var simulatedInput = "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var newPlayer = playerFactory.createNewPlayerFromUserInput(1, "");

        var isHumanPlayer = newPlayer.getClass().equals(HumanPlayer.class);

        assertTrue(isHumanPlayer);
    }

    @Test
    void createsAComputerPlayer() {
        var display = new Display(new ConsoleWriter());
        var simulatedInput = "computer" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var newPlayer = playerFactory.createNewPlayerFromUserInput(1, "");

        var isComputerPlayer = newPlayer.getClass().equals(ComputerPlayer.class);

        assertTrue(isComputerPlayer);
    }

    @Test
    void createsAnUnbeatableComputerPlayer() {
        var display = new Display(new ConsoleWriter());
        var simulatedInput = "unbeatable" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var newPlayer = playerFactory.createNewPlayerFromUserInput(1, "");

        var isUnbeatableComputerPlayer = newPlayer.getClass().equals(UnbeatableComputerPlayer.class);

        assertTrue(isUnbeatableComputerPlayer);
    }
}