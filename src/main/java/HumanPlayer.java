public class HumanPlayer implements Player {
    private final Display display;
    private final String mark;
    private final InputValidator inputValidator;
    
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
        display.playerTurnMessage(mark);
        var move = validateMove();
        
        while (!board.moveValid(move, mark, opponentMark)) {
            display.invalidMoveMessage(mark);
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
