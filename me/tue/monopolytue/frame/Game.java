package me.tue.monopolytue.frame;

import me.tue.monopolytue.turn.Participant;
import me.tue.monopolytue.turn.Player;

import javax.swing.JFrame;

public class Game {

    private JFrame frame;
    private GamePanel panel;
    private Participant[] participants;



    public Game() {

        this.participants = new Participant[]{new Player(1), new Participant(2), new Participant(3), new Participant(4)};
        this.participants[0].setTurn(true);

        this.frame = new JFrame("Monopoly");
        this.frame.setSize(1920, 1080);
        this.panel = new GamePanel(this.frame, this.participants);
        this.frame.add(this.panel);
        this.frame.pack();

    }

    public void start() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1920, 1080);
        this.frame.setVisible(true);
    }
    
}
