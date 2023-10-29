package me.tue.monopolytue.frame;

import me.tue.monopolytue.menu.MenuPanel;
import me.tue.monopolytue.turn.participant.Participant;

import javax.swing.*;

/**
 * The class game is the frame where the game is played upon.
 */
public class Game extends JFrame {

    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;

    /**
     * Constructor to create a game instance.
     */
    public Game() {

        super("Monopoly");
        this.setSize(1920, 1080);

        this.gamePanel = new GamePanel();
        this.menuPanel = new MenuPanel(this);

        this.pack();

    }

    /**
     * Open the game
     * Renders the starting screen the game settings can be configured.
     */
    public void open() {
        this.add(menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setVisible(true);
    }

    /**
     * Starts the game
     *
     * @param participants - the participants that participate in the game
     */

    public void start(Participant[] participants) {
        this.remove(menuPanel);
        this.add(this.gamePanel);
        this.repaint();
        this.gamePanel.start(participants);
    }

    /**
     * Ends the current game and calls the open method.
     * The user can then choose to start a new game with new settings.
     */

    public void stop() {
        this.remove(this.gamePanel);
        this.add(menuPanel);
        this.repaint();
    }
}
