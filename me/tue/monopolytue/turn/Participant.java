package me.tue.monopolytue.turn;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.board.Square;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Participant {

    private int participantID;
    private int balance;
    private int positionOnBoard = 0;
    private boolean bankrupt = false;

    //todo: add image
    private static final int IMG_WIDTH = 30;
    private static final int IMG_HEIGHT = 60;

    public Participant(int id) {
        this.participantID = id;
    }

    public void moveSquares(Board board, int squares) {
        this.positionOnBoard += squares;
        if (this.positionOnBoard >= board.getTotalSquareAmount()) {
            this.positionOnBoard -= board.getTotalSquareAmount();
        }
    }



    public void render(Graphics g, Board board) {

        BufferedImage image = this.getImage();
        Square currentSquare = this.getCurrentSquare(board);
        if (currentSquare == null) return;

        int playerIndex;
        for (int i = 0; i < board.getParticipants().length; i++) {
            if (this.equals(board.getParticipants()[i])) {
                playerIndex = i;
                break;
            }
        }

        
        int balanceX = 10;

        int x = (int) currentSquare.getLocation().getX() + 5;
        int y = (int) currentSquare.getLocation().getY() + 30;

        for (int i = 0; i < board.getParticipants().length; i++) {
            if (board.getParticipants()[i].positionOnBoard != this.positionOnBoard) continue;
            if (this.equals(board.getParticipants()[i])) {
                g.drawImage(image, x, y, board);
                break;
            } else {
                x += this.getImage().getWidth() + 5;
                if (x - currentSquare.getLocation().getX() > currentSquare.getImage().getWidth()) {
                    x = (int) currentSquare.getLocation().getX() + 5;
                    y += this.getImage().getHeight() + 5;
                }
            }
        }
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


    public void addToBalance(int amount) {
        this.balance += amount;
    }

    public void removeToBalance(int amount) {
        this.balance -= amount;
        this.checkBankrupt();
    }

    public void transferAmount(Participant receiver, int amount) {
        receiver.addToBalance(amount);
        this.removeToBalance(amount);
    }

    public void checkBankrupt() {
        if (this.balance < 0) {
            this.bankrupt = true;
        }
    }
    
}
