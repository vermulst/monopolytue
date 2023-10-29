package me.tue.monopolytue.popup;

import me.tue.monopolytue.frame.GamePanel;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.turn.participant.Player;

import java.awt.*;
import java.util.function.Consumer;

/**
 * Game over button that gets displayed when the game is over
 * When clicked the user menu is opened and a new game can be started
 */
public class GameOverButton extends PopupButton {

    private final Consumer<Boolean> buttonEvent;

    public GameOverButton(GamePanel gamePanel, Consumer<Boolean> buttonEvent, Participant winner) {
        super(gamePanel);
        this.buttonEvent = buttonEvent;

        String gameOverText = "Game Over!\n\n";
        String winnerText;
        if (winner instanceof Player) {
            winnerText = "You won!";
        } else {
            winnerText = "You lost!";
            winnerText = winnerText + "\nPlayer " + winner.getParticipantID() + " won!";
        }
        String text = gameOverText + winnerText;
        this.setText("<html>"  + text.replaceAll("\\n", "<br>") + "</html>");
    }


    @Override
    public void getExtraButtonEvents() {
        this.buttonEvent.accept(true);
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(245, 231, 181);
    }

    @Override
    public Color getForegroundColor() {
        return new Color(126, 129, 129);
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
    public Dimension getPreferredSize() {
        return new Dimension(1400, 883);
    }
}
