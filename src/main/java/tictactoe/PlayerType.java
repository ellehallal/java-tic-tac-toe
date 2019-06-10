package tictactoe;

public enum PlayerType {
    human,
    computer,
    unbeatable,
    invalid;

    static PlayerType fromString(String playerTypeAsString) {
        switch (playerTypeAsString) {
            case "human":
                return human;
            case "computer":
                return computer;
            case "unbeatable":
                return unbeatable;
            default:
                return invalid;
        }

    }
}
