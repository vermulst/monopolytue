package me.tue.monopolytue.board;

import java.util.concurrent.SynchronousQueue;

public enum SquareGroup {
    RED(100),
    YELLOW(150),
    GREEN(200),
    BLUE(250),
    CHANCE(0),
    CORNER(0);


    private int price;
    SquareGroup(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
