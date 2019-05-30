import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final Grid grid;

    public Board(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public void takeSquare(int position, String playerMark) {
        int index = position - 1;
        grid.setSquare(index, playerMark);
    }

    public boolean moveValid
            (int position, String player1Mark, String player2Mark) {

        return positionValid(position)
                && squareAvailable(position, player1Mark, player2Mark);
    }

    public boolean isWinningPlayer(String playerMark) {
        var isWinner = false;
        var count = 0;
        var size = 0;

        for (int[] combination : grid.getWinningCombinations()) {
            size = combination.length;

            for (int index : combination) {
                var square = grid.getSquare(index);
                if (square.equals(playerMark)) {
                    count += 1;
                }
                if (count == size) {
                    isWinner = true;
                }
            }
            count = 0;
        }
        return isWinner;
    }

    public boolean winningLineExists
            (String player1Mark, String player2Mark) {
        return isWinningPlayer(player1Mark) || isWinningPlayer(player2Mark);
    }

    public boolean isFull(String player1Mark, String player2Mark) {
        return availableSquares(player1Mark, player2Mark).isEmpty();
    }

    public boolean isComplete(String player1Mark, String player2Mark) {
        return isFull(player1Mark, player2Mark) ||
                winningLineExists(player1Mark, player2Mark);
    }

    public List<String> availableSquares(String player1Mark, String player2Mark) {
        var squares = grid.getSquares();
        return squares.stream()
                .filter(square -> !square.equals(player1Mark) && !square.equals(player2Mark))
                .collect(Collectors.toList());
    }

    private boolean squareAvailable
            (int position, String player1Mark, String player2Mark) {
        var index = position - 1;
        var square = grid.getSquare(index);

        return !square.contains(player1Mark) && !square.contains(player2Mark);
    }

    private boolean positionValid(int position) {
        position -= 1;
        var gridSize = grid.getSize();

        return position >= 0 && position < gridSize;
    }
}
