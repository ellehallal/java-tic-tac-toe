package tictactoe;

public interface Player {
    int chooseMove(Board board, String opponentMark);

    String getMark();
}
