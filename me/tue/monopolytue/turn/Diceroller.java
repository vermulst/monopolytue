package me.tue.monopolytue.turn;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.utils.Position;

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

    private BufferedImage image;

    private Board board;
    public int participantIndex = 0;
    public boolean isRolled = false;

    public Diceroller(Board board) {
        this.addMouseListener(this);
        try {
            this.image = ImageIO.read(new File("images/dices/emptydice.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.board = board;
    }
    public Diceroller(Position position, Board board) {
        this.addMouseListener(this);
        try {
            this.image = ImageIO.read(new File("images/dices/emptydice.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.board = board;
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
    }

    public void nextTurn() {
        this.participantIndex++;
        if (this.participantIndex == this.board.getParticipants().length) {
            this.participantIndex = 0;
        }
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

            // Draw the first empty dice image
            g.drawImage(image1, 0, 0, this);
            // Draw the second empty dice image
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
        //this.nextTurn(); todo: nextturnbutton
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
}
