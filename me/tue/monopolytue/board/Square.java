package me.tue.monopolytue.board;

import me.tue.monopolytue.popup.ChanceCard;
import me.tue.monopolytue.turn.participant.Participant;
import me.tue.monopolytue.turn.participant.Player;
import me.tue.monopolytue.utils.Location;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Class to represent an individual square on a Monopoly board.
 * Monitors the owner of a specific square and repaints the color.
 * It also renders the squares on the board at the beginning of the game.
 * When a player moves to a chancecard square the program executes the appropriate measures.
 */

public class Square {


    private Participant owner;
    private final SquareGroup squareGroup;
    private final Location location;
    private final String name;

    public Square(String name, Location location, SquareGroup squareGroup) {
        this.name = name;
        this.location = location;
        this.squareGroup = squareGroup;
    }

    public void setOwner(Participant owner) {
        this.owner = owner;
    }

    /**
     * Function to pull a chance card when participant lands on a chancecard.
     * Transfer rent from participant to owner when an card is already owned.
     *
     * @param board - the board where the square is located
     * @param participant - the participant landing on this square
     */
    public void onLand(Board board, Participant participant) {
        if (this.squareGroup.equals(SquareGroup.CHANCE)) {
            ChanceCard chancecard = new ChanceCard(board.getGamePanel());
            if (participant instanceof Player) {
                board.getGamePanel().addPopup(chancecard);
            }
            chancecard.pullChanceCard(participant);
        }
        if (this.getOwner() != null && this.getOwner() != participant) {
            participant.transferAmount(this.getOwner(), this.getSquareGroup().getRent());
        }
    }


    /**
     * Render the square onto the panel.
     */
    public void render(Graphics g, JComponent component) {
        BufferedImage image = this.getImage();
        this.writeTextOnImage(image, this.getName());
        image = this.rotateImageByDegrees(image, this.getLocation().getDegreeRotation(), component);
        if (this.getOwner() != null) {
            image = this.replaceColor(image, Color.WHITE, this.getOwner().getColor());
        }
        g.drawImage(image, (int) this.location.getX(), (int) this.location.getY(), component);
    }

    public void writeTextOnImage(BufferedImage image, String text) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.setFont(new Font("Tahoma", Font.PLAIN, 15));
        graphics2D.drawString(text, 3, this.getImage().getHeight() - 3);
        graphics2D.dispose();
    }

    /**
    * Returns a modified image to replace a color by another one.
    *
    * @param image - image to modify
    * @param targetColor - color to be replaced
    * @param replacementColor - color that replaces the targetColor
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
     * Returns the appropiate image for the board.
     * Image depends on the type of square
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
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return the rotated image depending on which side the image needs to be placed.
     *
     * @param angle - the angle in degrees to rotate the image by
     * @param component - the component where the image is on
     * @param img - the image to be rotated
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

    public Location getLocation() {
        return location;
    }

    public Participant getOwner() {
        return owner;
    }

    public SquareGroup getSquareGroup() {
        return squareGroup;
    }

    public String getName() {
        return name;
    }
}
