public class Board {
    public static final String EMPTY = ".";
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLS = 3;
    private String[][] board;
    public static final Shot INVALID_SHOT = new Shot(-1,-1);

    public Board() {
        board = new String[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    @Override
    public String toString() {
        String s = "  ";
        for (int i = 1; i <= board[0].length; i++) {
            s += i + " ";
        }
        s += "\n";

        for (int row = 0; row < board.length; row++) {
            s += (row + 1) + " ";
            for (int col = 0; col < board[0].length; col++) {
                s += board[row][col] + " ";
            }
            s += "\n";
        }
        return s;
    }

    public boolean correctShoot(Shot shot, Player player) {
        if (shot.row < 0 || shot.row >= NUM_ROWS ||
                shot.col < 0 || shot.col >= NUM_COLS) {
            System.out.println("Wrong ROW or COL");
            return false;
        }
        String symbol = player.getSymbol();
        if (board[shot.row][shot.col] == EMPTY) {
            board[shot.row][shot.col] = symbol;
            return true;
        } else {
            System.out.println("Position already in use");
            return false;
        }
    }

    public boolean wins() {
        // Check rows
        for (int row = 0; row < board.length; row++) {
            String symbol = board[row][0];
            if (symbol != EMPTY) {
                int count = 1;
                for (int col = 1; col < board[0].length; col++) {
                    if (board[row][col] == symbol) {
                        count++;
                    }
                }
                if (count == NUM_COLS) {
                    return true;
                }
            }

        }
        // Check columns
        for (int col = 0; col < board[0].length; col++) {
            String symbol = board[0][col];
            if (symbol != EMPTY) {
                int count = 1;
                for (int row = 1; row < board.length; row++) {
                    if (board[row][col] == symbol) {
                        count++;
                    }
                }
                if (count == NUM_ROWS) {
                    return true;
                }
            }
        }

        // Check diagonal 1
        String symbol = board[0][0];
        if (symbol != EMPTY) {
            int count = 1;
            for (int i = 1; i < board.length; i++) {
                if (board[i][i] == symbol) {
                    count++;
                }
            }
            if (count == NUM_ROWS) {
                return true;
            }
        }

        // Check diagonal 2
        symbol = board[0][NUM_COLS - 1];
        if (symbol != EMPTY) {
            int count = 1;
            int row = 1;
            for (int i = NUM_COLS - 2; i >= 0; i--) {
                if (board[row][i] == symbol) {
                    count++;
                    row ++;
                }
            }
            if (count == NUM_COLS) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDraw() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].equals(EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }


    public Shot shootAI(Player currentPlayer) {
        Shot shot = rule1(currentPlayer);
        if (shot.isValid()) {
            return shot;
        }
        shot = rule2(currentPlayer);
        if (shot.isValid()) {
            return shot;
        }
        shot = rule3(currentPlayer);
        if (shot.isValid()) {
            return shot;
        }
        shot = rule4(currentPlayer);
        return shot;
    }


    private Shot rule1(Player currentPlayer) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].equals(EMPTY)) {
                    Shot shot = new Shot(row, col);
                    correctShoot(shot, currentPlayer);
                    if (wins()) {
                        board[row][col] = EMPTY;
                        return shot;
                    } else {
                        board[row][col] = EMPTY;
                    }
                }
            }
        }
        return INVALID_SHOT;
    }

    private Shot rule2(Player currentPlayer) {
        Player human = Main.changePlayer(currentPlayer, Main.player1, Main.player2);
        Shot humanShot = rule1(human);
        Main.changePlayer(currentPlayer, Main.player1, Main.player2);
        return humanShot;
    }

    private Shot rule3(Player currentPlayer) {
        if (board[NUM_ROWS / 2][NUM_COLS / 2].equals(EMPTY)) {
            return new Shot(NUM_ROWS / 2, NUM_COLS / 2);
        } else {
            return INVALID_SHOT;
        }
    }

    private Shot rule4(Player currentPlayer) {
        boolean emptyPosition = false;
        int row = 20, col = 20;
        while (!emptyPosition) {
            row = (int)(Math.random() * NUM_ROWS);
            col = (int)(Math.random() * NUM_COLS);
            if (board[row][col].equals(EMPTY)) {
                emptyPosition = true;
            }
        }
        return new Shot(row, col);
    }


}
