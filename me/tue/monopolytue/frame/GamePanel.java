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
        this.setSize(frame.getSize());
        this.board = new Board(participants);
        this.diceroller = new Diceroller(this.board);

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        flowLayout.setVgap(100);
        flowLayout.setHgap(100);
        this.setLayout(flowLayout);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //add components to GamePanel
        for (Participant participant : participants) {
            leftPanel.add(participant);
        }
        middlePanel.add(this.board);
        rightPanel.add(this.diceroller);

        this.add(leftPanel);
        this.add(middlePanel);
        this.add(rightPanel);
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
