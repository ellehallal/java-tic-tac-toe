package tictactoe;

public class HumanPlayer implements Player {
    private final String mark;
    private final InputValidator inputValidator;
    private final PlayerType playerType = PlayerType.human;

    public HumanPlayer
            (String mark, InputValidator inputValidator) {
        this.mark = mark;
        this.inputValidator = inputValidator;
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
        var grid = board.getGrid();
        var move = inputValidator.validateMove(grid, mark, true);

        while (move != -2 && !board.moveValid(move, mark, opponentMark)) {
            move = inputValidator.validateMove(grid, mark, false);
        }
        return move;
    }
}
