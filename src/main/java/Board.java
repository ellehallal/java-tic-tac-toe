import java.util.*;

public class Board {
    private List<String> squares;

    public Board(List<String> squares) {
        this.squares = squares;
    }

    public Board() {
        this(new ArrayList<String>(
                Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
    }

    public List<String> getSquares() {
        return squares;
    }
}
