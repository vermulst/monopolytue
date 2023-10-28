package me.tue.monopolytue.popup;

import me.tue.monopolytue.turn.participant.Participant;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Chancecard class renders the chancecard when a player drops on a chancecard.
 * Adjusts the balance of the participant accordingly.
 */

public class Chancecard extends JButton {

    String[] stringCards = new String[5];

    String pulledCard;
    int[] balanceChange = {-100, -50, 50, 100, 150};

    /**
     * Renders the Chancecard into the foreground.
     */
    public Chancecard() {
        super("");

        this.setFont(new Font("Tahoma", Font.PLAIN, 60));
        stringCards[0] = "You have been convicted of money laundering.\n Pay a fine of €100.";
        stringCards[1] = "You have exceeded the speed limit.\n Pay a fine of €50.";
        stringCards[2] = "It's your birthday.\n You receive a gift of €50.";
        stringCards[3] = "Your stocks have increased.\n You receive €100 in dividend.";
        stringCards[4] = "You won a lawsuit.\n You earn €150";

        this.setMinimumSize(this.getPreferredSize());
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        this.setBackground(new Color(245, 231, 181));
        this.setForeground(new Color(5, 3, 3));

        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1,100,1,2);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);

        this.setBorder(newBorder);
    }

    /**
     * Updates the tect of the chancecard.
     */

    public void updateText() {
        String chanceCard = "Chancecard!\n\n";
        String text = chanceCard + pulledCard;
        this.setText("<html>"  + text.replaceAll("\\n", "<br>") + "</html>");
    }

    /**
     * Renders the chance card.
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1400, 883);
    }

    /**
     * pulls a chancecard when the player drops on a chance card.
     * @param participant
     */

    public void pullChancecard(Participant participant) {
        int number = ThreadLocalRandom.current().nextInt(5);
        this.event(participant, number);
        pulledCard = this.stringCards[number];
        this.updateText();
        this.update(getGraphics());
    }

    /**
     * Adjusts the balance of the player accoring to the chancecard.
     * @param participant
     * @param number
     */

    public void event(Participant participant, int number) {
        switch (number) {
            case 0 -> {
                participant.removeFromBalance(100);
            }
            case 1 -> {
                participant.removeFromBalance(50);
            }
            case 2 -> {
                participant.addToBalance(50);
            }
            case 3 -> {
                participant.addToBalance(100);
            }
            case 4 -> {
                participant.addToBalance(150);
            }
        }
    }
}
