package tictactoe;

public class HumanPlayer implements Player {
    private final String mark;
    private final InputValidator inputValidator;

    public HumanPlayer
            (String mark, InputValidator inputValidator) {
        this.mark = mark;
        this.inputValidator = inputValidator;
    }

    public String getMark() {
        return mark;
    }

    @Override
    public int chooseMove(Board board, String opponentMark) {
        var grid = board.getGrid();
        var move = inputValidator.validateMove(grid, mark, true);

        while (!board.moveValid(move, mark, opponentMark)) {
            move = inputValidator.validateMove(grid, mark, false);
        }
        return move;
    }
}
