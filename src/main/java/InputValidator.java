import java.util.Scanner;

public class InputValidator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public int validateMove() {
        var input = scanner.nextLine();
        return (input.matches("\\d+")) ? Integer.parseInt(input) : 0;
    }
}
