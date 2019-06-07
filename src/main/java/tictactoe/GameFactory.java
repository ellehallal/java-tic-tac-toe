package tictactoe;

import java.util.Arrays;
import java.util.List;

public class GameFactory {
    private final PlayerFactory playerFactory;
    private final List newBoardSquares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

    public GameFactory
            (PlayerFactory playerFactory) {
        this.playerFactory = playerFactory;
    }

    public Game newGame() {
        var player1 = playerFactory.createNewPlayer(1, "");
        var player2 = playerFactory.createNewPlayer(2, player1.getMark());
        var board = BoardFactory.newBoard(newBoardSquares);

        return new Game(board, player1, player2);
    }

    public Game existingGame(String currentPlayersMark, String currentPlayersType,
                             String opponentsMark, String opponentsType, List squares) {

        var player1 = playerFactory.createTypeOfPlayer(currentPlayersMark, currentPlayersType);
        var player2 = playerFactory.createTypeOfPlayer(opponentsMark, opponentsType);
        var board = BoardFactory.newBoard(squares);

        return new Game(board, player1, player2);
    }
}
