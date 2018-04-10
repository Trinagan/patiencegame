import java.util.Scanner;

public class Exit {

    public Scores high = new Scores();

    private String path = high.scoresPath;

    public void exit(Integer piles, Integer cardAmount){

        Integer cardsLeft = 52 - cardAmount;

        Integer pile = piles;

        System.out.println("Game over");
        System.out.println("You have " + pile + " piles, with " + cardsLeft + " cards left.");
        Scanner username = new Scanner(System.in);
        String nick;
        System.out.println("Your nick: ");
        nick = username.nextLine();
        try {

        high.saveScores(path, nick, pile, cardsLeft);
        } catch (Exception e) {
            System.err.println("Error 404: WTF IS WRONG??");
        }

        System.out.println("To close the game, close 'The Cards' window");
    }
}
