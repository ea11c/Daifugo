import java.util.Vector;

/**
 * Created by eric on 2/27/2017.
 */
public class Player implements Comparable<Player> {
    final Vector<Card> hand;
    int turnOrder;
    int outOrder;
    String name;
    boolean Passed;
    int score;
    Player(int turn, String n){
        turnOrder = turn;
        outOrder = -1;
        name = n;
        Passed = false;
        score = 0;
        hand = new Vector<Card>(13, 3);
    }
    @Override
    public int compareTo(Player other){
        return this.turnOrder - other.turnOrder;
    }
}
