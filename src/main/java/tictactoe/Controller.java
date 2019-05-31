package tictactoe;

public class Controller {
    private Game game;
    private final GameFactory gameFactory;
    private final Display display;

    public Controller(GameFactory gameFactory, Display display) {
        this.gameFactory = gameFactory;
        this.display = display;
    }

    public void playGame() {
        game = createGame();
        while (!game.over()) game.playMove();
        endOfGame();
    }

    private Game createGame() {
        return gameFactory.createGame();
    }

    private void endOfGame() {
        var outcome = game.outcome();
        display.outcomeMessage(outcome);
        display.showGrid(game.board.getGrid());
    }
}
