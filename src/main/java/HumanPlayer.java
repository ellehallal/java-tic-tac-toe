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
    public int choose_move(Board board) {
        display.make_move();
        var move = scanner.nextInt();
        return move;
    }
}
