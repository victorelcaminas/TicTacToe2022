import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Player player1, player2, currentPlayer;
        Board b = new Board();
        boolean gameOver = false;
        player1 = getPlayerFromKeyboard(input);
        player2 = getPlayerFromKeyboard(input);
        currentPlayer = getRandomPlayer(player1, player2);
        while (!gameOver) {
            System.out.println(b);
            System.out.println(currentPlayer.getName() +
                    "(" + currentPlayer.getSymbol() + ")" +
                    " Enter row and col:");
            System.out.print("Row: ");
            int row = input.nextInt() - 1;
            System.out.print("Column:");
            int col = input.nextInt() - 1;
            if (b.shoot(row, col, currentPlayer)) {
                if (b.wins()) {
                    System.out.println(b);
                    System.out.println("WINNER: " + currentPlayer);
                    gameOver = true;
                } else {
                    currentPlayer = changePlayer(currentPlayer, player1, player2);
                }
            }
        }
    }

    private static Player changePlayer(Player current, Player player1, Player player2) {
        return current == player1 ? player2 : player1;
    }

    private static Player getRandomPlayer(Player player1, Player player2) {
        int rand = (int)(Math.random() * 2);
        return rand == 0 ? player1 : player2;
    }

    private static Player getPlayerFromKeyboard(Scanner input) {
        System.out.println("Enter the name of player " + (Player.getNumPlayers() == 0 ? "1" : "2"));
        String name = input.next();
        return new Player(name);
    }
}
