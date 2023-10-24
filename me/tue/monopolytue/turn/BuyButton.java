package me.tue.monopolytue.turn;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.board.Square;
import me.tue.monopolytue.board.SquareGroup;

public class BuyButton extends JButton implements MouseListener {
    Diceroller diceroller;
    Board board;

    public BuyButton(Board board, Diceroller diceroller) {
        super("Buy Card");
        this.diceroller = diceroller;
        this.addMouseListener(this);
        this.board = board;
    }

    void buyButton() {
        Participant participant = board.getParticipants()[this.diceroller.participantIndex];
        Square square = participant.getCurrentSquare(board);
        if (square.getOwner() != null) {
            return;
        }

        if (participant.balance >= square.getSquareGroup().getPrice()) {
            participant.removeToBalance(square.getSquareGroup().getPrice());
            square.setOwner(participant);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        this.buyButton();
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