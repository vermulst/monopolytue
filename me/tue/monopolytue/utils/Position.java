package me.tue.monopolytue.utils;

import java.awt.*;
import java.util.Objects;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position clone() {
        return new Position(this.x, this.y);
    }


    public void multiply(int multiplier) {
        this.x *= multiplier;
        this.y *= multiplier;
    }

    public void add(Position position) {
        this.x += position.getX();
        this.y += position.getY();
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public double getDistance(Location location) {
        double y = Math.abs(location.getY() - this.getY());
        double x = Math.abs(location.getX() - this.getX());
        return Math.hypot(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(x, position.getX()) == 0 && Double.compare(y, position.getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "me.tue.monopolytue.utils.Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
