package me.tue.monopolytue.menu;

import me.tue.monopolytue.frame.Game;
import me.tue.monopolytue.frame.GamePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Class for the menupanel at the start of the game.
 */

public class MenuPanel extends JPanel {


    private int selectedPlayerAmount;
    private StartButton startButton;
    private JPanel selectingPlayerPanel;

    /**
     * Render the menu panel with the selected number of players.
     * @param game
     */

    public MenuPanel(Game game) {

        this.selectedPlayerAmount = 4;

        this.startButton = new StartButton(game, this);
        this.add(this.startButton);

        this.setSize(1920, 1080);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(boxLayout);

        this.add(Box.createRigidArea(new Dimension(100,200)));
        this.add(this.startButton);
        this.add(Box.createRigidArea(new Dimension(0,40)));
        this.initPlayerAmountSelect();
    }

    /**
     * initialize the selected number of players.
     */

    public void initPlayerAmountSelect() {
        this.selectingPlayerPanel = new JPanel();

        JLabel text = new JLabel("Player amount: ");
        text.setFont(new Font("Tahoma", Font.PLAIN, 40));
        text.setOpaque(true);
        text.setBackground(new Color(245, 231, 181));
        text.setForeground(new Color(5, 3, 3));
        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1,2,1,2);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);
        text.setBorder(newBorder);

        this.selectingPlayerPanel.add(text);

        for (int i = 0; i < 4; i++) {
            PlayerAmountSelectButton button = new PlayerAmountSelectButton(this, (i + 1));
            this.selectingPlayerPanel.add(button);
        }
        this.add(selectingPlayerPanel);
    }


    public int getSelectedPlayerAmount() {
        return selectedPlayerAmount;
    }

    public StartButton getStartButton() {
        return startButton;
    }

    public void setSelectedPlayerAmount(int selectedPlayerAmount) {
        this.selectedPlayerAmount = selectedPlayerAmount;
    }

    public JPanel getSelectingPlayerPanel() {
        return selectingPlayerPanel;
    }
}
