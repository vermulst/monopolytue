package me.tue.monopolytue.turn.button;

import me.tue.monopolytue.turn.Diceroller;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.turn.participant.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Button which is used to pass on the turn when clicked.
 */

public class NextTurnButton extends JButton implements MouseListener {
    
    private final Diceroller diceroller;

    /**
     * Constructs the next turn button.
     * @param diceroller
     */
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

    /**
     * Passes on the turn when the button is pressed
     * Checks if the player already rolled the dice
     */
    public void onClick() {
        if (diceroller.isRolled()) {
            diceroller.setRolled(false);
            int indexNew = this.diceroller.getParticipantIndex() + 1;
            if (indexNew == diceroller.getBoard().getParticipants().length) {
                indexNew--;
            }
            Participant oldParticipant = diceroller.getBoard().getParticipants()[this.getDiceroller().getParticipantIndex()];
            Participant newParticipant = diceroller.getBoard().getParticipants()[indexNew];
            // Must only work for the player, not AIOpponents
            if (oldParticipant instanceof Player || newParticipant instanceof Player) {
                diceroller.nextTurn();
                diceroller.emptyDice();
                diceroller.getPriceCard().updateText();
            }
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

    public Diceroller getDiceroller() {
        return diceroller;
    }
}
