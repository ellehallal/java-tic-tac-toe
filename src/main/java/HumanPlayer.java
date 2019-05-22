public class HumanPlayer implements Player {
    private Display display;
    private String mark;
    private InputValidator inputValidator;

    public HumanPlayer
            (Display display, String mark, InputValidator inputValidator) {
        this.display = display;
        this.mark = mark;
        this.inputValidator = inputValidator;
    }

    public String getMark() {
        return mark;
    }

    @Override
    public int chooseMove(Board board, String opponentMark) {
        display.showGrid(board.getGrid());
        var move = 0;
        move = validateMove();

        while (board.moveValid(move, mark, opponentMark) == false) {
            display.invalidMoveMessage();
            display.showGrid(board.getGrid());
            move = validateMove();
        }
        return move;
    }

    private int validateMove() {
        display.makeMoveMessage();
        return inputValidator.validateMove();
    }
}
