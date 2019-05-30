package tictactoe;

public enum GameOutcome {
    TIE("tie");

    public final String type;

    GameOutcome(String type) {
        this.type = type;
    }
}
