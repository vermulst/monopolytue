package me.tue.monopolytue.turn.button;

import me.tue.monopolytue.turn.Diceroller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class NextTurnButton extends JButton implements MouseListener {
    
    public Diceroller diceroller;

    public NextTurnButton(Diceroller diceroller) {
        super("Next Turn");
        this.setBackground(new Color(245, 231, 181));
        this.setForeground(new Color(5, 3, 3));
        this.setFocusPainted(false);
        this.setFont(new Font("Tahoma", Font.PLAIN, 40));

        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1,120,1,120);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);

        this.setBorder(newBorder);

        this.diceroller = diceroller;
        this.addMouseListener(this);
    }

    public void onClick() {
        if (diceroller.isRolled) {
            this.diceroller.isRolled = false;
            this.diceroller.nextTurn();
            this.diceroller.emptyDice();
            this.diceroller.getPriceCard().updateText();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 150);
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
