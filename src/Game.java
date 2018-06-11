public class Game {
    Player[] players;
    Deck deck;
    Card[] tableCards;

    Game(int numPlayers) {
        players = new Player[numPlayers];
        players[0] = new Player("You", true);
        for (int i = 1; i < numPlayers; i++) {
            players[i] = new Player("Player " + i, false);
        }
        deck = new Deck();
        tableCards = new Card[5];
    }

    void dealHoleCards() {
        for (Player p : players) {
            p.addHoleCards(deck.drawCard(), deck.drawCard());
        }
    }

    void dealFlop() {
        tableCards[0] = deck.drawCard();
        tableCards[1] = deck.drawCard();
        tableCards[2] = deck.drawCard();
    }

    void dealTurn() {
        tableCards[3] = deck.drawCard();
    }

    void dealRiver() {
        tableCards[4] = deck.drawCard();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card c : tableCards) {
            if (c != null) {
                sb.append(c.toString()); sb.append(" ");
            }
        }
        sb.append("\n");
        for (Player p : players) {
            sb.append(p.toString());
        }
        return sb.toString();
    }
}
