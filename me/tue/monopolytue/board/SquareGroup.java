package me.tue.monopolytue.board;

import java.util.concurrent.SynchronousQueue;

/**
 * The class square group which determines the price of the card,
 * depending upon the side of the card.
 * Moreover also the rent is determined
 */

public enum SquareGroup {
    RED(150),
    YELLOW(225),
    GREEN(300),
    BLUE(375),
    CHANCE(0),
    CORNER(0);


    private int price;

    SquareGroup(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return (int) Math.ceil((double) price * 0.3);
    }
}
