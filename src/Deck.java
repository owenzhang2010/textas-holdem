import java.util.Random;

public class Deck {
    Card[] cards;
    int topIndex;

    Deck() {
        int[] ranks = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        String[] rankStrs = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        String[] suits = {"C", "D", "H", "S"};
        cards = new Card[52];

        for (int r = 0; r < ranks.length; r++) {
            for (int s = 0; s < suits.length; s++) {
                cards[4 * r + s] = new Card(ranks[r], rankStrs[r], suits[s]);
            }
        }
        topIndex = 0;
        shuffle();
    }

    void shuffle() {
        Random r = new Random();
        for (int i = 0; i < 52; i++) {
            int j = r.nextInt(52);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    Card drawCard() {
        Card c = cards[topIndex];
        topIndex++;
        return c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card c : cards) {
            sb.append(c.toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}
