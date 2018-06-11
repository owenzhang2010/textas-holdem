public class Card {
    int rank;
    String rankStr;
    String suit;

    Card(int r, String rs, String s) {
        rank = r;
        rankStr = rs;
        suit = s;
    }

    @Override
    public String toString() {
        return rankStr + suit;
    }
}
