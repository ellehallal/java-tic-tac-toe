package tictactoe;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InputValidatorTest {
    @Test
    void returnsUsersInputWhenItContainsNumbersOnly() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var simulatedInput = "33";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var move = inputValidator.validateMove(grid, "x", true);

        assertEquals(33, move);
    }

    @Test
    void returnsZeroWhenUsersInputDoesNotContainNumbersOnly() {
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        var simulatedInput = "33other*$&£*$43) []";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var move = inputValidator.validateMove(grid, "x", true);

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
        var simulatedInput = "c" + System.getProperty("line.separator");
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
    void returnsUserInputIfItIsuc() {
        var simulatedInput = "uc" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertEquals("uc", playerSelection);
    }

    @Test
    void ifInputIsNotucItIsNotReturned() {
        var simulatedInput = "3" + System.getProperty("line.separator")
                + "uc" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validatePlayerSelection(1);

        assertNotEquals("3", playerSelection);
        assertEquals("uc", playerSelection);
    }

    @Test
    void returnsPlayersMarkIfInputIsNotADigit() {
        var simulatedInput = "7" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "");

        assertEquals("o", playerSelection);
    }

    @Test
    void returnsPlayersMarkIfInputIsNotAnEmptyString() {
        var simulatedInput = "" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "");

        assertEquals("o", playerSelection);
    }

    @Test
    void returnsPlayersMarkIfInputIsNotWhitespace() {
        var simulatedInput = " " + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "");

        assertEquals("o", playerSelection);
    }

    @Test
    void returnsPlayersMarkIfInputIsTheSameAsOtherPlayersMark() {
        var simulatedInput = "x" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);

        var playerSelection = inputValidator.validateMarkSelection(1, "x");

        assertEquals("o", playerSelection);
    }
}