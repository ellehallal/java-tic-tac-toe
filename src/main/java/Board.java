public class Board {
    private Grid grid;

    public Board(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public void addMarkToGrid(int position, String mark) {
        int index = position - 1;
        grid.setSquare(index, mark);
    }

}
