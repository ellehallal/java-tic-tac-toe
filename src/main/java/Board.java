public class Board {
    private Grid grid;

    public Board(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public void addMarkToGrid(int position, String mark, String player2Mark) {
        if (moveValid(position, mark, player2Mark)) {
            int index = position - 1;
            grid.setSquare(index, mark);
        }
        else {
            System.out.println("invalid move");
        }
    }

    public boolean moveValid
            (int position, String player1Mark, String player2Mark) {

        if (positionNotValid(position) || squareTaken(position, player1Mark, player2Mark)) {
            return false;
        }
        return true;
    }

    private boolean squareTaken
            (int position, String player1Mark, String player2Mark) {
        var index = position - 1;
        var square = grid.getSquare(index);

        return square.contains(player1Mark) || square.contains(player2Mark);
    }


    private boolean positionNotValid(int position) {
        position -= 1;
        var gridSize = grid.getSize();


        if(position < 0 || position >= gridSize) {
            return true;
        } else {return false;}
    }

}
