package me.tue.monopolytue.board;

import java.util.concurrent.SynchronousQueue;

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
        return (int) Math.ceil((double)price * 0.3);
    }
}
