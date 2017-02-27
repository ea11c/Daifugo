import java.util.Vector;

/**
 * Created by eric on 2/27/2017.
 */
public class Player {
    public final int hand_size;
    public final Vector<Card> hand;
    public final int turnOrder;
    Player(int turn){
        turnOrder = turn;
        hand_size = 0;
        hand = new Vector<Card>(hand_size, 10);
    }
}
