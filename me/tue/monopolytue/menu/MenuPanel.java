package me.tue.monopolytue.menu;

import me.tue.monopolytue.frame.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Class for the menu panel displayed before the start of the game.
 */

public class MenuPanel extends JPanel {


    private int selectedPlayerAmount;
    private JPanel selectingPlayerPanel;

    /**
     * Render the menu panel with the selected number of players.
     * @param game - the game object to start the game after the start button is pressed
     */
    public MenuPanel(Game game) {

        this.selectedPlayerAmount = 4;

        StartButton startButton = new StartButton(game, this);
        startButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        this.setSize(1920, 1080);

        BoxLayout gridBagLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(gridBagLayout);

        JComponent emptyComponent = (JComponent) Box.createRigidArea(new Dimension(startButton.getWidth(),100));
        JComponent emptyComponent1 = (JComponent) Box.createRigidArea(new Dimension(0,40));

        this.add(emptyComponent);
        this.add(startButton);
        this.add(emptyComponent1);
        this.initPlayerAmountSelectPanel();

    }

    /**
     * Initialize the panel to select the amount of players
     */
    public void initPlayerAmountSelectPanel() {
        this.selectingPlayerPanel = new JPanel();

        this.selectingPlayerPanel.add(this.getPlayerSelectTextLabel());

        for (int i = 1; i < 4; i++) {
            PlayerAmountSelectButton button = new PlayerAmountSelectButton(this, (i + 1));
            this.selectingPlayerPanel.add(button);
        }
        this.add(selectingPlayerPanel);
    }


    public JLabel getPlayerSelectTextLabel() {
        JLabel textLabel = new JLabel("Player amount: ");
        textLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        textLabel.setOpaque(true);
        textLabel.setBackground(new Color(245, 231, 181));
        textLabel.setForeground(new Color(5, 3, 3));
        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1,2,1,2);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);
        textLabel.setBorder(newBorder);
        return textLabel;
    }


    public int getSelectedPlayerAmount() {
        return selectedPlayerAmount;
    }

    public void setSelectedPlayerAmount(int selectedPlayerAmount) {
        this.selectedPlayerAmount = selectedPlayerAmount;
    }

    public JPanel getSelectingPlayerPanel() {
        return selectingPlayerPanel;
    }
}
