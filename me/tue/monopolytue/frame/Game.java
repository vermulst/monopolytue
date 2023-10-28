package me.tue.monopolytue.frame;

import me.tue.monopolytue.menu.MenuPanel;
import me.tue.monopolytue.turn.participant.Participant;

import javax.swing.*;

/**
 * The class game is the frame where the game is played upon.
 *
 * 
 */

public class Game extends JFrame {

    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    /**
     * The game is played upon a panel. 
     */

    public Game() {

        super("Monopoly");
        this.setSize(1920, 1080);


        this.gamePanel = new GamePanel();
        this.menuPanel = new MenuPanel(this);

        this.pack();

    }

    /**
     * The metod open renders the starting screen the game settings can be configured.
     */

    public void open() {
        this.add(menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setVisible(true);
    }

    /**
     * The metod start starts the actual game board, with the cofigured number of participants.
     * 
     * @param participants
     */

    public void start(Participant[] participants) {
        this.remove(menuPanel);
        this.add(this.gamePanel);
        this.repaint();
        System.out.println("test");
        this.gamePanel.start(participants);
    }

    /**
     * The method stop stops the current game and calls the open method
     * to alter the gamesetting and starting playing a new game.
     */

    public void stop() {
        this.add(menuPanel);
        this.remove(this.gamePanel);
        this.repaint();
    }


    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
