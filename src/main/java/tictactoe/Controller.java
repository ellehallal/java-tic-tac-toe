package tictactoe;

public class Controller {
    private Game game;
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
        gameLoop(game);
    }

    private Game createGame() {
        return gameFactory.createGame();
    }

    private void gameLoop(Game game) {
        while (!game.over() && !game.saveGame) game.playMove();
        saveGameOrShowOutcome();
    }

    private void saveGameOrShowOutcome() {
        if (game.saveGame) {
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
}
