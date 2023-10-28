package me.tue.monopolytue.frame;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.popup.PopupActionListener;
import me.tue.monopolytue.turn.button.BuyButton;
import me.tue.monopolytue.turn.Diceroller;
import me.tue.monopolytue.turn.button.NextTurnButton;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.turn.PriceCard;

import java.awt.*;

import javax.swing.*;

/**
 * The class game panel 
 */

public class GamePanel extends JLayeredPane {
    
    private Board board;
    private Diceroller diceroller;
    private BuyButton buyButton;
    private NextTurnButton nextTurnButton;
    private PriceCard priceCard;

    private JPanel organizedPanel;
    private JPanel popupPanel;

    private Boolean paused = false;

    /**
     * Renering the gaming panel
     */

    public GamePanel() {
        this.setSize(1920, 1080);

        this.board = new Board(this);
        this.diceroller = new Diceroller(this.board);
        this.buyButton = new BuyButton(board, diceroller);
        this.priceCard = new PriceCard(this.diceroller, this.board);
        this.diceroller.setPriceCard(this.priceCard);
        this.nextTurnButton = new NextTurnButton(this.diceroller);
    }

    /**
     * Render the start panel, to set the game settings. 
     * @param participants
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
     * Initializing the organized panel.
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
     */

    public void initPopupPanel() {
        JPanel popupPanel = new JPanel();
        popupPanel.setOpaque(false);
        popupPanel.setLayout(new GridBagLayout());
        popupPanel.setSize(this.getSize());
        this.popupPanel = popupPanel;
    }

    /**
     * Adds the the pop-up button.
     * @param button
     */

    public void addPopup(JButton button) {
        this.popupPanel.add(button);
        this.paused = true;
        button.addActionListener(new PopupActionListener(button, this));
    }

    /**
     * Initialize left panel on the gamepanel
     * @param participants
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
     * Initialize middle panel on to the gaming board.
     */

    public void initMiddlePanel() {
        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        middlePanel.add(this.board);
        this.organizedPanel.add(middlePanel);
    }

    /**
     * Initialize right panel on to the gaming board
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

    public Board getBoard() {
        return board;
    }

    public Diceroller getDiceroller() {
        return diceroller;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public JPanel getPopupPanel() {
        return popupPanel;
    }
}
