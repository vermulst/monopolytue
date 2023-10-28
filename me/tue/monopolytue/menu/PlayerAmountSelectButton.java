package me.tue.monopolytue.menu;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerAmountSelectButton extends JButton implements MouseListener {

    private MenuPanel menuPanel;
    private int num;

    /**
     * Rendering the button to select the number of players.
     * 
     * @param menuPanel
     * @param num
     */

    public PlayerAmountSelectButton(MenuPanel menuPanel, int num) {
        super(String.valueOf(num));

        this.setBackground(new Color(245, 231, 181));
        this.setForeground(new Color(5, 3, 3));

        this.setFocusPainted(false);
        this.setFont(new Font("Tahoma", Font.PLAIN, 40));

        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1,5,1,5);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);

        this.setBorder(newBorder);

        this.menuPanel = menuPanel;
        this.num = num;
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.menuPanel.setSelectedPlayerAmount(this.num);

        this.setBackground(new Color(187, 152, 25));
        for (Component component : this.menuPanel.getSelectingPlayerPanel().getComponents()) {
            if (component.equals(this)) continue;
            component.setBackground(new Color(253, 198, 86));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
