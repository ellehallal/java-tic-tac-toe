import java.io.PrintStream;

public class ConsoleWriter {
    private PrintStream output;

    public ConsoleWriter(PrintStream output) {
        this.output = output;
    }

    public ConsoleWriter() {
        this(System.out);
    }

    public void println(String output) {
        this.output.println(output);
    }

}
