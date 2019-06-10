package tictactoe;

public class UnbeatableComputerPlayer implements Player {
    private final Display display;
    private final String mark;
    private final Minimax minimax;
    private final PlayerType playerType = PlayerType.unbeatable;

    public UnbeatableComputerPlayer(Display display, String mark, Minimax minimax) {
        this.display = display;
        this.mark = mark;
        this.minimax = minimax;
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
        return minimax.chooseMove(board, mark, opponentMark);
    }
}
