public class CardCheck {

    public static boolean checkMove(String firstCard, String secondCard) {
        //Checks the card number
        if (firstCard.charAt(0) == secondCard.charAt(0)) {
            return true;
        }
        //Checks the card suit
        if (firstCard.charAt(1) == secondCard.charAt(1)) {
            return true;
        }
        return false;

    }
}
