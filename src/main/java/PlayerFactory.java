public class PlayerFactory {
    private final Display display;
    private final InputValidator inputValidator;

    public PlayerFactory
            (Display display, InputValidator inputValidator) {
        this.display = display;
        this.inputValidator = inputValidator;
    }

    public Player createNewPlayer(String playerType, String mark) {
        if (playerType.equals("h")) {
            return new HumanPlayer(mark, inputValidator);
        } else {
            return new ComputerPlayer(display, mark);
        }
    }
}
