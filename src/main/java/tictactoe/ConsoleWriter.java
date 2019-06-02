package tictactoe;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;

public class ConsoleWriter implements Serializable {
    transient private final PrintStream output;

    public ConsoleWriter(PrintStream output) {
        this.output = output;
    }

    public ConsoleWriter() {
        this(System.out);
    }

    public void println(String toPrint) {
        output.println(toPrint);
    }

    public void println(IOException error) {
        output.println(error);
    }
}
