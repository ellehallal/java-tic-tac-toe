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

    public String setSquare(int index, String data) {
        return squares.set(index, data);
    }

    public int getSize(){
        return squares.size();
    }
}
