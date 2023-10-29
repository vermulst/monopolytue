package me.tue.monopolytue.frame;

import me.tue.monopolytue.menu.MenuPanel;
import me.tue.monopolytue.turn.participant.Participant;

import javax.swing.*;

public class Game extends JFrame {

    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;

    public Game() {

        super("Monopoly");
        this.setSize(1920, 1080);

        this.gamePanel = new GamePanel();
        this.menuPanel = new MenuPanel(this);

        this.pack();

    }


    public void open() {
        this.add(menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setVisible(true);
    }

    public void start(Participant[] participants) {
        this.remove(menuPanel);
        this.add(this.gamePanel);
        this.repaint();
        this.gamePanel.start(participants);
    }

    public void stop() {
        this.remove(this.gamePanel);
        this.add(menuPanel);
        this.repaint();
    }
}
