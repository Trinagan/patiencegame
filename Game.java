/**
 * The Game of patience main class
 * @author Chris Loftus and Lynda Thomas
 * @version 2.0
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.stage.Stage;
import uk.ac.aber.dcs.cs12320.cards.gui.javafx.CardTable;

import static java.lang.Thread.sleep;

public class Game extends Application {

    private CardTable cardTable;
    private boolean gameStarted = false;
    private boolean wrongKey = false;
    private boolean Exit = false;
    private int i = 2;
    private int j = 0;

    private ArrayList<String> cardStrings = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        cardTable = new CardTable(stage);

        // The interaction with this game is from a command line
        // menu. We need to create a separate non-GUI thread
        // to run this in. DO NOT REMOVE THIS.
        Runnable commandLineTask = () -> {
            // REPLACE THE FOLLOWING EXAMPLE WITH YOUR CODE

            while(Exit==false) {

                cardTable.cardDisplay(cardStrings);

                System.out.println("------------------------------------");
                System.out.println("1 - Shuffle the deck");
                System.out.println("2 - Show the pack");
                System.out.println("3 - Deal a card");
                System.out.println("4 - Make a move, move last pile onto previous one");
                System.out.println("5 - Make a move, move last pile back over two piles");
                System.out.println("6 - Amalgamate piles in the middle (by giving their numbers)");
                System.out.println("7 - Print the displayed cards on the command line");
                System.out.println("8 - Play for me once (if two possible moves, makes the ‘furthest’ move)");
                System.out.println("9 - Play for me many times");
                System.out.println("10 - Display top 10 results");
                System.out.println("Q - Quit");
                System.out.println("------------------------------------");

                if(wrongKey){
                    System.out.println("Incorrect choice! Try again!");
                }

                wrongKey=false;

                Scanner input = new Scanner(System.in);

                String menu = input.next();

                ArrayList<Character> num = new ArrayList<>();
                ArrayList<Character> col = new ArrayList<>();


                switch(menu){

                    case "1":
                        if(gameStarted) {
                            System.err.println("You can't shuffle the deck once the game has started!");
                            break;
                        } else if (!gameStarted){
                            try {
                                Cards.shuffle();
                                Cards.load();
                            } catch (Exception e){
                                System.err.println("Cards.txt could not be read.");
                            }
                        }
                        break;

                    case "2":
                        try {
                            //The method to print the deck
                            Cards.printer();
                            cardTable.cardDisplay(Cards.shuffleDeck());
                            System.out.println("Press Enter to go back.");
                            try{System.in.read();}
                            catch(Exception e){}
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                    case "3":
                        cardStrings.add(shuffleDeck.get(j));
                        j++;
                        break;

                    case "4":
                        if(num.get(1) == num.get(2) || col.get(1) == col.get(2)){
                            cardStrings.set(j - 2, shuffleDeck.get(j - 1));
                            cardStrings.remove(j - 1);
                        }else{
                            System.out.println("Can't make that move!");
                        }

                        break;

                    case "5":
                        if(num.get(1) == num.get(4) || col.get(1) == col.get(4)) {
                            cardStrings.set(j - 4, shuffleDeck.get(j - 1));
                            cardStrings.remove(j - 1);
                        }else{
                            System.out.println("Can't make that move!");
                        }
                        break;

                    case "6":

                        break;

                    case "7":

                        break;

                    case "8":

                        break;

                    case "9":

                        break;

                    case "10":

                        break;

                    case "q":
                        Exit = true;
                        break;

                    case "Q":
                        Exit = true;
                        break;

                    default:
                        wrongKey=true;
                        break;

                }

            }
        };
        Thread commandLineThread = new Thread(commandLineTask);
        // This is how we start the thread.
        // This causes the run method to execute.
        commandLineThread.start();
    }

    // //////////////////////////////////////////////////////////////
    public static void main(String args[]) {
        //Game game = new Game();
        //game.playGame();
        Application.launch(args);
    }
}