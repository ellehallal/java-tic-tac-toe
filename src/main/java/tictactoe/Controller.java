package tictactoe;

import java.sql.SQLException;

public class Controller {
    private Game game;
    private boolean continueGame = true;
    private final GameFactory gameFactory;
    private final Display display;
    private final GameSaver gameSaver;
    private final GameLoader gameLoader;
    private final InputValidator inputValidator;

    public Controller(GameFactory gameFactory, Display display,
                      GameSaver gameSaver, GameLoader gameLoader, InputValidator inputValidator) {
        this.gameFactory = gameFactory;
        this.display = display;
        this.gameSaver = gameSaver;
        this.gameLoader = gameLoader;
        this.inputValidator = inputValidator;
    }

    public void playGame() throws SQLException {
        game = GameManager.newOrExistingGame(inputValidator, gameFactory, gameLoader, gameSaver);
        display.saveGameReminderMessage();

        while (continueGame) {
            gameLoop(game);
            saveGameOrShowOutcome();

            if (!game.over()) {
                continueGameOrEndGame();
            } else {
                continueGame = false;
                exitGame();
            }
        }
    }

    private void gameLoop(Game game) {
        while (!game.over() && !game.saveGame) {
            game.playMove();
        }
    }

    private void saveGameOrShowOutcome() {
        if (game.saveGame) {
            game.saveGame = false;
            saveGame();
        } else {
            endOfGame();
        }
    }

    private void endOfGame() {
        var outcome = game.outcome();
        display.outcomeMessage(outcome);
        display.showGrid(game.board.getGrid());
    }

    private void saveGame() {
        var gameName = inputValidator.validateGameNameDoesNotExist(gameSaver);
        gameSaver.saveGame(gameName, game);
        display.savingGameMessage(gameName);
        display.gameSavedMessage();
    }

    private void continueGameOrEndGame() {
        var input = inputValidator.validateContinueGameSelection();

        if (!input) {
            continueGame = false;
            exitGame();
        }
    }

    private void exitGame() {
        display.exitGameMessage();
    }
}
