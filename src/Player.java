public class Player {
    private String name;
    private String symbol;
    private boolean isAI;
    private static int numPlayers = 0;

    public Player(String name, boolean isAI) {
        this.isAI = isAI;
        this.name = isAI ? "AI" : name;
        if (numPlayers == 0) {
            symbol = "O";
        } else {
            symbol = "X";
        }
        numPlayers ++;
    }

    public boolean isAI() {
        return isAI;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public static int getNumPlayers() {
        return numPlayers;
    }
}
