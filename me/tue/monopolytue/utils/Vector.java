package me.tue.monopolytue.utils;

/**
 * Class used to represent a vector.
 */
public class Vector extends Position {

    /**
     * Used to construct a vector.
     * @param x - x value of vector.
     * @param y - y value of vector.
     */
    public Vector(int x, int y) {
        super(x, y);
    }

    /**
     * Rotates the vector by 90 degrees.
     */
    public void rotateBy90() {
        this.setX(-this.getX());
        double currentY = this.getY();
        this.setY(this.getX());
        this.setX(currentY);
    }

    /**
     * Rotates the vector counterclockwise by 90 degrees.
     */
    public void rotateBy90CounterClockWise() {
        this.rotateBy90();
        this.multiply(-1);
    }
}
