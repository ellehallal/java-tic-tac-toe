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
                grid.getSquare(6) + " | " + grid.getSquare(7) + " | " + grid.getSquare(8);
        consoleWriter.println(squaresOutput);
    }

    public void makeMoveMessage() {
        var output = "Choose a position from 1 - 9:";
        consoleWriter.println(output);
    }

    public void invalidMoveMessage(String mark) {
        var output = "Invalid move. Please try again, " + mark + ".";
        consoleWriter.println(output);
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

    private void tieMessage() {
        var output = "It's a tie!";
        consoleWriter.println(output);
    }

    private void winnerMessage(String mark) {
        var output = mark + " is the winner!";
        consoleWriter.println(output);
    }

}
