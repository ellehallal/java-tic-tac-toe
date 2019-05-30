package tictactoe;

public class UnbeatableComputerPlayer implements Player {
    private final Display display;
    private final String mark;
    private final Minimax minimax;


    public UnbeatableComputerPlayer(Display display, String mark, Minimax minimax) {
        this.display = display;
        this.mark = mark;
        this.minimax = minimax;
    }

    @Override
    public int chooseMove(Board board, String opponentMark) {
        display.computerPlayerMessages(board.getGrid(), mark);
        return minimax.chooseMove(board, mark, opponentMark);
    }

    @Override
    public String getMark() {
        return mark;
    }
}
