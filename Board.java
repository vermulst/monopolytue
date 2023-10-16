import java.awt.Graphics;

import javax.swing.JPanel;

public class Board {

    private Square[] squares;


    public Board() {
        Location vector = new Location(-36, 0);
        Location vector1 = new Location(-72, 0);

        this.squares = new Square[24];
        Location startLocation = new Location(700, 500);
        for (int i = 0; i < 4; i++) {
            startLocation.setDegreeRotation(i * 90);
            this.squares[i * 6] = new Square(startLocation.cloneLocation(), SquareGroup.CORNER);
            System.out.println("Coordinate for corner: " + startLocation);

            if (i > 1) {
                startLocation.add(vector);
            }


            for (int j = 0; j < 5; j++) {
                System.out.println("Coordinate for square " + (i * 6 + j + 1) + " :" + startLocation);
                startLocation.add(vector);
                this.squares[i * 6 + j + 1] = new Square(startLocation.cloneLocation(), SquareGroup.values()[i]);
            }
            if (i > 1) {
                startLocation.add(vector);
            } else {
                startLocation.add(vector1);
            }
            vector1.rotateBy90CounterClockWise();
            vector.rotateBy90CounterClockWise();
        }
    }


    public void render(Graphics g, JPanel panel) {
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null) continue;
            squares[i].render(g, panel);
        }
    }
}