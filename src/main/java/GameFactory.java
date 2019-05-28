public class GameFactory {
    private final InputValidator inputValidator;
    private final PlayerFactory playerFactory;
    private final Board board;

    public GameFactory
            (InputValidator inputValidator, PlayerFactory playerFactory, Board board) {
        this.inputValidator = inputValidator;
        this.playerFactory = playerFactory;
        this.board = board;
    }

    public Game createGame() {
        var player1 = createPlayer(1, "");
        var player2 = createPlayer(2, player1.getMark());

        return new Game(board, player1, player2);
    }

    private Player createPlayer(int playerNumber, String otherPlayersMark) {
        var playerType = inputValidator.validatePlayerSelection(playerNumber);
        var playerMark = inputValidator.validateMarkSelection(playerNumber, otherPlayersMark);
        return playerFactory.createPlayer(playerType, playerMark);
    }
}
