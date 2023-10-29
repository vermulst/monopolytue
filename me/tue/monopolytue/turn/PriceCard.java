package me.tue.monopolytue.turn;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.board.Square;
import me.tue.monopolytue.turn.participant.Participant;

/**
 * Renders the price of the card where the player is located.
 * Also displays other information when appropriate.
 */
public class PriceCard extends JLabel {


    public DiceRoller diceroller;
    public Board board;

    /**
     * Creates a PriceCard label.
     * @param diceroller - A DiceRoller object
     * @param board - A board object
     */
    public PriceCard(DiceRoller diceroller, Board board) {
        super("");
        this.setMinimumSize(this.getPreferredSize());
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        this.setOpaque(true);
        this.setBackground(new Color(245, 231, 181));
        this.setForeground(new Color(5, 3, 3));

        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1, 50, 1, 50);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);

        this.setBorder(newBorder);

        this.setFont(new Font("Tahoma", Font.PLAIN, 40));

        this.diceroller = diceroller;
        this.board = board;
    }

    /**
     * Updates the text of the label when called.
     */
    public void updateText() {
        int price = this.getPrice();
        if (price == 0) {
            this.setText("");
            return;
        }
        Participant owner = this.getCurrentSquare().getOwner();
        Participant participant = this.getCurrentParticipant();
        if (owner != null) {
            if (participant.equals(owner)) {
                this.setText("This is your square");
            } else {
                this.setFont(new Font("Tahoma", Font.PLAIN, 20));
                Square square = this.getCurrentSquare();
                int rent = square.getSquareGroup().getRent();
                this.setText("<html>Square owned by player " + owner.getParticipantID()
                        + "<br>Rent payed: " + rent + "</br></html>");
            }
            return;
        }
        this.setText("Price: " + this.getPrice());
    }


    public Participant getCurrentParticipant() {
        return this.board.getParticipants()[this.diceroller.getParticipantIndex()];
    }

    /**
     * Gets the price of the current square.
     * @return int price.
     */
    public int getPrice() {
        if (!this.diceroller.isRolled()) {
            return 0;
        }
        Square square = this.getCurrentSquare();
        return square.getSquareGroup().getPrice();
    }

    /**
     * Returns the current square where the current participant is located.
     * @return square.
     */
    public Square getCurrentSquare() {
        Participant participant = this.getCurrentParticipant();
        return participant.getCurrentSquare(this.board);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 150);
    }
}