import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game g = new Game(4);
        g.dealHoleCards();
        boolean gameInProgress = true;
        int ante = 100;
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to Text-as Holdem, a text-based version of... yeah you know. Let's play some poker!\n");
        while (gameInProgress) {
            System.out.println(g.toString());
            System.out.println("The ante is " + ante + ". Press 'f' to fold, 'c' to check, 'r' to raise, or 'q' to quit:");
            char c = Character.toLowerCase(reader.next().charAt(0));
            if (c == 'q') {
                gameInProgress = false;
            } else if (c == 'f') {
                System.out.println("You fold.");
            } else if (c == 'c') {
                System.out.println("You check.");
            } else if (c == 'r') {
                System.out.println("You raise. How much would you like to raise by? (Enter as multiple of the ante)");
                int raise = reader.nextInt();
                System.out.println(raise);
            }
        }
        reader.close();
    }
}
