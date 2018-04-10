import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Scores {

    private String[][] scores = new String[3][10];
    public String scoresPath;

    public void loadScores() throws FileNotFoundException {
        scoresPath = System.getProperty("user.dir") + "\\src\\sc.txt";

        Scanner readScores = new Scanner(new FileReader(scoresPath));

        for(int j = 0; j <= 9; j++){
            for(int k = 0; k <= 2; k++){
                scores[k][j] = (readScores.nextLine());
            }
        }
    }
    
    public void saveScores(String path, String nick, Integer piles, Integer cardsLeft) throws Exception {

        String[] pileArray;
        String[] cardsLeftArray;
        String[] namesArray;
        pileArray = new String[10];
        cardsLeftArray = new String[10];
        namesArray = new String[10];

        //Copies the existing text file contents into the appropriate arrays
        for(Integer i = 0; i <=9 ; i++){
            namesArray[i] = scores[0][i];
            pileArray[i] = scores[1][i];
            cardsLeftArray[i] = scores[2][i];
        }

        for (Integer i = 0; i <=9; i++) {
            //Moves the results lower down the list if the current one is higher
            if (piles > Integer.parseInt(pileArray[i]) || cardsLeft < Integer.parseInt(cardsLeftArray[i]) || namesArray[i] == null) {
                try {
                    pileArray[i + 9] = pileArray[i + 8];
                    namesArray[i + 9] = namesArray[i + 8];
                    cardsLeftArray[i + 9] = cardsLeftArray[i + 8];
                    namesArray[i + 8] = namesArray[i + 7];
                    pileArray[i + 8] = pileArray[i + 7];
                    cardsLeftArray[i + 8] = cardsLeftArray[i + 7];
                    namesArray[i + 7] = namesArray[i + 6];
                    pileArray[i + 7] = pileArray[i + 6];
                    cardsLeftArray[i + 7] = cardsLeftArray[i + 6];
                    namesArray[i + 6] = namesArray[i + 5];
                    pileArray[i + 6] = pileArray[i + 5];
                    cardsLeftArray[i + 6] = cardsLeftArray[i + 5];
                    namesArray[i + 5] = namesArray[i + 4];
                    pileArray[i + 5] = pileArray[i + 4];
                    cardsLeftArray[i + 5] = cardsLeftArray[i + 4];
                    namesArray[i + 4] = namesArray[i + 3];
                    pileArray[i + 4] = pileArray[i + 3];
                    cardsLeftArray[i + 4] = cardsLeftArray[i + 3];
                    namesArray[i + 3] = namesArray[i + 2];
                    pileArray[i + 3] = pileArray[i + 2];
                    cardsLeftArray[i + 3] = cardsLeftArray[i + 2];
                    namesArray[i + 2] = namesArray[i + 1];
                    pileArray[i + 2] = pileArray[i + 1];
                    cardsLeftArray[i + 2] = cardsLeftArray[i + 1];
                    namesArray[i + 1] = namesArray[i];
                    pileArray[i + 1] = pileArray[i];
                    cardsLeftArray[i + 1] = cardsLeftArray[i];
                    pileArray[i] = piles.toString();
                    cardsLeftArray[i] = cardsLeft.toString();
                    namesArray[i] = nick;
                } catch (Exception e) {
                    break;
                }

            }
        }
        for(int l = 0; l<=9; l++) {
            System.out.println(namesArray[l]);
            System.out.println(pileArray[l]);
            System.out.println(cardsLeftArray[l]);
        }
        try {
            File scores = new File(scoresPath);
            FileWriter saveScores = new FileWriter(scores);
            for (Integer i = 0; i <= 9; i++) {
                //Writes the score and players name into the text file line by line
                saveScores.write(namesArray[i]);
                saveScores.write(System.getProperty("line.separator"));
                saveScores.write(pileArray[i].toString());
                saveScores.write(System.getProperty("line.separator"));
                saveScores.write(cardsLeftArray[i].toString());
                saveScores.write(System.getProperty("line.separator"));
                if (i == 9) {
                    //saves the text file when done
                    saveScores.flush();
                }
            }
        } catch(Exception e){
            System.err.println("Scores.txt couldn't be found!");
        }
    }

    public void printScores() {
        try {
            //Finds the location of the high score text file
            for (Integer i = 0; i < 10; i++) {
                if(scores[0][i] != null) {
                    //Prints the top 10 scores out on to the console
                    System.out.println("#" + (i + 1) + ": " + scores[0][i] + ", " + scores[1][i] + " piles, " + scores[2][i] + " cards");
                }
            }
        } catch (Exception e) {
            System.err.println("Scores.txt could not be read");
        }

    }

}
