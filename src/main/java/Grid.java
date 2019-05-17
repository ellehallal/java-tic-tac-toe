import java.util.List;

public class Grid {
    private List<String> squares;

    public Grid(List<String> squares) {
        this.squares = squares;
    }

    public List<String> getSquares() {
        return squares;
    }

    public String getSquare(int index) {
        return squares.get(index);
    }
}
