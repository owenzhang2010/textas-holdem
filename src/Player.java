public class Player {
    Card[] holeCards;
    int money;
    String name;
    boolean isUser;

    Player(String n, boolean isUser) {
        name = n;
        money = 2000;
        holeCards = new Card[2];
        this.isUser = isUser;
    }

    void addHoleCards(Card c1, Card c2) {
        holeCards[0] = c1;
        holeCards[1] = c2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("~~~~~~~~~~~~~~\n");
        sb.append("Name: "); sb.append(name);
        sb.append("\nMoney: "); sb.append(money);
        if (isUser) {
            sb.append("\n Hole cards: ");
            sb.append(holeCards[0]); sb.append(" "); sb.append(holeCards[1]);
        }
        sb.append("\n~~~~~~~~~~~~~~\n");
        return sb.toString();
    }
}
