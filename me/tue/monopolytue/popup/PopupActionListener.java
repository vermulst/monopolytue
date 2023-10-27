package me.tue.monopolytue.popup;

import me.tue.monopolytue.frame.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupActionListener implements ActionListener {

    private JButton button;
    private GamePanel gamePanel;
    public PopupActionListener(JButton button, GamePanel gamePanel) {
        this.button = button;
        this.gamePanel = gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.setPaused(false);
        gamePanel.getPopupPanel().remove(button);
        gamePanel.getPopupPanel().repaint();
    }
}
