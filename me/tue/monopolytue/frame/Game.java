package me.tue.monopolytue.frame;

import javax.swing.JFrame;

public class Game {

    private JFrame frame;
    private GamePanel panel;


    public Game() {
        this.frame = new JFrame("Monopoly");
        this.panel = new GamePanel();
        this.frame.add(this.panel);
    }

    public void start() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1920, 1080);
        this.frame.setVisible(true);
    }
    
}
