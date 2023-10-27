package me.tue.monopolytue.menu;

import me.tue.monopolytue.frame.Game;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.turn.participant.Player;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartButton extends JButton implements MouseListener {

    private Game game;
    private MenuPanel menuPanel;

    public StartButton(Game game, MenuPanel menuPanel) {
        super("Start Game");

        this.setBackground(new Color(245, 231, 181));
        this.setForeground(new Color(5, 3, 3));

        this.setFocusPainted(false);
        this.setFont(new Font("Tahoma", Font.PLAIN, 40));

        LineBorder border1 = new LineBorder(new Color(253, 198, 86), 3, true);
        EmptyBorder border2 = new EmptyBorder(1,2,1,2);
        Border newBorder = BorderFactory.createCompoundBorder(border1, border2);

        this.setBorder(newBorder);

        this.game = game;
        this.menuPanel = menuPanel;
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


        Participant[] participants = new Participant[menuPanel.getSelectedPlayerAmount()];
        participants[0] = new Player(1);
        participants[0].setTurn(true);
        for (int i = 1; i < participants.length; i++) {
            participants[i] = new Participant(i + 1);
        }

        game.start(participants);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
