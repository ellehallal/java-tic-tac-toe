import java.util.Random;

public class ComputerPlayer implements Player {
    private final Display display;
    private final String mark;
    
    public ComputerPlayer(Display display, String mark) {
        this.display = display;
        this.mark = mark;
    }
    
    @Override
    public int chooseMove(Board board, String opponentMark) {
        display.computerMessages(board.getGrid(), mark);
        
        var availableSquares = board.availableSquares(mark, opponentMark);
        Random rand = new Random();
        var move = availableSquares.get(rand.nextInt(availableSquares.size()));
        
        return Integer.parseInt(move);
    }
    
    @Override
    public String getMark() {
        return mark;
    }
}
