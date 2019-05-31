package tictactoe;

import java.io.IOException;

public class Display {
    private final ConsoleWriter consoleWriter;

    public Display(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
    }

    public void showGrid(Grid grid) {
        var squaresOutput =
                grid.getSquare(0) + " | " + grid.getSquare(1) + " | " + grid.getSquare(2) +
                        "\n---------\n" +
                        grid.getSquare(3) + " | " + grid.getSquare(4) + " | " + grid.getSquare(5) +
                        "\n---------\n" +
                        grid.getSquare(6) + " | " + grid.getSquare(7) + " | " + grid.getSquare(8) + "\n";
        consoleWriter.println(squaresOutput);
    }


    public void outcomeMessage(String outcome) {
        clearScreen();
        if (outcome.equals(GameOutcome.tie.toString())) {
            tieMessage();
        } else {
            winnerMessage(outcome);
        }
    }

    public void playerSelectionMessage(int playerNumber) {
        clearScreen();
        var output = "Please select player " + playerNumber + "\n(human, computer or unbeatable):";
        consoleWriter.println(output);
    }

    public void invalidPlayerSelectionMessage() {
        var output = "Invalid player selection. Please try again.";
        consoleWriter.println(output);
        pause(1500);
    }

    public void playerMarkMessage(int playerNumber) {
        clearScreen();
        var output = "Please select player " + playerNumber + "'s mark:";
        consoleWriter.println(output);
    }

    public void invalidPlayerMarkMessage() {
        var output = "Invalid move selection. Please try again.";
        consoleWriter.println(output);
        pause(1500);
        clearScreen();
    }

    public void computerPlayerMessages(Grid grid, String mark) {
        clearScreen();
        playerTurnMessage(mark);
        showGrid(grid);
        makeMoveMessage();
        pause(1500);
        computerIsThinkingMessage(mark);
        pause(2000);
    }

    public void humanPlayerMessages(Grid grid, String playersMark, boolean isValid) {
        clearScreen();
        if (isValid) playerTurnMessage(playersMark);
        if (!isValid) invalidMoveMessage(playersMark);

        showGrid(grid);
        makeMoveMessage();
    }

    public void inputErrorMessage() {
        var output = "Trouble reading input ";
        consoleWriter.println(output);
    }

    public void errorMessage(IOException error) {
        consoleWriter.println(error);
    }

    private void pause(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void tieMessage() {
        var output = "It's a tie!\n";
        consoleWriter.println(output);
    }

    private void winnerMessage(String mark) {
        var output = mark + " is the winner!\n";
        consoleWriter.println(output);
    }

    private void computerIsThinkingMessage(String mark) {
        var output = mark + ", is thinking...\n";
        consoleWriter.println(output);
    }

    private void playerTurnMessage(String mark) {
        var output = mark + ", it's your turn.";
        consoleWriter.println(output);
    }

    private void makeMoveMessage() {
        var output = "Choose a position from 1 - 9:";
        consoleWriter.println(output);
    }

    private void invalidMoveMessage(String mark) {
        var output = "Invalid move. Please try again, " + mark + ".";
        consoleWriter.println(output);
        pause(1500);
        clearScreen();
    }

    private void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
