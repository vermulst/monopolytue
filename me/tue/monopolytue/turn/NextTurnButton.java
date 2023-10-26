package me.tue.monopolytue.turn;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class NextTurnButton extends JButton implements MouseListener {
    
    public Diceroller diceroller;

    public NextTurnButton(Diceroller diceroller) {
        super("Next Turn");
        this.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        this.diceroller = diceroller;
        this.addMouseListener(this);
    }

    public void onClick() {
        if (diceroller.isRolled) {
            this.diceroller.isRolled = false;
            this.diceroller.nextTurn();
            this.diceroller.emptyDice();
            this.diceroller.getPriceCard().update();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
 
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.onClick();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
