package me.tue.monopolytue.turn.participant;

import me.tue.monopolytue.board.Board;
import me.tue.monopolytue.board.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.text.NumberFormat;

/**
 * The class Participant monitors the balance, position on the board, bankruptcy
 * and if it's the participant's turn.
 * Renders a pawn with the color depending on the ParticipantID
 */
public class Participant extends JComponent {

    private final int participantID;
    private int balance = 750;
    private int positionOnBoard = 0;
    private boolean turn = false;
    private boolean bankrupt = false;

    private static final int IMG_WIDTH = 50;
    private static final int IMG_HEIGHT = 50;

    private static final int REWARD_PASSING_START = 50;

    private BufferedImage image;

    /**
     * The constructor of the Participant class.
     * @param id - the id of the participant
     */
    public Participant(int id) {
        this.participantID = id;
        this.initImage();
    }


    /**
     * Render the pawn of the participant to the square it's currently standing on.
     */
    public void renderPawn(Graphics g, Board board) {

        BufferedImage image = this.getImage();
        Square currentSquare = this.getCurrentSquare(board);
        if (currentSquare == null) {
            return;
        }

        int playerIndex = 0;
        for (int i = 0; i < board.getParticipants().length; i++) {
            if (!board.getParticipants()[i].getCurrentSquare(board).equals(currentSquare)) {
                continue;
            }
            if (!this.equals(board.getParticipants()[i])) {
                playerIndex++;
            }
            else {
                break;
            }
        }

        int x = (int) currentSquare.getLocation().getX();
        int y = (int) currentSquare.getLocation().getY() + 25;

        for (int i = 0; i < playerIndex; i++) {
            x += (int) (image.getWidth() - (IMG_WIDTH / 2.5));
            double currentPositionOnSquare = x - currentSquare.getLocation().getX();
            double pawnWidth = (double) this.getImage().getWidth() / 2;
            if (currentPositionOnSquare + pawnWidth > currentSquare.getImage().getWidth()) {
                x = (int) currentSquare.getLocation().getX();
                y += image.getHeight() + 5;
            }
        }

        g.drawImage(image, x, y, board);
    }

    /**
     * Boolean whether a participant has the turn.
     * @param turn - whether it's the participant's turn.
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
        this.repaint();
    }

    /**
     * Formats a number with dots every 3 numbers starting from the end.
     * @param number - number to format.
     * @return A formatted String
     */
    private String formatted(int number) {
        String formattedNumber = NumberFormat.getInstance().format(number);
        formattedNumber = formattedNumber.replaceAll(",", ".");
        return formattedNumber;
    }

    /**
     * Get the color of the pixel in the center of the participant's image
     * @return Color
     */
    public Color getColor() {
        int rgb = this.getImage().getRGB((int) (this.getImage().getWidth() / 2.0), (int) (this.getImage().getHeight() / 2.0));
        int red = Math.abs(256 + (int) (rgb/(Math.pow(256, 2))) - 20);
        int green = Math.abs(256 + (rgb/256) % 256 - 20);
        int blue = Math.abs(256 + rgb%256 - 20);
        return new Color(red, green, blue);
    }


    /**
     * Create the image for the participant
     */
    public void initImage() {
        try {
            BufferedImage image1 = ImageIO.read(new File("images/pawns/player" + this.participantID + ".png"));

            BufferedImage scaledImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(image1, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
            graphics2D.dispose();

            this.image = scaledImage;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Attempt to let the participant buy the square
     * @param square - square to buy
     * @param board - board where the square is located on
     */
    public void buySquare(Square square, Board board) {
        if (square.getSquareGroup().getPrice() <= 0) {
            return;
        }
        if (this.balance >= square.getSquareGroup().getPrice()) {
            this.removeFromBalance(square.getSquareGroup().getPrice());
            square.setOwner(this);
            board.repaint();
        }
    }

    /**
     * Moves the participant the correct number of squares.
     * @param board - the board to move squares on.
     * @param squares - the amount of squares.
     */
    public void moveSquares(Board board, int squares) {
        this.positionOnBoard += squares;
        if (this.positionOnBoard >= board.getTotalSquareAmount()) {
            this.addToBalance(REWARD_PASSING_START);
            this.positionOnBoard -= board.getTotalSquareAmount();
        }
    }

    /**
     * Adds amount to balance of the participant.
     * @param amount - the added amount.
     */
    public void addToBalance(int amount) {
        this.balance += amount;
        this.repaint();
    }
    
    /**
     * Removes amount from balance of the particpant.
     * @param amount - the removed amount.
     */
    public void removeFromBalance(int amount) {
        this.balance -= amount;
        this.checkBankrupt();
        this.repaint();
    }

    /**
     * Transfers amount from participant to receiver.
     * @param receiver - the receiver.
     * @param amount - the paid amount.
     */
    public void transferAmount(Participant receiver, int amount) {
        receiver.addToBalance(amount);
        this.removeFromBalance(amount);
    }

    /**
     * Checks whether participant is bankrupt.
     */
    public void checkBankrupt() {
        if (this.balance < 0) {
            this.bankrupt = true;
        }
    }

    /**
     * Check the value of all the participant's squares combined
     * @param board - board to check worth
     * @return int value
     */
    public int getTotalWorth(Board board) {
        int value = 0;
        for (Square square : board.getSquares()) {
            if (this.equals(square.getOwner())) {
                value += square.getSquareGroup().getPrice();
            }
        }
        return value;
    }

    // draw the balance counter
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.getFont().getSize() < 40) {
            g.setFont(new Font("Tahoma", Font.PLAIN, 40));
        }
        Graphics2D graphics2D = (Graphics2D) g;

        if (this.isTurn()) {
            graphics2D.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
            graphics2D.setColor(new Color(245, 231, 181));
            graphics2D.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        }

        Color playerColor = this.getColor();

        graphics2D.setColor(playerColor);
        String playerText = "Player " + this.participantID;

        if (this.isBankrupt()) {
            AttributedString as = new AttributedString(playerText);
            as.addAttribute(TextAttribute.FONT, g.getFont());
            as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 1,
                    playerText.length());
            as.addAttribute(TextAttribute.STRIKETHROUGH,
                    TextAttribute.STRIKETHROUGH_ON, 0, playerText.length());

            graphics2D.drawString(as.getIterator(), 10, 50);
        } else {
            graphics2D.drawString(playerText, 10, 50);
        }
        int width = g.getFontMetrics().stringWidth(playerText);

        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.drawString(": €" + formatted(balance), 10 + width, 50);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 75);
    }

    public Square getCurrentSquare(Board board) {
        return board.getSquares()[this.positionOnBoard];
    }

    public int getParticipantID() {
        return participantID;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isTurn() {
        return turn;
    }

    public BufferedImage getImage() {
        return image;
    }

}
