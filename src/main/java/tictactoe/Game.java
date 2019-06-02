package tictactoe;

public class Game {
    public final Board board;
    public boolean saveGame = false;
    private Player currentPlayer;
    private Player opponent;
    private final Player player1;
    private final Player player2;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.opponent = player2;
    }

    public String currentPlayersMark() {
        return currentPlayer.getMark();
    }

    public String opponentsMark() {
        return opponent.getMark();
    }

    public String currentPlayersType() {
        return currentPlayer.getPlayerType();
    }

    public String opponentsType() {
        return opponent.getPlayerType();
    }

    public void playMove() {
        var move = currentPlayer.chooseMove(board, opponentsMark());
        switch (move) {
            case -2:
                saveGame = true;
                break;
            default:
                board.takeSquare(move, currentPlayersMark());
                toggleCurrentPlayer();
        }
    }

    public boolean over() {
        return board.isFull(currentPlayersMark(), opponentsMark()) ||
                board.winningLineExists(currentPlayersMark(), opponentsMark());
    }

    public String outcome() {
        if (board.winningLineExists(currentPlayersMark(), opponentsMark())) {
            return winner();
        } else {
            return GameOutcome.tie.toString();
        }
    }

    private String winner() {
        if (board.isWinningPlayer(currentPlayersMark())) {
            return currentPlayersMark();
        } else {
            return opponentsMark();
        }
    }

    private void toggleCurrentPlayer() {
        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
            opponent = player1;
        } else {
            currentPlayer = player1;
            opponent = player2;
        }
    }
}
