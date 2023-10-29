package me.tue.monopolytue.board;

import me.tue.monopolytue.frame.GamePanel;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.utils.Location;
import me.tue.monopolytue.utils.Vector;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Class used to represent a Monopoly TUE board.
 * The board contains squares and participants which are rendered individually.
 */
public class Board extends JPanel {

    private final GamePanel gamePanel;
    private Participant[] participants;
    private final Square[] squares;
    private final String[] squareNames;

    private static final int SQUARES_PER_ROW = 7; // must be odd for a proper board
    private static final int CHANCE_CARDS_PER_ROW = 2; // does not allow more than ((SQUARES_PER_ROW - 3) / 2)
    private static final int IMG_WIDTH = 75;


    /**
     * Constructor to create a monopoly board.
     * Places squares and creates a border.
     * @param gamePanel - Game panel to render the board onto
     */
    public Board(GamePanel gamePanel) {
        this.squareNames = new String[]{
                "Vertigo", "Matrix", "Helix", "Auditorium", "Atlas", "MetaForum", "Luna", "Neuron", "Flux", "Gemini", "Fenix",
                "Ceres", "Hubble", "Limbopad", "De Zaale", "Fontys", "TUE", "Eindhoven", "SSC", "KvK"
        };
        this.gamePanel = gamePanel;
        this.squares = new Square[(SQUARES_PER_ROW) * 4 + 4];
        this.placeSquares();
        LineBorder border1 = new LineBorder(new Color(0, 0, 0), 2, true);
        this.setBorder(border1);
        this.setLayout(new GridBagLayout());
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

        int nameIndex = 0;

        double squareChanceCardRatio = ((double) SQUARES_PER_ROW / (double) CHANCE_CARDS_PER_ROW);
        double denominator = ((-0.723 * Math.log(CHANCE_CARDS_PER_ROW)) + 2);
        double chanceCardInterval = squareChanceCardRatio / denominator;

        for (int i = 0; i < 4; i++) {
            startLocation.setDegreeRotation(i * 90);
            this.squares[i * ((SQUARES_PER_ROW) + 1)] = new Square("", startLocation.clone(), SquareGroup.CORNER);

            if (i > 1) {
                startLocation.add(vector);
            }

            for (int j = 0; j < SQUARES_PER_ROW; j++) {
                int squareIndex = i * ((SQUARES_PER_ROW) + 1) + j + 1;
                startLocation.add(vector);
                if (((j % (int) Math.round(chanceCardInterval)) == 0) && (j != 0) && (j != SQUARES_PER_ROW - 1)) {
                    this.squares[squareIndex] = new Square("", startLocation.clone(), SquareGroup.CHANCE);
                } else {
                    this.squares[squareIndex] = new Square(squareNames[nameIndex], startLocation.clone(), SquareGroup.values()[i]);
                    nameIndex++;
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null) continue;
            squares[i].render(g, this);
        }
        for (int i = 0; i < participants.length; i++) {
            if (participants[i] == null || participants[i].isBankrupt()) {
                continue;
            }
            participants[i].renderPawn(g, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = (SQUARES_PER_ROW) * IMG_WIDTH + 2 * (IMG_WIDTH * 2);
        return new Dimension(width, width);
    }


    public void setParticipants(Participant[] participants) {
        this.participants = participants;
    }


    public int getTotalSquareAmount() {
        return this.squares.length;
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