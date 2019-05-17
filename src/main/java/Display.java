import java.util.List;

public class Display {
    private ConsoleWriter consoleWriter;

    public Display(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
    }

    public void show_grid(Grid grid) {
        var squaresOutput =
            grid.getSquare(0) + " | " + grid.getSquare(1) + " | " + grid.getSquare(2) +
            "\n---------\n" +
            grid.getSquare(3) + " | " + grid.getSquare(4) + " | " + grid.getSquare(5) +
            "\n---------\n" +
            grid.getSquare(6) + " | " + grid.getSquare(7) + " | " + grid.getSquare(8);
        consoleWriter.println(squaresOutput);
    }
}
