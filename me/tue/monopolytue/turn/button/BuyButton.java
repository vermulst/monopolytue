package me.tue.monopolytue.turn.button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.board.Square;
import me.tue.monopolytue.turn.DiceRoller;
import me.tue.monopolytue.turn.participant.Participant;

/**
 * This class conducts operations when the Buy button is pressed by user.
 */
public class BuyButton extends JButton implements MouseListener {
    public DiceRoller diceroller;
    public Board board;

    /**
     * The constructor of the BuyButton class.
     * Creates a buy button to buy squares on the board.
     * 
     * @param board - the board where the buy button can buy squares from.
     * @param diceroller - a die roller object to know which participant is buying.
     */
    public BuyButton(Board board, DiceRoller diceroller) {
        super("Buy Square");

        this.setBackground(new Color(245, 231, 181));
        this.setForeground(new Color(5, 3, 3));


        this.setFocusPainted(false);
        this.setFont(new Font("Tahoma", Font.PLAIN, 40));

        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1,120,1,120);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);

        this.setBorder(newBorder);

        this.diceroller = diceroller;
        this.board = board;
        this.addMouseListener(this);

    }

    /**
     * Attempts to buy square if the player rolled the dice and the square has no owner.
     */
    public void onClick() {
        if (!this.diceroller.isRolled()) {
            return;
        }
        Participant participant = board.getParticipants()[this.diceroller.getParticipantIndex()];
        Square square = participant.getCurrentSquare(board);
        if (square.getOwner() != null) {
            return;
        }
        participant.buySquare(square, board);
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