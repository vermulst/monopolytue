package me.tue.monopolytue.frame;

import me.tue.monopolytue.turn.Diceroller;
import me.tue.monopolytue.board.Board;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    private Board board;
    private Diceroller diceroller;

    public GamePanel() {
        this.board = new Board();
        this.diceroller = new Diceroller();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.board.render(g, this);
        this.diceroller.render(g, this);
    }
}
