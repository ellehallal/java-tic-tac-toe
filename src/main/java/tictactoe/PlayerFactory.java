package tictactoe;

public class PlayerFactory {
    private final Display display;
    private final InputValidator inputValidator;
    private final Minimax minimax;

    public PlayerFactory
            (Display display, InputValidator inputValidator, Minimax minimax) {
        this.display = display;
        this.inputValidator = inputValidator;
        this.minimax = minimax;
    }

    public Player createNewPlayer
            (int playerNumber, String otherPlayersMark) {
        var playerType = getPlayerTypeFromUser(playerNumber);
        var mark = getPlayerMarkFromUser(playerNumber, otherPlayersMark);

        return createTypeOfPlayer(mark, playerType);
    }

    private String getPlayerMarkFromUser(int playerNumber, String otherPlayersMark) {
        return inputValidator.validateMarkSelection(playerNumber, otherPlayersMark);
    }

    private String getPlayerTypeFromUser(int playerNumber) {
        return inputValidator.validatePlayerSelection(playerNumber);
    }

    public Player createTypeOfPlayer(String mark, String playerType) {
        if (playerType.equals(PlayerTypes.human.toString())) {
            return new HumanPlayer(mark, inputValidator);
        } else if (playerType.equals(PlayerTypes.computer.toString())) {
            return new ComputerPlayer(display, mark);
        } else {
            return new UnbeatableComputerPlayer(display, mark, minimax);
        }
    }
}
