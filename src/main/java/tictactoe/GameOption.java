package tictactoe;

public enum GameOption {
    save,
    yes,
    no,
    existing,
    start,
    invalid;

    static GameOption fromString(String gameOptionAsString) {
        switch (gameOptionAsString) {
            case "start":
                return start;
            case "existing":
                return existing;
            default:
                return invalid;
        }
    }
}
