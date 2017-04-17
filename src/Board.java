import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by eric on 4/3/2017.
 */
public class Board extends JFrame {
    private Player[] players;
    private int numPlayers;
    private Deck deck;
    private int currentTurn = 0;
    private Card back;
    private JPanel display;
    private JLabel[] tableCards;
    private JPanel[] hands;
    private JPanel table;

    public Board(int P){
        super("Daifugo");
        setLayout(new BorderLayout());
        numPlayers = P;
        deck = new Deck();
        InitPlayers();
        deck.Deal(players);
        InitHands();
        back = new Card("spades", 3);

        display = new JPanel();
        add(display, BorderLayout.CENTER);
        display.setBackground(Color.GREEN);
        display.setLayout(new BorderLayout());
        tableCards = new JLabel[4];
        table = new JPanel();
        table.setLayout(new GridLayout(1, 5));
        for(int i = 0; i < 4; i++) {
            tableCards[i] = new JLabel();
            table.add(tableCards[i]);
        }
        JButton Pass = new JButton();
        Pass.setText("Pass");
        JButton Play = new JButton();
        Play.setText("Play");
        JPanel buttons = new JPanel(new GridLayout(2,1));
        buttons.add(Pass);
        buttons.add(Play);
        table.add(buttons);
        display.add(table);
        add(hands[currentTurn], BorderLayout.SOUTH);
        add(hands[1], BorderLayout.WEST);
        add(hands[2], BorderLayout.NORTH);
        add(hands[3], BorderLayout.EAST);
        revalidate();
        repaint();
    }
    private void InitPlayers(){
        players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            players[i] = new Player(i);
        }
    }
    private void InitHands(){
        hands = new JPanel[numPlayers];
        Border unhighlight = BorderFactory.createLineBorder(Color.BLACK);
        for(int i = 0; i < numPlayers; i++){
            hands[i] = new JPanel();
            if(i == currentTurn) {
                hands[i].setLayout(new GridLayout(1, players[i].HandSize));
                for (int j = 0; j < players[i].HandSize; j++) {
                    JPanel CardHolder = new JPanel();
                    CardHolder.add(new JLabel(players[i].hand.elementAt(j).getImg()));
                    CardHolder.setBorder(unhighlight);
                    CardHolder.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Border highlighted = BorderFactory.createLineBorder(Color.YELLOW);
                            if(CardHolder.getBorder() == unhighlight)
                                CardHolder.setBorder(highlighted);
                            else
                                CardHolder.setBorder(unhighlight);
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
                    hands[i].add(CardHolder);
                    CardHolder.setVisible(true);
                }
            }
            else if(i == currentTurn + 2 || i == currentTurn - 2){
                hands[i].setLayout(new GridLayout(1, players[i].HandSize));
                for (int j = 0; j < players[i].HandSize; j++) {
                    JPanel CardHolder = new JPanel();
                    CardHolder.add(new JLabel(players[i].hand.elementAt(j).getBackImg()));
                    hands[i].add(CardHolder);
                    CardHolder.setVisible(true);
                }
            }
            else{
                hands[i].setLayout(new GridLayout(players[i].HandSize, 1));
                for (int j = 0; j < players[i].HandSize; j++) {
                    JPanel CardHolder = new JPanel();
                    CardHolder.add(new JLabel(players[i].hand.elementAt(j).getBackImg()));
                    hands[i].add(CardHolder);
                    CardHolder.setVisible(true);
                }
            }
        }
    }
}
