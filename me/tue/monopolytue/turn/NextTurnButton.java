package me.tue.monopolytue.turn;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class NextTurnButton extends JButton implements MouseListener {
    
    Diceroller diceroller;

    public NextTurnButton(Diceroller diceroller) {
        super("Next Turn");
        this.diceroller = diceroller;
        this.addMouseListener(this);
    }

    void onClick() {
        if (diceroller.isRolled) {
            this.diceroller.isRolled = false;
            this.diceroller.nextTurn();    
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
 
    @Override
    public void mouseClicked(MouseEvent e) {
        this.onClick();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
