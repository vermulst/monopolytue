package me.tue.monopolytue.utils;


public class Location extends Position {

}
/**
 * class location, used to simplify x, y and rotation
 */
public class Location extends Position{


    private int degreeRotation;

    public Location(double x, double y) {
        super(x, y);
    }

    public void setDegreeRotation(int degreeRotation) {
        this.degreeRotation = degreeRotation;
    }

    /**
     * Clone the location and return the location.
     * @return a clone with the same properties
     */
    public Location clone() {
        Location location = new Location(this.getX(), this.getY());
        location.setDegreeRotation(this.getDegreeRotation());
        return location;
    }

    public int getDegreeRotation() {
        return degreeRotation;
    }

}
