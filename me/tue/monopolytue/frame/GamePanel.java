package me.tue.monopolytue.frame;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.popup.GameOverButton;
import me.tue.monopolytue.popup.PopupButton;
import me.tue.monopolytue.turn.button.BuyButton;
import me.tue.monopolytue.turn.Diceroller;
import me.tue.monopolytue.turn.button.NextTurnButton;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.turn.PriceCard;

import java.awt.*;
import java.util.function.Consumer;

import javax.swing.*;

public class GamePanel extends JLayeredPane {
    
    private final Board board;
    private final Diceroller diceroller;
    private final BuyButton buyButton;
    private final NextTurnButton nextTurnButton;
    private final PriceCard priceCard;

    private JPanel organizedPanel;
    private JPanel popupPanel;
    private Boolean paused = false;

    public GamePanel() {
        this.setSize(1920, 1080);

        this.board = new Board(this);
        this.diceroller = new Diceroller(this.board);
        this.buyButton = new BuyButton(board, diceroller);
        this.priceCard = new PriceCard(this.diceroller, this.board);
        this.diceroller.setPriceCard(this.priceCard);
        this.nextTurnButton = new NextTurnButton(this.diceroller);
    }


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

    public void stop(Participant winner) {
        GamePanel gamePanel = this;
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

    public void initPopupPanel() {
        JPanel popupPanel = new JPanel();
        popupPanel.setOpaque(false);
        popupPanel.setLayout(new GridBagLayout());
        popupPanel.setSize(this.getSize());
        this.popupPanel = popupPanel;
    }

    public void addPopup(PopupButton button) {
        this.getPopupPanel().add(button);
    }


    public void initLeftPanel(Participant[] participants) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        for (Participant participant : participants) {
            leftPanel.add(participant);
        }
        this.organizedPanel.add(leftPanel);
    }

    public void initMiddlePanel() {
        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        middlePanel.add(this.board);
        this.organizedPanel.add(middlePanel);
    }

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

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Boolean getPaused() {
        return paused;
    }

    public JPanel getPopupPanel() {
        return popupPanel;
    }
}
