import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * Created by eric on 4/3/2017.
 */
public class Board extends JFrame {
    private Player[] players;
    private int numPlayers;
    private Border unhighlight = BorderFactory.createLineBorder(Color.BLACK);
    private Border highlighted = BorderFactory.createLineBorder(Color.YELLOW);
    private Deck deck;
    private int currentTurn = 0;
    private JPanel display;
    private JLabel[] tableCards;
    private JPanel[] hands;
    private JPanel table;
    private int numHighlighted = 0;
    private int numPlayed = 0;
    private int currentRank = 0;
    private Vector<CardPanel> currentHighlights;
    public Board(int P){
        super("Daifugo");
        setLayout(new BorderLayout());
        numPlayers = P;
        deck = new Deck();
        InitPlayers();
        deck.Deal(players);
        display = new JPanel();
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
        Pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTurn++;
                Game();
            }
        });
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentHighlights.size() >= numPlayed){
                    Card temp = players[currentHighlights.firstElement().playerPos].hand.elementAt(currentHighlights.firstElement().handPos);
                    if(temp.getRank() > currentRank){
                        currentRank = temp.getRank();
                        table.removeAll();
                        table.add(buttons);
                        for(int i = 0; i < currentHighlights.size(); i++){
                            tableCards[i] = new JLabel(players[currentHighlights.elementAt(i).playerPos].hand.elementAt(currentHighlights.elementAt(i).handPos).getImg());
                            table.add(tableCards[i]);
                        }
                        numPlayed = currentHighlights.size();
                        currentTurn++;
                        Game();
                    }
                }
            }
        });
        table.add(buttons);
        display.add(table);
        InitHands();
        Game();
    }
    private void Game(){
        if(currentTurn != 0){
            RotateTable();
        }
        add(display, BorderLayout.CENTER);
        add(hands[currentTurn % 4], BorderLayout.SOUTH);
        add(hands[(currentTurn + 1) % 4], BorderLayout.WEST);
        add(hands[(currentTurn + 2) % 4], BorderLayout.NORTH);
        add(hands[(currentTurn + 3) % 4], BorderLayout.EAST);
        revalidate();
        repaint();
    }
    private void RotateTable(){

    }
    private void InitPlayers(){
        players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            players[i] = new Player(i);

        }
    }
    private void InitHands(){
        hands = new JPanel[numPlayers];
        currentHighlights = new Vector<>(4);
        for(int i = 0; i < numPlayers; i++){
            hands[i] = new JPanel();
            if(i == currentTurn % 4) {
                hands[i].setLayout(new GridLayout(1, players[i].HandSize));
                for (int j = 0; j < players[i].HandSize; j++) {
                    CardPanel CardHolder = new CardPanel(i, j);
                    CardHolder.add(new JLabel(players[i].hand.elementAt(j).getImg()));
                    CardHolder.setBorder(unhighlight);
                    CardHolder.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(CardHolder.getBorder() == unhighlight) {
                                if(numHighlighted != 0){
                                    if(players[CardHolder.playerPos].hand.elementAt(CardHolder.handPos).getRank() == numHighlighted){
                                        CardHolder.setBorder(highlighted);
                                        currentHighlights.addElement(CardHolder);
                                    }
                                    else{
                                        numHighlighted = players[CardHolder.playerPos].hand.elementAt(CardHolder.handPos).getRank();
                                        for(int i = 0; i < currentHighlights.size(); i++){
                                            currentHighlights.elementAt(i).setBorder(unhighlight);
                                            revalidate();
                                            repaint();
                                            currentHighlights.remove(i);
                                        }
                                        CardHolder.setBorder(highlighted);
                                        currentHighlights.addElement(CardHolder);
                                    }
                                }
                                else {
                                    CardHolder.setBorder(highlighted);
                                    currentHighlights.addElement(CardHolder);
                                }
                            }
                            else {
                                currentHighlights.remove(CardHolder);
                                CardHolder.setBorder(unhighlight);
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
                    hands[i].add(CardHolder);
                    CardHolder.setVisible(true);
                }
            }
            else if(i == (currentTurn + 2) % 4){
                hands[i].setLayout(new GridLayout(1, players[i].HandSize));
                for (int j = 0; j < players[i].HandSize; j++) {
                    JPanel CardHolder = new JPanel();
                    CardHolder.add(new JLabel(players[i].hand.elementAt(j).getBackImg()));
                    hands[i].add(CardHolder);
                    CardHolder.setVisible(true);
                }
            }
            else if(i == (currentTurn +1) % 4 || i == (currentTurn + 3) % 4){
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
