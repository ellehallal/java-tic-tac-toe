import java.util.ArrayList;
import java.util.List;

public class Grid {
    
    private final int[][] winningCombinations = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6},
    };
    private final List<String> squares;
    
    public Grid(List<String> squares) {
        this.squares = new ArrayList<>(squares);
    }
    
    public int[][] getWinningCombinations() {
        return winningCombinations;
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
    
    public int getSize() {
        return squares.size();
    }
}
