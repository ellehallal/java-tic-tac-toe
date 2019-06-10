package tictactoe;

import java.sql.SQLException;

public class GameManager {
    public static Game newOrExistingGame
            (InputValidator inputValidator, GameFactory gameFactory, GameLoader gameLoader, GameSaver gameSaver) throws SQLException {
        var input = inputValidator.validateGameOptionSelection();
        if (input.matches(GameOption.start.toString())) {
            return createNewGame(gameFactory);
        } else {
            return loadExistingGame(inputValidator, gameFactory, gameLoader, gameSaver);
        }
    }

    private static Game createNewGame(GameFactory gameFactory) {
        return gameFactory.newGame();
    }

    private static Game loadExistingGame
            (InputValidator inputValidator, GameFactory gameFactory,
             GameLoader gameLoader, GameSaver gameSaver) throws SQLException {

        var gameName = inputValidator.validateGameNameExists(gameSaver);
        gameLoader.getGame(gameName);
        var currentPlayersMark = gameLoader.getPlayerInformation(0).toString();
        var currentPlayersType = gameLoader.getPlayerInformation(1).toString();
        var opponentsMark = gameLoader.getPlayerInformation(2).toString();
        var opponentsType = gameLoader.getPlayerInformation(3).toString();
        var squares = gameLoader.getSquares();
        return gameFactory.existingGame(currentPlayersMark, currentPlayersType, opponentsMark, opponentsType, squares);
    }
}
