package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;

public class InputValidator {
    private final BufferedReader bufferedReader;
    private final Display display;

    public InputValidator(BufferedReader bufferedReader, Display display) {
        this.bufferedReader = bufferedReader;
        this.display = display;
    }

    public int validateMove(Grid grid, String playersMark, boolean isValid) {
        display.humanPlayerMessages(grid, playersMark, isValid);
        var input = getInput();
        return (input.matches(ValidMove.DIGIT.move)) ? Integer.parseInt(input) : 0;
    }

    public String validatePlayerSelection(int playerNumber) {
        display.playerSelectionMessage(playerNumber);
        var input = getInput().toLowerCase();

        while (!input.matches(PlayerTypes.HUMAN.type) && !input.matches(PlayerTypes.COMPUTER.type)) {
            display.invalidPlayerSelectionMessage();
            display.playerSelectionMessage(playerNumber);

            input = getInput();
        }
        return input;
    }

    public String validateMarkSelection(int playerNumber, String otherPlayersMark) {
        display.playerMarkMessage(playerNumber);
        var input = getInput();

        while (isMarkSelectionInvalid(input, otherPlayersMark)) {
            display.invalidPlayerMarkMessage();
            display.playerMarkMessage(playerNumber);
            input = getInput();
        }
        return input;
    }

    private String getInput() {
        var input = "";

        try {
            input = bufferedReader.readLine();
        } catch (IOException error) {
            display.inputErrorMessage();
            display.errorMessage(error);
        }
        return input;
    }

    private boolean isMarkSelectionInvalid(String input, String otherPlayersMark) {
        return input.matches(InvalidPlayerMarks.DIGIT.mark)
                || input.matches(InvalidPlayerMarks.WHITESPACE.mark)
                || input.matches(InvalidPlayerMarks.EMPTY.mark)
                || input.matches(otherPlayersMark);
    }
}