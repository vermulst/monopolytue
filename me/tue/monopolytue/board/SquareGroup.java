package me.tue.monopolytue.board;


/**
 * The enum to represent square group which determines the price of the card.
 * Moreover the rent can be calculated.
 */

public enum SquareGroup {
    RED(150),
    YELLOW(225),
    GREEN(300),
    BLUE(375),
    CHANCE(0),
    CORNER(0);


    private final int price;

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
