package tictactoe;

public enum ValidMove {
    DIGIT("\\d+");

    public final String move;

    ValidMove(String move) {
        this.move = move;
    }
}