import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    private Board board;

    public GamePanel() {
        this.board = new Board();
    }

    @Override
    public void paintComponent(Graphics g) {
        this.board.render(g);
    }
}
