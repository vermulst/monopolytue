import java.awt.Graphics;

import javax.swing.JPanel;

public class Board {

    private Square[] squares;

    public Board() {
        this.squares = new Square[]{new Square(), new Square(), new Square(), new Square(),
             new Square(), new Square(), new Square(), new Square(), new Square(), new Square()};
    }


    public void render(Graphics g) {
        for (int i = 0; i < squares.length; i++) {
            squares[i].render(g);
        }
    }
}