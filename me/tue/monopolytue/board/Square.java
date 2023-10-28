package me.tue.monopolytue.board;

import me.tue.monopolytue.popup.Chancecard;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.utils.Location;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This sqaure class monitors the owner of a specific square and repaints the color 
 * of the square to the corresonding player.
 * Moreover it renders the squares on the board at the beginning of the game.
 * When a player moves to a chancecard square the program executes the appriate measures.
 */

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
     * Function to pull a chancecard when participant lands on a chancecard.
     * Transfer rent from participant to owner when an card is already owned.
     * 
     * @param board
     * @param participant
     */

    public void onLand(Board board, Participant participant) {
        if (this.squareGroup.equals(SquareGroup.CHANCE)) {
            Chancecard chancecard = new Chancecard();
            board.getGamePanel().addPopup(chancecard);
            chancecard.pullChancecard(participant);
        }
        if (this.getOwner() != null && this.getOwner() != participant) {
            participant.transferAmount(this.getOwner(), this.getSquareGroup().getRent());
        }
    }


    /**
     * Render the square onto the panel
     */
    public void render(Graphics g, JComponent component) {
        BufferedImage image = this.getImage();
        image = this.rotateImageByDegrees(image, this.getLocation().getDegreeRotation(), component);
        if (this.getOwner() != null) {
            image = replaceColor(image, Color.WHITE, this.getOwner().getColor());
        }
        g.drawImage(image, (int) this.location.getX(), (int) this.location.getY(), component);
    }

/**
 * Returns a modified iage where the color of the square is altered to the corresponding owner. 
 * 
 * @param image
 * @param targetColor
 * @param replacementColor
 * @return modifiedImage
 */

    public static BufferedImage replaceColor(BufferedImage image, Color targetColor, Color replacementColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage modifiedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                if (pixelColor.equals(targetColor)) {
                    modifiedImage.setRGB(x, y, replacementColor.getRGB());
                } else {
                    modifiedImage.setRGB(x, y, image.getRGB(x, y));
                }
            }
        }

        return modifiedImage;
    }

    /**
     * Returns the appriate image for the board.
     * Either emptysquare, emptycorner or chancecard/
     * 
     * @return image
     */

    public BufferedImage getImage() {
        File file = new File("images/emptysquare.png");
        if (SquareGroup.CORNER.equals(this.getSquareGroup())) {
            file = new File("images/emptycorner.png");
        } else if (SquareGroup.CHANCE.equals(this.getSquareGroup())) {
            file = new File("images/chancecard.png");
        }
        try {
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Return the rotated image depend upon which side the image needs to be placed.
     * 
     * @return rotated
     */


    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle, JComponent component) {
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
        g2d.drawImage(img, 0, 0, component);
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
