package me.tue.monopolytue.frame;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.popup.GameOverButton;
import me.tue.monopolytue.popup.PopupButton;
import me.tue.monopolytue.turn.button.BuyButton;
import me.tue.monopolytue.turn.DiceRoller;
import me.tue.monopolytue.turn.button.NextTurnButton;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.turn.PriceCard;

import java.awt.*;
import java.util.function.Consumer;

import javax.swing.*;

/**
 * Class for displaying the ongoing game.
 */
public class GamePanel extends JLayeredPane {
    
    private final Board board;
    private final DiceRoller diceroller;
    private final BuyButton buyButton;
    private final NextTurnButton nextTurnButton;
    private final PriceCard priceCard;

    private JPanel organizedPanel;
    private JPanel popupPanel;

    /**
     * Constructor to create the game panel.
     */
    public GamePanel() {
        this.setSize(1920, 1080);

        this.board = new Board(this);
        this.diceroller = new DiceRoller(this.board);
        this.buyButton = new BuyButton(board, diceroller);
        this.priceCard = new PriceCard(this.diceroller, this.board);
        this.diceroller.setPriceCard(this.priceCard);
        this.nextTurnButton = new NextTurnButton(this.diceroller);
    }

    /**
     * Adds all the panels to start the game.
     *
     * @param participants - players of the game
     */
    public void start(Participant[] participants) {
        this.board.setParticipants(participants);
        this.initOrganizedPanel();
        this.initPopupPanel();

        this.initLeftPanel(participants);
        this.initMiddlePanel();
        this.initRightPanel();

        this.add(this.organizedPanel, JLayeredPane.DEFAULT_LAYER);
        this.add(this.popupPanel, JLayeredPane.PALETTE_LAYER);
    }


    /**
     * Attempts to end the game with a game over popup.
     *
     * @param winner - the winner of the game
     */
    public void stop(Participant winner) {
        GamePanel gamePanel = this;

        /* The content inside the Consumer will get passed into the button
            which then gets accepted when the button is pressed */
        Consumer<Boolean> endGameEvent = new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                Game game = (Game) SwingUtilities.windowForComponent(gamePanel);
                game.stop();
            }
        };
        GameOverButton gameOverButton = new GameOverButton(this, endGameEvent, winner);
        this.getPopupPanel().add(gameOverButton);
    }

    /**
     * Initializing the organized panel.
     * Panel for displaying player balance, board and dice
     * in an organized manner
     */
    public void initOrganizedPanel() {
        JPanel organizedPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        flowLayout.setVgap(100);
        flowLayout.setHgap(100);
        organizedPanel.setLayout(flowLayout);
        organizedPanel.setSize(this.getSize());
        this.organizedPanel = organizedPanel;
    }

    /**
     * Initializing pop-up panel
     * Overlay panel for displaying a chance card or the game over screen
     */
    public void initPopupPanel() {
        JPanel popupPanel = new JPanel();
        popupPanel.setOpaque(false);
        popupPanel.setLayout(new GridBagLayout());
        popupPanel.setSize(this.getSize());
        this.popupPanel = popupPanel;
    }

    /**
     * Adds a popup to the overlay panel.
     * @param button - the popup button to be added
     */
    public void addPopup(PopupButton button) {
        this.getPopupPanel().add(button);
    }

    /**
     * Initialize left panel onto the game panel.
     * Panel to display player balances.
     * @param participants - the participants that participate in the game
     */
    public void initLeftPanel(Participant[] participants) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        for (Participant participant : participants) {
            leftPanel.add(participant);
        }
        this.organizedPanel.add(leftPanel);
    }

    /**
     * Initialize middle panel onto the game panel.
     * Panel to display the game board
     */
    public void initMiddlePanel() {
        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        middlePanel.add(this.board);
        this.organizedPanel.add(middlePanel);
    }


    /**
     * Initialize right panel on to the gaming board
     * Panel to display dice and various buttons
     */
    public void initRightPanel() {
        JPanel rightPanel = new JPanel();

        BoxLayout boxLayout = new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS);
        rightPanel.setLayout(boxLayout);

        rightPanel.add(this.diceroller);
        rightPanel.add(Box.createRigidArea(new Dimension(0,40)));
        rightPanel.add(this.priceCard);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(this.buyButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));
        rightPanel.add(this.nextTurnButton);

        this.organizedPanel.add(rightPanel);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public JPanel getPopupPanel() {
        return popupPanel;
    }
}
