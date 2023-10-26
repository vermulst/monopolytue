package me.tue.monopolytue.turn;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import me.tue.monopolytue.board.SquareGroup;

public class PriceCard extends JComponent {
    
    SquareGroup squareGroup;

    public PriceCard(int price) {

    }

    public void showPriceCard(int price, Graphics gPrice) {
        super.paintComponent(gPrice);

        Graphics2D graphics2D = (Graphics2D) gPrice;
        graphics2D.drawString("Price card: ", price, price);
    }
    
}
