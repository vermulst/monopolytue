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
import me.tue.monopolytue.turn.Diceroller;
import me.tue.monopolytue.turn.participant.Participant;

/**
 * This class conducts operations when the Buybutton is pressed by user.
 */

public class BuyButton extends JButton implements MouseListener {
    public Diceroller diceroller;
    public Board board;

    /**
     * The constructor of the BuyButton class.
     * Renders the buybutton class on the right side of the panel.
     * 
     * @param board
     * @param diceroller
     */

    public BuyButton(Board board, Diceroller diceroller) {
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
     * Conducts approriate operations when player wants to buy a non occupied card,
     * such as removing balance of the corresponding player and setting the owner.
     */

    public void buyButton() {
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
        this.buyButton();
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }



}