package me.tue.monopolytue.utils;

public class Vector extends Position{
    public Vector(int x, int y) {
        super(x, y);
    }

    public void rotateBy90() {
        this.setX(-this.getX());
        double currentY = this.getY();
        this.setY(this.getX());
        this.setX(currentY);
    }

    public void rotateBy90CounterClockWise() {
        this.rotateBy90();
        this.multiply(-1);
    }
}
