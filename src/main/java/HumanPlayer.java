import java.util.Scanner;

public class HumanPlayer implements Player {
    private Display display;
    private String mark;
    private Scanner scanner;


    public HumanPlayer
            (Display display, String mark, Scanner scanner) {
        this.display = display;
        this.mark = mark;
        this.scanner = scanner;

    }

    public String getMark() {
        return mark;
    }

    @Override
    public int chooseMove(Board board, String opponentMark) {
        var move = 0;
        move = validateMove();

        while (board.moveValid(move, mark, opponentMark) == false) {
            display.invalid_move();
            move = validateMove();
        }
        return move;
    }

    private int validateMove(){
        display.make_move();
        var input = scanner.nextLine();

        return (input.matches("\\d+")) ? Integer.parseInt(input) : 0;
    }
}
