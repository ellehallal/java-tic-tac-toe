import java.io.PrintStream;

public class ConsoleWriter {
    private final PrintStream output;
    
    public ConsoleWriter(PrintStream output) {
        this.output = output;
    }
    
    public ConsoleWriter() {
        this(System.out);
    }
    
    public void println(String toPrint) {
        output.println(toPrint);
    }
}
