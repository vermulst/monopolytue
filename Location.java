import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Location {


    private double x;
    private double y;
    private int degreeRotation;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setDegreeRotation(int degreeRotation) {
        this.degreeRotation = degreeRotation;
    }

    public Location cloneLocation() {
        Location location = new Location(this.x, this.y);
        location.setDegreeRotation(this.getDegreeRotation());
        return location;
    }

    public void multiply(double multiplier) {
        this.x *= multiplier;
        this.y *= multiplier;
    }

    public void add(Location location) {
        this.x += location.getX();
        this.y += location.getY();
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

    public Location difference(Location location) {
        return new Location(this.getX() - location.getX(), this.getY() - location.getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDegreeRotation() {
        return degreeRotation;
    }

    public void rotateBy90() {
        this.x = -this.x;
        double y = this.y;
        this.y = this.x;
        this.x = y;
    }

    public void rotateBy90CounterClockWise() {
        this.rotateBy90();
        this.multiply(-1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(x, location.x) == 0 && Double.compare(y, location.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
