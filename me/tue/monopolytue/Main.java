package me.tue.monopolytue;

import me.tue.monopolytue.frame.Game;

import java.awt.*;

public class Main {

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static void main(String[] args) {

        Game game = new Game();
        game.start();
    }
}