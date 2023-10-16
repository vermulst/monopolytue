package me.tue.monopolytue.turn;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Diceroller {

    private int dice1 = 0;
    private int dice2 = 0;

    public Diceroller() {

    }

    void rollDice() {
        Random random = new Random();
        this.dice1 = random.nextInt(6) + 1;
        this.dice2 = random.nextInt(6) + 1;
    }

    int getSum() {
        return this.dice1 + this.dice2;
    }


    public void render(Graphics g, JPanel jPanel) {
        //todo:
        File file = new File("images/emptydice.png");
        try {
            Image image = ImageIO.read(file);
            g.drawImage(image, 1700, 0, jPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
