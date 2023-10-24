package me.tue.monopolytue.board;

import me.tue.monopolytue.turn.Participant;
import me.tue.monopolytue.utils.Location;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Square {


    private Participant owner;
    private SquareGroup squareGroup;
    private Location location;

    public Square(Location location, SquareGroup squareGroup) {
        this.location = location;
        this.squareGroup = squareGroup;
    }

    public void setOwner(Participant owner) {
        this.owner = owner;
    }

    /**
     * Render the square onto the panel
     */
    public void render(Graphics g, JPanel panel) {
        File file = new File("images/emptysquare.png");
        if (SquareGroup.CORNER.equals(this.getSquareGroup())) {
            file = new File("images/emptycorner.png");
        } else if (SquareGroup.CHANCE.equals(this.getSquareGroup())) {
            file = new File("images/chancecard.png");
        }
        try {
            BufferedImage image = ImageIO.read(file);
            image = this.rotateImageByDegrees(image, this.getLocation().getDegreeRotation(), panel);
            g.drawImage(image, (int) this.location.getX(), (int) this.location.getY(), panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation() {
        return location;
    }


    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle, JPanel panel) {
        if (angle == 0) return img;
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((double) (newWidth - w) / 2, (double) (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, panel);
        g2d.dispose();

        return rotated;
    }

    public Participant getOwner() {
        return owner;
    }

    public SquareGroup getSquareGroup() {
        return squareGroup;
    }
}
