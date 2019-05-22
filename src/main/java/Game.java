public class Game {
    public Player currentPlayer;
    public Player opponent;
    public Board board;
    private Player player1;
    private Player player2;

    public Game (Board board, Player player1, Player player2) {
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

    public void playMove() {
        var move = currentPlayer.chooseMove(board, opponentsMark());
        board.takeSquare(move, currentPlayersMark());
        toggleCurrentPlayer();
    }

    public boolean over() {
        return board.full(currentPlayersMark(), opponentsMark()) ||
                board.winningLineExists(currentPlayersMark(), opponentsMark());
    }

    public String outcome() {
        if (board.winningLineExists(currentPlayersMark(), opponentsMark())) {
            return winner();
        } else {
            return "tie";
        }
    }

    private String winner() {
        if (board.winningLine(currentPlayersMark())) {
            return currentPlayersMark();
        } else {
            return opponentsMark();
        }
    }

    private void toggleCurrentPlayer(){
        if (currentPlayer == player1) {
            currentPlayer = player2;
            opponent = player1;
        } else {
            currentPlayer= player1;
            opponent = player2;
        }
    }

}
