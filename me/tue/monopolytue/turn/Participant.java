package me.tue.monopolytue.turn;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.board.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

public class Participant extends JComponent {

    private int participantID;
    public int balance = 1500;
    private int positionOnBoard = 0;
    private boolean bankrupt = false;
    private boolean turn = false;


    private static final int REWARD_PASSING_START = 200;

    public Participant(int id) {
        this.participantID = id;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
        this.repaint();
    }

    private String formatted(int number) {
        String formattedNumber = NumberFormat.getInstance().format(number);
        formattedNumber = formattedNumber.replaceAll(",", ".");
        return formattedNumber;
    }


    // draw the balance counter
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.getFont().getSize() < 40) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        }
        Graphics2D graphics2D = (Graphics2D) g;
        Color playerColor = this.getColor();


        graphics2D.setColor(playerColor);
        String playerText = "Player " + this.participantID;

        graphics2D.drawString(playerText, 10, 50);
        int width = g.getFontMetrics().stringWidth(playerText);

        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.drawString(": " + formatted(balance), 10 + width, 50);

        if (this.turn) {
            graphics2D.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
    }


    public Color getColor() {
        int rgb = this.getImage().getRGB((int) (this.getImage().getWidth() / 2.0), (int) (this.getImage().getHeight() / 2.0));
        int red = Math.abs(256 + (int) (rgb/(Math.pow(256, 2))) - 20);
        int green = Math.abs(256 + (rgb/256) % 256 - 20);
        int blue = Math.abs(256 + rgb%256 - 20);
        return new Color(red, green, blue);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 75);
    }

    //draw the pawn onto the existing board
    public void renderPawn(Graphics g, Board board) {

        BufferedImage image = this.getImage();
        Square currentSquare = this.getCurrentSquare(board);
        if (currentSquare == null) return;

        int playerIndex = 0;
        for (int i = 0; i < board.getParticipants().length; i++) {
            if (!board.getParticipants()[i].getCurrentSquare(board).equals(currentSquare)) continue;
            if (!this.equals(board.getParticipants()[i])) {
                playerIndex++;
            }
            else {
                break;
            }
        }

        int x = (int) currentSquare.getLocation().getX() + 15;
        int y = (int) currentSquare.getLocation().getY() + 25;

        for (int i = 0; i < playerIndex; i++) {
            x += this.getImage().getWidth() + 5;
            if (x - currentSquare.getLocation().getX() > currentSquare.getImage().getWidth()) {
                x = (int) currentSquare.getLocation().getX() + 15;
                y += this.getImage().getHeight() + 5;
            }
        }

        g.drawImage(image, x, y, board);
    }



    public Square getCurrentSquare(Board board) {
        Square square = board.getSquares()[this.positionOnBoard];
        return square;
    }

    public BufferedImage getImage() {
        try {
            BufferedImage image1 = ImageIO.read(new File("images/pawns/player" + this.participantID + ".png"));
            return image1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void moveSquares(Board board, int squares) {
        this.positionOnBoard += squares;
        if (this.positionOnBoard >= board.getTotalSquareAmount()) {
            this.addToBalance(REWARD_PASSING_START);
            this.positionOnBoard -= board.getTotalSquareAmount();
        }
    }

    public void addToBalance(int amount) {
        this.balance += amount;
        this.repaint();
    }

    public void removeToBalance(int amount) {
        this.balance -= amount;
        this.checkBankrupt();
        this.repaint();
    }

    public void transferAmount(Participant receiver, int amount) {
        receiver.addToBalance(amount);
        this.removeToBalance(amount);
        this.paintTransferAmount(this.getGraphics(), amount, receiver);
    }

    public void paintTransferAmount(Graphics g, int amount, Participant receiver) {
        super.paintComponent(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawString("Player " + this.participantID + "paid " + String.valueOf(amount) + " to player " + receiver.participantID, 0, 50);
    }

    public void checkBankrupt() {
        if (this.balance < 0) {
            this.bankrupt = true;
        }
    }

    public int getParticipantID() {
        return participantID;
    }
}
