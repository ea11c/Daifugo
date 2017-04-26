import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by eric on 4/24/2017.
 */
public class CardPanel extends JPanel {
    Card card;
    int playerPos;
    private Border unhighlight = BorderFactory.createLineBorder(Color.BLACK);
    private Border highlighted = BorderFactory.createLineBorder(Color.YELLOW);
    CardPanel(Card t, int p){
        this.card = t;
        this.playerPos = p;
        setBorder(unhighlight);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!Board.Passing) {
                    if (getBorder() == unhighlight) {
                        if (Board.currentHighlights.isEmpty()) {
                            setBorder(highlighted);
                            Board.currentHighlights.add((CardPanel) e.getSource());
                        }
                        else {
                            if (Board.numHighlighted == card.getRank()) {
                                setBorder(highlighted);
                                Board.currentHighlights.add((CardPanel) e.getSource());
                            }
                            else {
                                for (int i = 0; i < Board.currentHighlights.size(); i++) {
                                    Board.currentHighlights.elementAt(i).setBorder(unhighlight);
                                }
                                Board.currentHighlights.clear();
                                setBorder(highlighted);
                                Board.currentHighlights.add((CardPanel) e.getSource());
                                Board.numHighlighted = card.getRank();
                            }
                        }
                    } else {
                        Board.currentHighlights.remove((CardPanel) e.getSource());
                        setBorder(unhighlight);
                    }
                }
                else{
                    if(getBorder() == unhighlight){
                        if(Board.passingHighlights.size() < Board.AmountPassing){
                            setBorder(highlighted);
                            Board.passingHighlights.add((CardPanel)e.getSource());
                        }
                        else if(Board.passingHighlights.size() == Board.AmountPassing){
                            for(int i = 0; i < Board.passingHighlights.size(); i++){
                                Board.passingHighlights.elementAt(i).setBorder(unhighlight);
                            }
                            Board.passingHighlights.clear();
                            setBorder(highlighted);
                            Board.passingHighlights.add((CardPanel)e.getSource());
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
