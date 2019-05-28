public class Display {
    private final ConsoleWriter consoleWriter;

    public Display(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
    }

    public void showGrid(Grid grid) {
        clearScreen();
        var squaresOutput =
                grid.getSquare(0) + " | " + grid.getSquare(1) + " | " + grid.getSquare(2) +
                        "\n---------\n" +
                        grid.getSquare(3) + " | " + grid.getSquare(4) + " | " + grid.getSquare(5) +
                        "\n---------\n" +
                        grid.getSquare(6) + " | " + grid.getSquare(7) + " | " + grid.getSquare(8) + "\n";
        consoleWriter.println(squaresOutput);
    }

    public void makeMoveMessage() {
        var output = "Choose a position from 1 - 9:";
        consoleWriter.println(output);
    }

    public void invalidMoveMessage(String mark) {
        var output = "Invalid move. Please try again, " + mark + ".";
        consoleWriter.println(output);
        pause(1500);
    }

    public void outcomeMessage(String outcome) {
        if (outcome.equals("tie")) {
            tieMessage();
        } else {
            winnerMessage(outcome);
        }
    }

    public void playerTurnMessage(String mark) {
        var output = mark + ", it's your turn.";
        consoleWriter.println(output);
    }

    public void playerSelectionMessage(int playerNumber) {
        clearScreen();
        var output = "Please select player " + playerNumber + " (h = human, c = computer):";
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
        var output = "Invalid mark selection. Please try again.";
        consoleWriter.println(output);
        pause(1500);
    }

    public void computerMessages(Grid grid, String mark) {
        showGrid(grid);
        playerTurnMessage(mark);
        makeMoveMessage();
        pause(1000);
        computerIsThinkingMessage(mark);
        pause(2000);
    }

    private void pause(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void tieMessage() {
        var output = "It's a tie!";
        consoleWriter.println(output);
    }

    private void winnerMessage(String mark) {
        var output = mark + " is the winner!";
        consoleWriter.println(output);
    }

    private void computerIsThinkingMessage(String mark) {
        var output = mark + ", is thinking...";
        consoleWriter.println(output);
    }

    private void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
