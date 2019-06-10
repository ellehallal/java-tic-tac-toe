package tictactoe;

import java.util.Random;

public class ComputerPlayer implements Player {
    private final Display display;
    private final String mark;
    private final PlayerType playerType = PlayerType.computer;

    public ComputerPlayer(Display display, String mark) {
        this.display = display;
        this.mark = mark;
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public String getPlayerType() {
        return playerType.toString();
    }

    @Override
    public int chooseMove(Board board, String opponentMark) {
        display.computerPlayerMessages(board.getGrid(), mark);

        var availableSquares = board.availableSquares(mark, opponentMark);
        Random rand = new Random();
        var move = availableSquares.get(rand.nextInt(availableSquares.size()));

        return Integer.parseInt(move);
    }
}
