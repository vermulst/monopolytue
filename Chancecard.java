import java.util.Random;

public class Chancecard {
    Random random = new Random(0);

    String[] stringCards = new String[5]; 

    stringCards[0] = "You have been convicted of money laundering. Pay a fine of EUR 100.";
    stringCards[1] = "You have exceeded the speed limit. Pay a fine of EUR 50.";
    stringCards[2] = "It's your birthday. You receive a gift of EUR 50.";
    stringCards[3] = "Your stocks have increased. You receive a gift of EUR 150.";
    stringCards[4] = "jhkj"

    void pullChancecard() {
        int number = random.nextInt(5);



    }
}

public static void main(String[] args) {
    new Chancecard().pullChancecard();
}