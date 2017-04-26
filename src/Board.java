import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by eric on 4/3/2017.
 */
public class Board extends JFrame {
    private Player[] players;
    private int numPlayers;
    private int numOut = 0;
    private int currentTurn = 0;
    private Deck deck;
    private static int WinScore = 10;
    private JPanel display;
    private JLabel[] tableCards;
    private JPanel[] hands;
    static boolean Passing = false;
    private JPanel table;
    private int numPassed = 0;
    static int numHighlighted = 0;
    private int numPlayed = 0;
    private int currentRank = 0;
    private int lastPlayed = 0;
    static Vector<CardPanel> currentHighlights;
    static Vector<CardPanel> passingHighlights = new Vector<>(2);
    static int AmountPassing = 0;
    private JPanel buttons = new JPanel(new GridLayout(2,1));
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
        buttons.add(Pass);
        buttons.add(Play);
        Pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Passing) {
                    if (!players[currentTurn % 4].Passed) {
                        numPassed++;
                        players[currentTurn % 4].Passed = true;
                    }
                    currentTurn++;
                    if (numPassed == numPlayers) {
                        //everybody has passed, set current turn to last person to play a card and clear the table
                        currentTurn = lastPlayed;
                        numPassed = 0;
                        for (int i = 0; i < numPlayers; i++) {
                            players[i].Passed = false;
                        }
                        currentRank = 0;
                        numPlayed = 0;
                        table.removeAll();
                        table.add(buttons);
                        for (int i = 0; i < 4; i++) {
                            tableCards[i] = new JLabel();
                            table.add(tableCards[i]);
                        }
                        RotateTable();
                    }
                    Game();
                }
                else{
                    if(passingHighlights.size() == AmountPassing){
                        AmountPassing--;
                        players[numPlayers - currentTurn - 1].hand.add(passingHighlights.firstElement().card);
                        passingHighlights.remove(passingHighlights.firstElement());
                        if(currentTurn%4 == 0){
                            players[numPlayers - currentTurn - 1].hand.add(passingHighlights.firstElement().card);
                            passingHighlights.remove(passingHighlights.firstElement());
                            currentTurn++;
                        }
                        else if(currentTurn%4 == 1){
                            currentTurn = 4;
                            Passing = false;
                            for(int i = 0; i < numPlayers; i++){
                                players[i].hand.sort(Card::compareTo);
                            }
                        }
                        passingHighlights.clear();
                    }
                    Game();
                }
            }
        });
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Passing) {
                    if (currentHighlights.size() == numPlayed || numPlayed == 0) {
                        Card temp = currentHighlights.firstElement().card;
                        if (temp.getRank() > currentRank || (MainMenu.SkipRule && temp.getRank() == currentRank)) {
                            if (players[currentTurn % 4].Passed) {
                                players[currentTurn % 4].Passed = false;
                                numPassed--;
                            }
                            table.removeAll();
                            table.add(buttons);
                            for (int i = 0; i < currentHighlights.size(); i++) {
                                tableCards[i] = new JLabel(currentHighlights.elementAt(i).card.getImg());
                                table.add(tableCards[i]);
                                players[currentTurn % 4].hand.remove(currentHighlights.elementAt(i).card);
                                hands[currentTurn % 4].remove(currentHighlights.elementAt(i));
                            }
                            lastPlayed = currentTurn;
                            if (players[currentTurn % 4].hand.isEmpty()) {
                                numOut++;
                                players[currentTurn % 4].outOrder = numOut;
                            }
                            numPlayed = currentHighlights.size();
                            currentTurn++;
                            if(MainMenu.SkipRule && temp.getRank() == currentRank){
                                //optional rule is in place to skip the next player if cards of the same rank are played
                                currentTurn++;
                            }
                            currentRank = temp.getRank();
                            if(temp.getRank() == 15){
                                //2 was played, so clear the table and take another turn;
                                currentTurn = lastPlayed;
                                numPassed = 0;
                                for (int i = 0; i < numPlayers; i++) {
                                    players[i].Passed = false;
                                }
                                currentRank = 0;
                                numPlayed = 0;
                                table.removeAll();
                                table.add(buttons);
                                for (int i = 0; i < 4; i++) {
                                    tableCards[i] = new JLabel();
                                    table.add(tableCards[i]);
                                }
                                RotateTable();
                            }
                            currentHighlights.clear();
                            Game();
                        }
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
        if(numOut == numPlayers - 1){
            //all ranks for next hand can be set and cards reshuffled/dealt
            for(int i = 0; i < numPlayers; i++){
                if(players[i].outOrder == 1){
                    players[i].score += 2;
                    if(players[i].score == WinScore){
                        JOptionPane.showMessageDialog(this, players[i].name + "wins the game!");
                        dispose();
                    }
                }
                else if(players[i].outOrder == 2){
                    players[i].score += 1;
                    if(players[i].score == WinScore){
                        JOptionPane.showMessageDialog(this, players[i].name + "wins the game!");
                        dispose();
                    }
                }
                if(players[i].outOrder > 0) {
                    players[i].turnOrder = players[i].outOrder - 1;
                }
                else{
                    players[i].turnOrder = numPlayers - 1;
                }
            }
            Arrays.sort(players, Player::compareTo);
            String results = "";
            for(int i = 0; i < numPlayers; i++){
                results = results + players[i].name + ": " + players[i].score + " points\n";
            }
            ClearAll();
            JOptionPane.showMessageDialog(this, results);
            //losing player gives winning player top 2 cards, 2nd worst gives 2nd top 1 card.  Winner and 2nd then give corresponding number of cards
            //of their choice back.
            InitHands();
            AmountPassing = 2;
            PassCards(0, numPlayers - 1);
            PassCards(1, numPlayers - 2);
            currentTurn = 0;
        }
        if(Passing){
            if(currentTurn == 0) {
                InitHands();
                JOptionPane.showMessageDialog(this, "Select your 2 cards to pass then press Pass.");
            }
            else if(currentTurn%4 == 1) {
                RotateTable();
                JOptionPane.showMessageDialog(this, "Select 1 card to pass then press Pass.");
            }
        }
        else if(currentTurn != 0){
            RotateTable();
            JOptionPane.showMessageDialog(this, players[currentTurn%4].name + "'s turn.");
        }
        else{
            InitHands();
            JOptionPane.showMessageDialog(this, players[currentTurn%4].name + "'s turn.");
        }
        add(display, BorderLayout.CENTER);
        add(hands[currentTurn % 4], BorderLayout.SOUTH);
        add(hands[(currentTurn + 1) % 4], BorderLayout.WEST);
        add(hands[(currentTurn + 2) % 4], BorderLayout.NORTH);
        add(hands[(currentTurn + 3) % 4], BorderLayout.EAST);
        revalidate();
        repaint();
    }
    private void PassCards(int p1, int p2){
        //add best card(s) from p2's hand into p1's according to their finish position
        Passing = true;
        players[p1].hand.add(players[p2].hand.remove(0));
        if(p1 == 0){
            players[p1].hand.add(players[p2].hand.remove(0));
        }
        System.out.println(players[p1].name + " cards:\n");
        for(int i = 0; i < players[p1].hand.size(); i++){
            System.out.println(players[p1].hand.elementAt(i).getRank() + " of " + players[p1].hand.elementAt(i).getSuit());
        }
        System.out.println(players[p2].name + " cards:\n");
        for(int i = 0; i < players[p2].hand.size(); i++){
            System.out.println(players[p2].hand.elementAt(i).getRank() + " of " + players[p2].hand.elementAt(i).getSuit());
        }
        players[p1].hand.sort(Card::compareTo);
    }
    private void ClearAll(){
        //resets everything to a new game state
        numOut = 0;
        AmountPassing = 0;
        Passing =false;
        numHighlighted = 0;
        numPassed = 0;
        numPlayed = 0;
        lastPlayed = 0;
        currentTurn = 0;
        currentRank = 0;
        currentHighlights.clear();
        deck = new Deck();
        for(int i = 0; i < numPlayers; i++){
            players[i].hand.clear();
        }
        table.removeAll();
        table.add(buttons);
        for(int i = 0; i < 4; i++){
            tableCards[i] = new JLabel();
            table.add(tableCards[i]);
        }
        remove(hands[0]);
        remove(hands[1]);
        remove(hands[2]);
        remove(hands[3]);
        hands[0].removeAll();
        hands[1].removeAll();
        hands[2].removeAll();
        hands[3].removeAll();
        deck.Deal(players);
    }
    private void RotateTable(){
        remove(hands[0]);
        remove(hands[1]);
        remove(hands[2]);
        remove(hands[3]);
        hands[0].removeAll();
        hands[1].removeAll();
        hands[2].removeAll();
        hands[3].removeAll();
        for(int i = 0; i < numPlayers; i++){
            if(i == currentTurn % 4){
                hands[i].setLayout(new GridLayout(1, players[i].hand.size()));
                for(int j = 0; j < players[i].hand.size(); j++) {
                    CardPanel CardHolder = new CardPanel(players[i].hand.elementAt(j), i);
                    CardHolder.add(new JLabel(CardHolder.card.getImg()));
                    hands[i].add(CardHolder);
                }
            }
            else if(i == (currentTurn + 2) % 4){
                hands[i].setLayout(new GridLayout(1, players[i].hand.size()));
                for(int j = 0; j < players[i].hand.size(); j++) {
                    CardPanel CardHolder = new CardPanel(players[i].hand.elementAt(j), i);
                    CardHolder.add(new JLabel(CardHolder.card.getBackImg()));
                    hands[i].add(CardHolder);
                }
            }
            else{
                hands[i].setLayout(new GridLayout(players[i].hand.size(), 1));
                for(int j = 0; j < players[i].hand.size(); j++) {
                    CardPanel CardHolder = new CardPanel(players[i].hand.elementAt(j), i);
                    CardHolder.add(new JLabel(CardHolder.card.getBackImg()));
                    hands[i].add(CardHolder);
                }
            }
        }
    }
    private void InitPlayers(){
        players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            players[i] = new Player(i, MainMenu.names[i]);
        }
    }
    private void InitHands(){
        hands = new JPanel[numPlayers];
        currentHighlights = new Vector<>(4);
        for(int i = 0; i < numPlayers; i++){
            hands[i] = new JPanel();
            if(i == currentTurn % 4) {
                hands[i].setLayout(new GridLayout(1, players[i].hand.size()));
                for (int j = 0; j < players[i].hand.size(); j++) {
                    CardPanel CardHolder = new CardPanel(players[i].hand.elementAt(j), i);
                    CardHolder.add(new JLabel(CardHolder.card.getImg()));
                    hands[i].add(CardHolder);
                    CardHolder.setVisible(true);
                }
            }
            else if(i == (currentTurn + 2) % 4){
                hands[i].setLayout(new GridLayout(1, players[i].hand.size()));
                for (int j = 0; j < players[i].hand.size(); j++) {
                    CardPanel CardHolder = new CardPanel(players[i].hand.elementAt(j), i);
                    CardHolder.add(new JLabel(CardHolder.card.getBackImg()));
                    hands[i].add(CardHolder);
                    CardHolder.setVisible(true);
                }
            }
            else if(i == (currentTurn +1) % 4 || i == (currentTurn + 3) % 4){
                hands[i].setLayout(new GridLayout(players[i].hand.size(), 1));
                for (int j = 0; j < players[i].hand.size(); j++) {
                    CardPanel CardHolder = new CardPanel(players[i].hand.elementAt(j), i);
                    CardHolder.add(new JLabel(CardHolder.card.getBackImg()));
                    hands[i].add(CardHolder);
                    CardHolder.setVisible(true);
                }
            }
        }
    }
}
