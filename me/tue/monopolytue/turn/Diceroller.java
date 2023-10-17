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
        // Load the empty dice image
        try {
            Image emptyDiceImage = ImageIO.read(new File("images/emptydice.png"));
            
            // Calculate the coordinates for the two dice
            int x1 = jPanel.getWidth() - 100; 
            int y = 10;
            int x2 = jPanel.getWidth() - 50;   
            
            // Draw the first empty dice image
            g.drawImage(emptyDiceImage, x1, y, jPanel);
            
            // Draw the value of dice1 in the first dice
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 60));
            g.drawString(Integer.toString(dice1), x1 + 35, y + 35);
            
            // Draw the second empty dice image
            g.drawImage(emptyDiceImage, x2, y, jPanel);
            
            // Draw the value of dice2 in the second dice
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 60));
            g.drawString(Integer.toString(dice2), x2 + 35, y + 35);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
