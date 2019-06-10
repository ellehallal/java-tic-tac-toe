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
        if (input.toLowerCase().matches(GameOption.save.toString())) {
            return -2;
        } else {
            return (input.matches(ValidMove.DIGIT.move)) ? Integer.parseInt(input) : 0;
        }
    }


    public String validatePlayerTypeSelection(int playerNumber) {
        display.playerSelectionMessage(playerNumber);
        var input = getInput().toLowerCase();

        while (PlayerType.fromString(input) == PlayerType.invalid) {
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

    public String validateGameNameDoesNotExist(GameSaver gameSaver) {
        display.gameNamePromptMessage();
        var input = getInput();

        while (gameSaver.isGameNameInDatabase(input)) {
            display.gameNameExistsMessage(input);
            input = getInput();
        }

        return input.toLowerCase();
    }

    public String validateGameNameExists(GameSaver gameSaver) {
        display.gameNamePromptMessage();
        var input = getInput();

        while (!gameSaver.isGameNameInDatabase(input)) {
            display.gameNameDoesNotExistMessage(input);
            input = getInput();
        }

        return input.toLowerCase();
    }

    public boolean validateContinueGameSelection() {
        display.continuePlayingGameMessage();
        var input = getInput().toLowerCase();

        while (!input.matches(GameOption.yes.toString()) && !input.matches(GameOption.no.toString())) {
            display.invalidContinuePlayingGameMessage();
            display.continuePlayingGameMessage();
            input = getInput().toLowerCase();
        }

        return input.matches(GameOption.yes.toString());
    }

    public String validateGameOptionSelection() {
        display.newOrExistingGameMessage();
        var input = getInput().toLowerCase();

        while (GameOption.fromString(input).equals(GameOption.invalid)) {
            display.invalidGameOptionMessage();
            display.newOrExistingGameMessage();

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
        return input.matches(InvalidPlayerMark.DIGIT.mark)
                || input.matches(InvalidPlayerMark.WHITESPACE.mark)
                || input.matches(InvalidPlayerMark.EMPTY.mark)
                || input.matches(otherPlayersMark);
    }
}