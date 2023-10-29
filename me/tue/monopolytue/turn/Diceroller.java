package me.tue.monopolytue.turn;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.turn.participant.AIOpponent;
import me.tue.monopolytue.turn.participant.Participant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Diceroller extends JComponent implements MouseListener {

    private int dice1 = 0;
    private int dice2 = 0;

    private final BufferedImage image;

    private final Board board;
    private int participantIndex = 0;
    private boolean isRolled = false;

    private PriceCard priceCard;

    public Diceroller(Board board) {
        this.addMouseListener(this);
        try {
            this.image = ImageIO.read(new File("images/dices/emptydice.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.board = board;
    }

    public void emptyDice() {
        this.dice1 = 0;
        this.dice2 = 0;
        this.repaint();
    }

    public void setPriceCard(PriceCard priceCard) {
        this.priceCard = priceCard;
    }

    public PriceCard getPriceCard() {
        return priceCard;
    }

    public void rollDice() {
        if (this.isRolled) return;
        Random random = new Random();
        this.dice1 = random.nextInt(6) + 1;
        this.dice2 = random.nextInt(6) + 1;
        Participant participant = this.board.getParticipants()[this.participantIndex];
        participant.moveSquares(this.board, this.getSum());
        this.board.repaint();
        participant.getCurrentSquare(this.board).onLand(this.board, participant);
        this.isRolled = true;
        this.getPriceCard().updateText();
    }

    public void nextTurn() {
        if (this.checkGameOver()) {
            return;
        }
        Participant previousParticipant = this.board.getParticipants()[this.participantIndex];
        this.participantIndex++;
        if (this.participantIndex == this.board.getParticipants().length) {
            this.participantIndex = 0;
        }
        Participant newParticipant = this.board.getParticipants()[this.participantIndex];
        previousParticipant.setTurn(false);

        if (newParticipant.isBankrupt()) {
            this.nextTurn();
            return;
        }

        newParticipant.setTurn(true);
        if (newParticipant instanceof AIOpponent aiOpponent) {
            aiOpponent.executeTurn(this);
        }
    }

    public boolean checkGameOver() {
        int participantsLeft = 0;
        for (int i = 0; i < this.getBoard().getParticipants().length; i++) {
            if (!this.board.getParticipants()[i].isBankrupt()) {
                participantsLeft++;
            }
        }
        if (participantsLeft < 2) {
            for (Participant participant : this.getBoard().getParticipants()) {
                if (!participant.isBankrupt()) {
                    this.board.getGamePanel().stop(participant);
                }
            }
            return true;
        }
        return false;
    }



    int getSum() {
        return this.dice1 + this.dice2;
    }


    @Override
    public void paintComponent(Graphics g) {
        try {
            if (this.getDice1() == 0) {
                // Draw the first empty dice image
                g.drawImage(this.image, 0, 0, this);
                // Draw the second empty dice image
                g.drawImage(this.image, image.getWidth(), 0, this);
                return;
            }
            BufferedImage image1 = ImageIO.read(new File("images/dices/Dice-" + this.getDice1() + "-b.svg.png"));
            BufferedImage image2 = ImageIO.read(new File("images/dices/Dice-" + this.getDice2() + "-b.svg.png"));

            g.drawImage(image1, 0, 0, this);
            g.drawImage(image2, image.getWidth(), 0, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getImage().getWidth() * 2, this.getImage().getHeight());
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        this.rollDice();
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Board getBoard() {
        return board;
    }

    public int getParticipantIndex() {
        return participantIndex;
    }

    public boolean isRolled() {
        return isRolled;
    }

    public void setRolled(boolean rolled) {
        isRolled = rolled;
    }
}
