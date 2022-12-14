import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Player player1, player2, currentPlayer;

    public static void main(String[] args) {
        boolean gameOver = false;
        Board board = new Board();
        player1 = new Player("", true); // getPlayerFromKeyboard();
        player2 = new Player("", true);
        currentPlayer = getRandomPlayer(player1, player2);
        while (!gameOver) {
            System.out.println(board);
            Shot shot = getShot(currentPlayer, board);
            if (board.correctShoot(shot, currentPlayer)) {
                if (board.wins()) {
                    System.out.println(board);
                    System.out.println("WINNER: " + currentPlayer);
                    gameOver = true;
                } else {
                    if (board.checkDraw()) {
                        System.out.println(board);
                        System.out.println("IT IS A DRAW: ");
                        gameOver = true;
                    } else {
                        currentPlayer = changePlayer(currentPlayer, player1, player2);
                    }
                }
            }
        }
    }

    public static Shot shootAI(Player currentPlayer, Board board) {
        return board.shootAI(currentPlayer);
    }

    public static Shot getHumanShot(Player currentPlayer) {
        System.out.println(currentPlayer.getName() +
                "(" + currentPlayer.getSymbol() + ")" +
                " Enter row and col:");
        System.out.print("Row: ");
        int row = input.nextInt() - 1;
        System.out.print("Column:");
        int col = input.nextInt() - 1;
        return new Shot(row, col);
    }

    public static Shot getShot(Player currentPlayer, Board board) {
        if (currentPlayer.isAI()) {
            return shootAI(currentPlayer, board);
        } else {
            return getHumanShot(currentPlayer);
        }

    }

    public static Player changePlayer(Player current, Player player1, Player player2) {
        return current == player1 ? player2 : player1;
    }

    private static Player getRandomPlayer(Player player1, Player player2) {
        int rand = (int)(Math.random() * 2);
        return rand == 0 ? player1 : player2;
    }

    private static Player getPlayerFromKeyboard() {
        System.out.println("Enter the name of player " + (Player.getNumPlayers() == 0 ? "1" : "2"));
        String name = input.next();
        return new Player(name, false);
    }
}
