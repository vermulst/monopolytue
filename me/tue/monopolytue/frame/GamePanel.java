package me.tue.monopolytue.frame;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.turn.Diceroller;
import me.tue.monopolytue.turn.Participant;
import me.tue.monopolytue.utils.Position;

import java.awt.*;

import javax.swing.*;

public class GamePanel extends JPanel {
    
    private Board board;
    private Diceroller diceroller;

    public GamePanel(JFrame frame, Participant[] participants) {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        layout.setVgap(100);
        layout.setHgap(100);
        this.setLayout(layout);
        this.setLocation(0, 0);
        this.setSize(frame.getSize());
        this.board = new Board(participants);
        this.diceroller = new Diceroller(this.board);
        this.add(this.board);
        this.add(this.diceroller);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Board getBoard() {
        return board;
    }

    public Diceroller getDiceroller() {
        return diceroller;
    }
}
