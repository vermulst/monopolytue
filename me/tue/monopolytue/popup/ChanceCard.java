package me.tue.monopolytue.popup;

import me.tue.monopolytue.frame.GamePanel;
import me.tue.monopolytue.turn.participant.Participant;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Chance card class renders the chance card when a player drops on a chance card square.
 * Adjusts the balance of the participant accordingly.
 */
public class ChanceCard extends PopupButton {

    private final String[] stringCards = new String[5];

    private String pulledCard;

    public ChanceCard(GamePanel gamePanel) {
        super(gamePanel);
        stringCards[0] = "You have been convicted of money laundering.\n Pay a fine of €150.";
        stringCards[1] = "You have exceeded the speed limit.\n Pay a fine of €100.";
        stringCards[2] = "It's your birthday.\n You receive a gift of €25.";
        stringCards[3] = "Your stocks have increased.\n You receive €50 in dividend.";
        stringCards[4] = "You won a lawsuit.\n You earn €75";
    }

    /**
     * Updates the text of the chance card.
     */
    public void updateText() {
        String chanceCard = "Chance card!\n\n";
        String text = chanceCard + this.pulledCard;
        this.setText("<html>"  + text.replaceAll("\\n", "<br>") + "</html>");
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(245, 231, 181);
    }

    @Override
    public Color getForegroundColor() {
        return new Color(5, 3, 3);
    }

    @Override
    public Color getBorderColor() {
        return new Color(253, 198, 86);
    }

    @Override
    public Font getFont() {
        return new Font("Tahoma", Font.PLAIN, 60);
    }

    @Override
    public void getExtraButtonEvents() {
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1400, 883);
    }

    /**
     * pulls a chance card when the player drops on a chance card.
     * @param participant - the participant to pull the chance card
     */
    public void pullChanceCard(Participant participant) {
        int number = ThreadLocalRandom.current().nextInt(5);
        this.event(participant, number);
        this.pulledCard = this.stringCards[number];
        this.updateText();
        this.update(getGraphics());
    }

    /**
     * Adjusts the balance of the player according to the chancecard.
     * @param participant - participant to apply the event on
     * @param number - the index of the chance card 0 - 4
     */
    public void event(Participant participant, int number) {
        switch (number) {
            case 0 -> {
                participant.removeFromBalance(150);
            }
            case 1 -> {
                participant.removeFromBalance(100);
            }
            case 2 -> {
                participant.addToBalance(25);
            }
            case 3 -> {
                participant.addToBalance(50);
            }
            case 4 -> {
                participant.addToBalance(75);
            }
        }
    }
}
