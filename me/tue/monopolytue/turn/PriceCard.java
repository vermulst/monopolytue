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
 * Renders the price of the card where player is located.
 * 
 */

public class PriceCard extends JLabel {


    public Diceroller diceroller;
    public Board board;

    /**
     * Renders the price of the card where player is located when neccesarry. 
     * @param diceroller
     * @param board
     */

    public PriceCard(Diceroller diceroller, Board board) {
        super("");
        this.setMinimumSize(this.getPreferredSize());
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        this.setOpaque(true);
        this.setBackground(new Color(245, 231, 181));
        this.setForeground(new Color(5, 3, 3));

        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1,120,1,120);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);

        this.setBorder(newBorder);

        this.setFont(new Font("Tahoma", Font.PLAIN, 40));

        this.diceroller = diceroller;
        this.board = board;
    }

    /**
     * Updates the price of the card accordingly.
     */

    public void updateText() {
        int price = this.getPrice();
        if (price == 0) {
            this.setText("");
        }
        Participant owner = this.getCurrentSquare().getOwner();
        if (owner != null) {
            if (!this.getCurrentSquare().getOwner().equals(owner)) {
                Square square = this.getCurrentSquare();
                Participant participant = this.getCurrentSquare().getOwner();
                int rent = square.getSquareGroup().getRent();
                this.setText("Square owned by player " + participant.getParticipantID()
                        + "\n" + "Rent payed: " + rent);
            } else {
                this.setText("This is your square");
                return;
            }
        }
        this.setText("Price: " + this.getPrice());
    }


    public Participant getCurrentParticipant() {
        return this.board.getParticipants()[this.diceroller.participantIndex];
    }

    /**
     * Gets the price of the current square
     * @return
     */

    public int getPrice() {
        if (!this.diceroller.isRolled) return 0;
        Square square = this.getCurrentSquare();
        return square.getSquareGroup().getPrice();
    }

    /**
     * Returns the current square where pawn is located.
     * @return
     */

    public Square getCurrentSquare() {
        Participant participant = this.getCurrentParticipant();
        return participant.getCurrentSquare(this.board);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 150);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*g.setFont(new Font("Tahoma", Font.PLAIN, 40));
        Graphics2D graphics2D = (Graphics2D) g;
        int price = this.getPrice();
        if (price == 0) return;

        int borderWidth = 3;
        graphics2D.setColor(new Color(253, 198, 86));
        graphics2D.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        graphics2D.setColor(new Color(245, 231, 181));
        graphics2D.fillRect(borderWidth, borderWidth, this.getWidth() - borderWidth - 1, this.getHeight() - borderWidth - 1);

        graphics2D.setColor(new Color(0, 0, 0));
        Participant owner = this.getCurrentSquare().getOwner();
        if (owner != null) {
            if (!this.getCurrentSquare().getOwner().equals(owner)) {
                Square square = this.getCurrentSquare();
                Participant participant = this.getCurrentSquare().getOwner();
                int rent = square.getSquareGroup().getRent();
                graphics2D.setFont(new Font("Tahoma", Font.PLAIN, 40));
                graphics2D.setColor(Color.RED);
                graphics2D.drawString("Square owned by player " + participant.getParticipantID(), 5, this.getHeight() - 60);
                graphics2D.setFont(new Font("Tahoma", Font.PLAIN, 20));
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString("Rent payed: " + rent, 5, this.getHeight() - 20);
                return;
            } else {
                graphics2D.drawString("This is your square", 5, this.getHeight() - 50);
                return;
            }
        }
        graphics2D.drawString("Price: " + this.getPrice(), 5, this.getHeight() - 50);*/
    }
}
