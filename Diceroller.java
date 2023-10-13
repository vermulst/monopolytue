import java.util.Random;

public class Diceroller {
    
    void determineNumber() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;
            int sumdice = dice1 + dice2;

            System.out.println(sumdice);
        }
    }

    public static void main(String[] args) {
        new Diceroller().determineNumber();
    }
}
