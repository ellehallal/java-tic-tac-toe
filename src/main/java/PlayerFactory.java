public class PlayerFactory {
    private final Display display;
    private final InputValidator inputValidator;
    
    public PlayerFactory
            (Display display, InputValidator inputValidator) {
        this.display = display;
        this.inputValidator = inputValidator;
    }
    
    public Player createPlayer(String playerType, String mark) {
        
        if (playerType.equals("h")) {
            return new HumanPlayer(display, mark, inputValidator);
        } else {
            return new ComputerPlayer(display, mark);
        }
    }
}
