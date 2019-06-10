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

    public Player createNewPlayerFromUserInput
            (int playerNumber, String otherPlayersMark) {
        var playerType = inputValidator.validatePlayerTypeSelection(playerNumber);
        var mark = inputValidator.validateMarkSelection(playerNumber, otherPlayersMark);

        return createPlayer(mark, playerType);
    }

    public Player createPlayer(String mark, String playerType) {
        if (playerType.equals(PlayerType.human.toString())) {
            return new HumanPlayer(mark, inputValidator);
        } else if (playerType.equals(PlayerType.computer.toString())) {
            return new ComputerPlayer(display, mark);
        } else {
            return new UnbeatableComputerPlayer(display, mark, minimax);
        }
    }
}