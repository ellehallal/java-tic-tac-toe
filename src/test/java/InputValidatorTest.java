import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    @Test
    void returnsUsersInputWhenItContainsNumbersOnly() {
        var simulatedInput = "33";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        var scanner = new Scanner(System.in);
        var inputValidator = new InputValidator(scanner);
        var move = inputValidator.validateMove();

        assertEquals(33, move);
    }

    @Test
    void returnsZeroWhenUsersInputDoesNotContainNumbersOnly() {
        var simulatedInput = "33other*$&£*$43) []";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        var scanner = new Scanner(System.in);
        var inputValidator = new InputValidator(scanner);
        var move = inputValidator.validateMove();

        assertEquals(0, move);
    }
}