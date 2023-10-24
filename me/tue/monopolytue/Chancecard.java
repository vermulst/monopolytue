package me.tue.monopolytue;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chancecard extends JComponent {

    String[] stringCards = new String[5];

    String pulledCard;
    int[] balanceChange = {-100, -50, 50, 100, 150};

    public Chancecard() {
        stringCards[0] = "You have been convicted of money laundering. Pay a fine of EUR 100.";
        stringCards[1] = "You have exceeded the speed limit. Pay a fine of EUR 50.";
        stringCards[2] = "It's your birthday. You receive a gift of EUR 50.";
        stringCards[3] = "Your stocks have increased. You receive 100 EUR in dividens.";
        stringCards[4] = "You won a lawsuit. You earn 150 EUR";
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.setColor(new Color(0, 0, 0));
        g.drawRect(0, 0, 200, 100);
        g.fillRect(0, 0, 200, 100);
        g.setColor(new Color(255, 255, 255));
        g.drawString(pulledCard, 0, 100);
        System.out.println("test");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }

    public void pullChancecard() {
        int number = ThreadLocalRandom.current().nextInt(5);
        System.out.println(this.stringCards[number]);
        pulledCard = this.stringCards[number];
        this.repaint();
    }
}
