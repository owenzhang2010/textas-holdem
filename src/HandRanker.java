import java.util.List;

public class HandRanker {
    private static final int HIGH_CARD = 0;
    private static final int PAIR = 1;
    private static final int TWO_PAIR = 2;
    private static final int THREES = 3;
    private static final int STRAIGHT = 4;
    private static final int FLUSH = 5;
    private static final int FULL_HOUSE = 6;
    private static final int FOURS = 7;
    private static final int STRAIGHT_FLUSH = 8;
    private static final int ROYAL_FLUSH = 9;


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
        int[] rankFreqs = new int[15]; // position 0 is dummy, 1 and 14 are both for aces, others in between
        int[] suitFreqs = new int[4]; // CDHS
        String suits = "CDHS";

        for (Card c : fives) {
            rankFreqs[c.rank] += 1;
            suitFreqs[suits.indexOf(c.suit)] += 1;
        }
        rankFreqs[1] = rankFreqs[14]; // aces
        if (isFlush(suitFreqs) && isStraight(rankFreqs)) {
            if (highCard(rankFreqs) == 14) {
                return ROYAL_FLUSH;
            }
            return STRAIGHT_FLUSH;
        } else if (isFlush(suitFreqs)) {
            return FLUSH;
        } else if (isStraight(rankFreqs)) {
            return STRAIGHT;
        }
        int maxFreq = maxFreq(rankFreqs);
        if (maxFreq == 4) return FOURS;
        if (maxFreq == 3) return triples(rankFreqs);
        if (maxFreq == 2) return doubles(rankFreqs);
        return HIGH_CARD;
    }

    private static boolean isStraight(int[] rankFreqs) {
        for (int i = 10; i >= 1; i--) {
            if (rankFreqs[i] == 1 && rankFreqs[i + 1] == 1 && rankFreqs[i + 2] == 1
                    && rankFreqs[i + 3] == 1 && rankFreqs[i + 4] == 1) return true;
        }
        return false;
    }

    private static boolean isFlush(int[] suitFreqs) {
        for (int freq : suitFreqs) {
            if (freq == 5) return true;
        }
        return false;
    }

    private static int highCard(int[] rankFreqs) {
        for (int i = 14; i >= 2; i--) {
            if (rankFreqs[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private static int maxFreq(int[] rankFreqs) {
        int max = 0;
        for (int f : rankFreqs) {
            max = (f > max) ? f : max;
        }
        return max;
    }

    private static int triples(int[] rankFreqs) {
        for (int f : rankFreqs) {
            if (f == 2) return FULL_HOUSE;
        }
        return THREES;
    }

    private static int doubles(int[] rankFreqs) {
        int pairs = 0;
        for (int f : rankFreqs) {
            if (f == 2) pairs++;
        }
        return (pairs == 2) ? TWO_PAIR : PAIR;
    }

    private static Card[] breakTies(List<Card[]> hands, int handType) { // returns null if it's a push
        switch (handType) {
            case HIGH_CARD:
                return null;
            case PAIR:
                return null;
            case TWO_PAIR:
                return null;
            case THREES:
                return null;
            case STRAIGHT:
                return null;
            case FLUSH:
                return null;
            case FULL_HOUSE:
                return null;
            case FOURS:
                return null;
            case STRAIGHT_FLUSH:
                return null;
            default:
                return null;

        }
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
