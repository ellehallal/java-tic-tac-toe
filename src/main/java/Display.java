import java.util.List;

public class Display {
    private ConsoleWriter consoleWriter;

    public Display(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
    }

    public void show_squares(List<String> squares) {
        var squaresOutput =
            squares.get(0) + " | " + squares.get(1) + " | " + squares.get(2) +
            "\n---------\n" +
            squares.get(3) + " | " + squares.get(4) + " | " + squares.get(5) +
            "\n---------\n" +
            squares.get(6) + " | " + squares.get(7) + " | " + squares.get(8);
        consoleWriter.println(squaresOutput);
    }
}
