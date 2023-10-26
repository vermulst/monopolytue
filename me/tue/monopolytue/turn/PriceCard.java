package me.tue.monopolytue.turn;
import java.awt.*;
import java.text.NumberFormat;

import javax.swing.JComponent;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.board.Square;
import me.tue.monopolytue.board.SquareGroup;

public class PriceCard extends JComponent {


    public Diceroller diceroller;
    public Board board;

    public PriceCard(Diceroller diceroller, Board board) {
        this.diceroller = diceroller;
        this.board = board;
    }

    public void update() {
        this.repaint();
    }


    public Participant getCurrentParticipant() {
        return this.board.getParticipants()[this.diceroller.participantIndex];
    }

    public int getPrice() {
        if (!this.diceroller.isRolled) return 0;
        Square square = this.getCurrentSquare();
        return square.getSquareGroup().getPrice();
    }

    public Square getCurrentSquare() {
        Participant participant = this.getCurrentParticipant();
        return participant.getCurrentSquare(this.board);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 100);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        Graphics2D graphics2D = (Graphics2D) g;
        int price = this.getPrice();
        if (price == 0) return;
        graphics2D.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        Participant owner = this.getCurrentSquare().getOwner();
        if (owner != null) {
            if (!this.getCurrentSquare().equals(owner)) {
                Square square = this.getCurrentSquare();
                Participant participant = this.getCurrentSquare().getOwner();
                int rent = square.getSquareGroup().getRent();
                graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 28));
                graphics2D.setColor(Color.RED);
                graphics2D.drawString("Square owned by player " + participant.getParticipantID(), 5, this.getHeight() - 60);
                graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString("Rent payed: " + rent, 5, this.getHeight() - 20);
                return;
            } else {
                graphics2D.drawString("This is your square", 5, this.getHeight() - 50);
                return;
            }
        }
        graphics2D.drawString("Price: " + this.getPrice(), 5, this.getHeight() - 50);
    }
}
