package tictactoe;

public class GameFactory {
    private final PlayerFactory playerFactory;
    private final Board board;

    public GameFactory
            (PlayerFactory playerFactory, Board board) {
        this.playerFactory = playerFactory;
        this.board = board;
    }

    public Game createNewGame() {
        var player1 = playerFactory.createNewPlayer(1, "");
        var player2 = playerFactory.createNewPlayer(2, player1.getMark());

        return new Game(board, player1, player2);
    }
}
