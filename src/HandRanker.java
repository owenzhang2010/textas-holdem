public class HandRanker {
    public static int rankHand(Card[] holeCards, Card[] tableCards) {
        if (holeCards == null || tableCards == null) throw new IllegalArgumentException("somethings null");
        if (holeCards.length != 2) throw new IllegalArgumentException("hole cards wrong size");
        if (tableCards.length != 5) throw new IllegalArgumentException("table cards wrong size");

        Card[] cards = new Card[7];
        System.arraycopy(holeCards, 0, cards, 0, 2);
        System.arraycopy(tableCards, 0, cards, 2, 5);
        Card[][] hands = get5CardHands(cards);

        Card[] bestHand = null;
        int bestRank = 0;
        for (Card[] hand : hands) {
            int rank = rankHand(hand);
            bestRank = (rank > bestRank) ? rank : bestRank;
            bestHand = (rank > bestRank) ? hand : bestHand;
        }

        return bestRank;
    }

    private static int rankHand(Card[] fives) {
        return 0;
    }

    // from https://stackoverflow.com/questions/29910312/algorithm-to-get-all-the-combinations-of-size-n-from-an-array-java
    private static Card[][] get5CardHands(Card[] sevens) {
        int k = 5;
        Card[][] subsets = new Card[21][5];
        int index = 0;

        int[] s = new int[k];                  // here we'll keep indices
        // pointing to elements in input array

        if (k <= sevens.length) {
            // first index sequence: 0, 1, 2, ...
            for (int i = 0; (s[i] = i) < k - 1; i++);
            subsets[0] = getSubset(sevens, s);
            index++;
            for(;;) {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && s[i] == sevens.length - k + i; i--);
                if (i < 0) {
                    break;
                }
                s[i]++;                    // increment this item
                for (++i; i < k; i++) {    // fill up remaining items
                    s[i] = s[i - 1] + 1;
                }
                subsets[index] = getSubset(sevens, s);
                index++;
            }
        }
        return subsets;
    }

    // generate actual subset by index sequence
    private static Card[] getSubset(Card[] input, int[] subset) {
        Card[] result = new Card[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];
        return result;
    }
}
