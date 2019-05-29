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
        displayMessages(board);
        
        var availableSquares = board.availableSquares(mark, opponentMark);
        Random rand = new Random();
        var move = availableSquares.get(rand.nextInt(availableSquares.size()));
        
        return Integer.parseInt(move);
    }
    
    @Override
    public String getMark() {
        return mark;
    }
    
    private void displayMessages(Board board) {
        display.showGrid(board.getGrid());
        display.playerTurnMessage(mark);
        display.makeMoveMessage();
        pause(1000);
        display.computerIsThinkingMessage(mark);
        pause(2000);
    }
    
    private void pause(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
