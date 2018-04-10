/**
 * The Game of patience main class
 * @author Chris Loftus and Lynda Thomas
 * @version 2.0
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import uk.ac.aber.dcs.cs12320.cards.gui.javafx.CardTable;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

    private Cards Cards = new Cards();
    private Exit Exit = new Exit();
    private Scores Scores = new Scores();

    private boolean gameStarted = false;
    private boolean wrongKey = false;
    private boolean deckEmpty = false;
    private boolean deckShuffled = false;
    private boolean endGame = false;
    private boolean Quit = false;

    private String menu;
    private String firstCard = null;
    private String secondCard = null;

    private Integer firstCardPlace = null;
    private Integer secondCardPlace = null;
    private Integer cardLocation = 0;

    private CardTable cardTable;

    private ArrayList<String> cardStrings = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        try {
            Scores.loadScores();
        } catch (Exception e) {}
        cardTable = new CardTable(stage);

        // The interaction with this game is from a command line
        // menu. We need to create a separate non-GUI thread
        // to run this in. DO NOT REMOVE THIS.
        Runnable commandLineTask = () -> {
            // REPLACE THE FOLLOWING EXAMPLE WITH YOUR CODE

            while(!Quit) {

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

                menu = input.next();
                menu = menu.toUpperCase();

                switch(menu){

                    case "1":
                        if(gameStarted) {
                            System.err.println("You can't shuffle the deck once the game has started!");
                            break;
                        } else if (!gameStarted){
                            try {
                                deckShuffled = true;
                                Cards.shuffle();
                                Cards.load();
                            } catch (Exception e){
                                System.err.println("Cards.txt could not be read");
                            }
                        }
                        break;

                    case "2":
                        try {
                            //The method to print the deck
                            Cards.printer();
                            cardTable.cardDisplay(Cards.printer);
                            System.out.println("Press Enter to go back");
                            try{System.in.read();}
                            catch(Exception e){}
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;

                    case "3":
                        if (!deckShuffled){
                            System.err.println("Shuffle the deck first!");
                        } else if (cardLocation == 52){
                            deckEmpty = true;
                        } else if (endGame){
                            System.out.println("Game over, you can't draw any more cards!");
                        } else if (deckShuffled && !deckEmpty && !endGame) {
                            String currentCard = Cards.shuffleDeck[cardLocation];
                            cardStrings.add(currentCard);
                            cardLocation++;
                            gameStarted = true;
                        } else if (deckEmpty){
                            System.out.println("All the cards from the deck have been drawn");
                    }
                        break;

                    case "4":
                        if(!gameStarted){
                                System.err.println("You can't make a move until you draw a card!");
                        }else {
                            try {
                                firstCard = cardStrings.get(cardStrings.size() - 1);

                                secondCard = cardStrings.get(cardStrings.size() - 2);

                                secondCardPlace = cardStrings.size() - 2;

                                makeMove(secondCardPlace, firstCard, secondCard);

                                break;
                            } catch (Exception e){
                                System.err.println("Can't make that move, try drawing another card");
                                break;
                            }
                        }

                    case "5":
                        if(!gameStarted){
                            System.err.println("You can't make a move until you draw a card!");
                        }else {
                            try {
                                firstCard = cardStrings.get(cardStrings.size() - 1);

                                secondCard = cardStrings.get(cardStrings.size() - 3);

                                secondCardPlace = cardStrings.size() - 3;

                                makeMove(secondCardPlace, firstCard, secondCard);

                                break;
                            } catch (Exception e) {
                                System.err.println("Can't make that move, try drawing another card");
                                break;
                            }
                        }

                    case "6":
                        if (!gameStarted) {
                            System.err.println("A move can't be made until you draw a card!");
                            break;
                        } else {
                            try {
                                Scanner amalgamation = new Scanner(System.in);
                                System.out.println("Which pile would you like to move?");
                                firstCardPlace = Integer.parseInt(amalgamation.next()) - 1;
                                firstCard = cardStrings.get(firstCardPlace);
                                System.out.println("Where would you like that pile to go?");
                                secondCardPlace = Integer.parseInt(amalgamation.next()) - 1;
                                secondCard = cardStrings.get(secondCardPlace);

                                if (firstCard == secondCard || firstCardPlace < secondCardPlace) {
                                    System.err.println("That move is not valid!");
                                    break;
                                } else {
                                    makeMove(secondCardPlace, firstCard, secondCard);
                                    break;
                                }
                            } catch (Exception e) {
                                System.err.println("That move is not valid!");
                                break;
                            }
                        }

                    case "7":
                        if (cardStrings.size() < 1) {
                            System.out.println("There are no cards on the table!");
                            break;
                        } else {
                            System.out.println("The cards currently on table are:");
                            for (Integer i = 0; i < cardStrings.size(); i++) {
                                String card = cardStrings.get(i).replace(".gif", "");
                                System.out.print(card + "  ");
                            }
                            System.out.println();
                            break;
                        }

                    case "8":

                        break;

                    case "9":

                        break;

                    case "10":
                        Scores.printScores();
                        break;

                    case "Q":
                        endGame = true;
                        Quit = true;
                        cardTable.allDone();
                        Exit.exit(cardStrings.size(), cardLocation);
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

    private void makeMove(Integer cardLocation, String firstCard, String secondCard) {
        boolean check = CardCheck.checkMove(firstCard, secondCard);
        if (firstCard == null || secondCard == null) {
            System.err.println("That move can't be made!");
        }else if (!check) {
            System.err.println("That move can't be made!");
        }else {
            cardStrings.remove(firstCard);
            cardStrings.set(cardLocation, firstCard);
            cardTable.cardDisplay(cardStrings);
            System.out.println(firstCard.replace(".gif", "") + " was moved over " + secondCard.replace(".gif", ""));
            nullify();
        }
    }

    private void nullify() {
        firstCard = null;
        secondCard = null;
        firstCardPlace = null;
        secondCardPlace = null;
    }

}