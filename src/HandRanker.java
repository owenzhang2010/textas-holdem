public class HandRanker {
    public static int rankHand(Card[] holeCards, Card[] tableCards) {
        if (holeCards == null || tableCards == null) throw new IllegalArgumentException("somethings null");
        if (holeCards.length != 2) throw new IllegalArgumentException("hole cards wrong size");
        if (tableCards.length != 5) throw new IllegalArgumentException("table cards wrong size");

        return 0;
    }
}
