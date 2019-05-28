import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InputValidatorTest {
    @Test
    void returnsUsersInputWhenItContainsNumbersOnly() {
        var simulatedInput = "33";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var move = inputValidator.validateMove();

        assertEquals(33, move);
    }

    @Test
    void returnsZeroWhenUsersInputDoesNotContainNumbersOnly() {
        var simulatedInput = "33other*$&£*$43) []";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var move = inputValidator.validateMove();

        assertEquals(0, move);
    }

    @Test
    void returnsUserInputIfItIsh() {
        var simulatedInput = "h";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertEquals("h", playerSelection);
    }

    @Test
    void returnsUserInputIfItIsc() {
        var simulatedInput = "c";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertEquals("c", playerSelection);
    }

    @Test
    void ifInputIsNothOrcItIsNotReturned() {
        var simulatedInput = "3" + System.getProperty("line.separator")
                + "h" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertNotEquals("3", playerSelection);
        assertEquals("h", playerSelection);
    }

    @Test
    void returnsPlayersMarkIfItIsNotADigit() {
        var simulatedInput = "7" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "");

        assertEquals("o", playerSelection);
    }
}