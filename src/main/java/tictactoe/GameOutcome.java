package tictactoe;

public enum GameOutcome {
    TIE("TIE");

    public final String type;

    GameOutcome(String type) {
        this.type = type;
    }
}
