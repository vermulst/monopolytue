import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

public class Square {
    

    public Square() {

    }

    /**
     * Render the square onto the panel
     */
    public void render(Graphics g) {
        //todo:
        g.drawRect(ThreadLocalRandom.current().nextInt(300), ThreadLocalRandom.current().nextInt(300), 5, 10);
    }
}
