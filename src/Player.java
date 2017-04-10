import java.util.Scanner;
import java.util.Vector;

/**
 * Created by eric on 2/27/2017.
 */
public class Player {
    final Vector<Card> hand;
    final int turnOrder;
    int outOrder;
    boolean Passed;
    int score;
    int HandSize;
    Player(int turn){
        turnOrder = turn;
        outOrder = -1;
        Passed = false;
        score = 0;
        hand = new Vector<Card>(13, 5);
        HandSize = 0;
    }

    int TakeTurn(int rank){
        int index;
        System.out.println("Select one of the following cards to play (0 - " + hand.size() + ") or -1 to pass.");
        System.out.println(this);
        Scanner input = new Scanner(System.in);
        index = input.nextInt();
        if(index == -1){
            return index;
        }
        while(hand.elementAt(index).getRank() <= rank){
            System.out.println("Your card must be greater than a " + rank);
            System.out.println("Please select a different card or pass");
            index = input.nextInt();
            if(index == -1){
                return index;
            }
        }
        return index;
    }

}
