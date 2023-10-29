package me.tue.monopolytue.utils;

/**
 * The class vector.
 */

public class Vector extends Position{
    public Vector(int x, int y) {
        super(x, y);
    }

    /**
     * Rotates the squares by 90 degrees
     */

    public void rotateBy90() {
        this.setX(-this.getX());
        double currentY = this.getY();
        this.setY(this.getX());
        this.setX(currentY);
    }

    /**
     * Rotates the squares counterclockwise by 90 degrees.
     */

    public void rotateBy90CounterClockWise() {
        this.rotateBy90();
        this.multiply(-1);
    }
}
