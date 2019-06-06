package tictactoe;

public class Controller {
    private Game game;
    private boolean continueGame = true;
    private final GameFactory gameFactory;
    private final Display display;
    private final GameSaver gameSaver;
    private final InputValidator inputValidator;

    public Controller(GameFactory gameFactory, Display display,
                      GameSaver gameSaver, InputValidator inputValidator) {
        this.gameFactory = gameFactory;
        this.display = display;
        this.gameSaver = gameSaver;
        this.inputValidator = inputValidator;
    }

    public void playGame() {
        game = createGame();
        display.saveGameReminderMessage();

        while (continueGame) {
            gameLoop(game);
            saveGameOrShowOutcome();

            if (!game.over()) {
                resumeGameOrEndGame();
            } else {
                continueGame = false;
                exitGame();
            }
        }
    }

    private Game createGame() {
        return gameFactory.createGame();
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
        var gameName = inputValidator.validateGameNameSelection(gameSaver);
        gameSaver.saveGame(gameName, game);
        display.savingGameMessage(gameName);
        display.gameSavedMessage();
    }

    private void resumeGameOrEndGame() {
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
