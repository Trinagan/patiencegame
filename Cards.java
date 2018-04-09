import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Cards {

    private String cardsPath;
    public ArrayList printer;

    public String[] shuffleDeck;

    public void getFile() {
        if (System.getProperty("user.dir").contains("out")) {
            cardsPath = System.getProperty("user.dir") + "\\cards.txt";
        } else {
            cardsPath = System.getProperty("user.dir") + "\\src\\cards.txt";
        }
    }

    public void load() throws FileNotFoundException {
        getFile();
        shuffleDeck = new String[52];
        Scanner loadDeck = new Scanner(new FileReader(cardsPath));

        for (Integer i = 0; i <= 51; i++) {
            shuffleDeck[i] = loadDeck.nextLine() + loadDeck.nextLine();
            shuffleDeck[i] = shuffleDeck[i] + ".gif";
        }
    }

    public void printer(){
        printer.add(shuffleDeck);
    }


    public void shuffle() throws Exception {
        getFile();

        String[] shuffleDeck;
        shuffleDeck = new String[52];

        Scanner loadDeck = new Scanner(new FileReader(cardsPath));

        for (Integer i = 0; i <= 51; i++) {
            shuffleDeck[i] = loadDeck.nextLine() + loadDeck.nextLine();
        }
        Collections.shuffle(Arrays.asList(shuffleDeck));

        File cards = new File(cardsPath);
        FileWriter deckFileSave = new FileWriter(cards);

        for (Integer i = 0; i <= 51; i++) {
            deckFileSave.write(shuffleDeck[i].charAt(0));
            deckFileSave.write(System.getProperty("line.separator"));
            deckFileSave.write(shuffleDeck[i].charAt(1));
            deckFileSave.write(System.getProperty("line.separator"));
            if (i == 51) {

                deckFileSave.flush();
            }
        }

        System.out.println("Deck shuffled");
    }
}
