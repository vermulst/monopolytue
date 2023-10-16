package me.tue.monopolytue.board;

import me.tue.monopolytue.utils.Location;
import me.tue.monopolytue.utils.Vector;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Board {

    private Square[] squares;
    private static final int SQUARES_PER_ROW = 6;
    private static final int CHANCE_CARDS_PER_ROW = 1;

    public Board() {
        this.squares = new Square[(SQUARES_PER_ROW + CHANCE_CARDS_PER_ROW) * 4 + 4];
        this.placeSquares();
    }

    public void placeSquares() {
        Vector vector = new Vector(-75, 0);
        Vector vector1 = new Vector(-150, 0);
        Location startLocation = new Location(1500, 800);
        for (int i = 0; i < 4; i++) {
            startLocation.setDegreeRotation(i * 90);
            this.squares[i * ((SQUARES_PER_ROW + CHANCE_CARDS_PER_ROW) + 1)] = new Square(startLocation.clone(), SquareGroup.CORNER);

            if (i > 1) {
                startLocation.add(vector);
            }

            for (int j = 0; j < SQUARES_PER_ROW + CHANCE_CARDS_PER_ROW; j++) {
                if (CHANCE_CARDS_PER_ROW == 1 && j == SQUARES_PER_ROW / 2) {
                    startLocation.add(vector);
                    this.squares[i * ((SQUARES_PER_ROW + CHANCE_CARDS_PER_ROW) + 1) + j + 1] = new Square(startLocation.clone(), SquareGroup.CHANCE);
                    j++;
                }
                startLocation.add(vector);
                this.squares[i * ((SQUARES_PER_ROW + CHANCE_CARDS_PER_ROW) + 1) + j + 1] = new Square(startLocation.clone(), SquareGroup.values()[i]);
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


    public void render(Graphics g, JPanel panel) {
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null) continue;
            squares[i].render(g, panel);
        }
    }
}