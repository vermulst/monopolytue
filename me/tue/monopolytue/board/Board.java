package me.tue.monopolytue.board;

import me.tue.monopolytue.frame.GamePanel;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.utils.Location;
import me.tue.monopolytue.utils.Vector;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Board extends JPanel {


    private Square[] squares;

    // must be odd for a proper board
    private static final int SQUARES_PER_ROW = 7;

    // does not allow more than ((SQUARES_PER_ROW - 3) / 2)
    private static final int CHANCE_CARDS_PER_ROW = 2;

    private static final int IMG_WIDTH = 75;

    private Participant[] participants;

    private GamePanel gamePanel;

    /**
     * Rendering the gaming board.
     * 
     * @param gamePanel 
     */

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.squares = new Square[(SQUARES_PER_ROW) * 4 + 4];
        this.placeSquares();

        LineBorder border1 = new LineBorder(new Color(0, 0, 0), 2, true);
        this.setBorder(border1);
        this.setLayout(new GridBagLayout());
    }

    public void setParticipants(Participant[] participants) {
        this.participants = participants;
    }


    public int getTotalSquareAmount() {
        return this.squares.length;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null) continue;
            squares[i].render(g, this);
        }
        for (int i = 0; i < participants.length; i++) {
            if (participants[i] == null) continue;
            participants[i].renderPawn(g, this);
        }
    }

    /**
     * Placing the squares on the board.
     */

    public void placeSquares() {
        Vector vector = new Vector(-IMG_WIDTH, 0);
        Vector vector1 = new Vector(-(IMG_WIDTH * 2), 0);
        Dimension bottomRightCoordinate = this.getPreferredSize();
        int startX = (int) (bottomRightCoordinate.getWidth() - (IMG_WIDTH * 2));
        int startY = (int) (bottomRightCoordinate.getHeight() - (IMG_WIDTH * 2));
        Location startLocation = new Location(startX, startY);


        double chanceCardInterval = ((double) SQUARES_PER_ROW / (double) CHANCE_CARDS_PER_ROW) / ((-0.723 * Math.log(CHANCE_CARDS_PER_ROW)) + 2);

        for (int i = 0; i < 4; i++) {
            startLocation.setDegreeRotation(i * 90);
            this.squares[i * ((SQUARES_PER_ROW) + 1)] = new Square(startLocation.clone(), SquareGroup.CORNER);

            if (i > 1) {
                startLocation.add(vector);
            }

            for (int j = 0; j < SQUARES_PER_ROW; j++) {
                startLocation.add(vector);
                if (((j % (int) Math.round(chanceCardInterval)) == 0) && (j != 0) && (j != SQUARES_PER_ROW - 1)) {
                    this.squares[i * ((SQUARES_PER_ROW) + 1) + j + 1] = new Square(startLocation.clone(), SquareGroup.CHANCE);
                } else {
                    this.squares[i * ((SQUARES_PER_ROW) + 1) + j + 1] = new Square(startLocation.clone(), SquareGroup.values()[i]);
                }
            }

            if (i > 1) {
                startLocation.add(vector);
            } else {
                startLocation.add(vector1);
            }
            vector1.rotateBy90CounterClockWise();
            vector.rotateBy90CounterClockWise();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = (SQUARES_PER_ROW) * IMG_WIDTH + 2 * (IMG_WIDTH * 2);
        return new Dimension(width, width);
    }


    public Square[] getSquares() {
        return squares;
    }

    public Participant[] getParticipants() {
        return participants;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}