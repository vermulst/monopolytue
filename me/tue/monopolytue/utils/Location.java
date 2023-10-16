package me.tue.monopolytue.utils;

import java.util.Objects;

public class Location extends Position{


    private int degreeRotation;

    public Location(double x, double y) {
        super(x, y);
    }

    public void setDegreeRotation(int degreeRotation) {
        this.degreeRotation = degreeRotation;
    }

    public Location clone() {
        Location location = new Location(this.getX(), this.getY());
        location.setDegreeRotation(this.getDegreeRotation());
        return location;
    }

    public Location difference(Location location) {
        return new Location(this.getX() - location.getX(), this.getY() - location.getY());
    }


    public int getDegreeRotation() {
        return degreeRotation;
    }

}
