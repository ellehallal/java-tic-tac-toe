package tictactoe;

public enum PlayerTypes {
    HUMAN("h"),
    COMPUTER("c"),
    UNBEATABLE("uc");

    public final String type;

    PlayerTypes(String type) {
        this.type = type;
    }
}
