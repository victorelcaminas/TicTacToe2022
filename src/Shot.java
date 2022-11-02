public class Shot {
    public int row, col;

    public Shot(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isValid() {
        if (row < 0 || row >= Board.NUM_ROWS ||
            col < 0 || col >= Board.NUM_COLS) {
            return false;
        } else {
            return true;
        }
    }
}
