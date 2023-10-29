package me.tue.monopolytue.popup;

import me.tue.monopolytue.frame.GamePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PopupButton extends JButton implements ActionListener {

    private GamePanel gamePanel;

    public PopupButton(GamePanel gamePanel) {
        super("");

        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        this.setBackground(this.getBackgroundColor());
        this.setForeground(this.getForegroundColor());
        this.setFont(this.getFont());
        this.setMinimumSize(this.getPreferredSize());

        LineBorder border1 = new LineBorder(this.getBorderColor(), 3, true);
        int xMargin = (int) (this.getPreferredSize().getWidth()/ 6);
        EmptyBorder border2 = new EmptyBorder(1,xMargin,1,xMargin);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);

        this.setBorder(newBorder);

        gamePanel.setPaused(true);
        this.gamePanel = gamePanel;

        this.addActionListener(this);
    }

    public abstract Color getBackgroundColor();
    public abstract Color getForegroundColor();
    public abstract Color getBorderColor();
    public abstract Font getFont();
    public abstract void getExtraButtonEvents();

    @Override
    public abstract Dimension getPreferredSize();

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.setPaused(false);
        gamePanel.getPopupPanel().remove(this);
        gamePanel.getPopupPanel().repaint();
        this.getExtraButtonEvents();
    }
}
