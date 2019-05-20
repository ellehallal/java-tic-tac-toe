import java.util.ArrayList;
import java.util.List;

public class Board {
    private Grid grid;

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

    public boolean winningLine(String playerMark) {
        var winner = false;
        var count = 0;
        var size = 0;

        for (int[] combination : grid.getWinningCombinations()) {
            size = combination.length;

            for (int index : combination) {
                if (grid.getSquare(index) == playerMark) count += 1;
                if (count == size) winner = true;
            }
            count = 0;
        }
        return winner;
    }

    public boolean winningLineExists
            (String player1Mark, String player2Mark) {
        return winningLine(player1Mark) || winningLine(player2Mark);
    }

    public boolean full(String player1Mark, String player2Mark) {
        var availableSquares = availableSquares(player1Mark, player2Mark);
        return availableSquares.size() == 0;
    }

    public boolean complete(String player1Mark, String player2Mark) {
        return full(player1Mark, player2Mark) ||
                winningLineExists(player1Mark, player2Mark);
    }

    public List<String> availableSquares(String player1Mark, String player2Mark) {
        List<String> availableSquares = new ArrayList<>();

        for (String square : grid.getSquares()) {
            if (square != player1Mark && square != player2Mark) {
                availableSquares.add(square);
            }
        }
        return availableSquares;
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
