import javax.swing.*;
import java.awt.event.MouseListener;

/**
 * Created by eric on 4/24/2017.
 */
public class CardPanel extends JPanel {
    int playerPos;
    int handPos;
    CardPanel(int p, int h){
        this.handPos = h;
        this.playerPos = p;
    }
}
