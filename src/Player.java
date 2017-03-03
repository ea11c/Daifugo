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
    Player(int turn){
        turnOrder = turn;
        outOrder = -1;
        Passed = false;
        hand = new Vector<Card>(13, 5);
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

    @Override
    public String toString(){
        //Gives a string of all the cards in a player's hand.
        StringBuilder tempHand = new StringBuilder();
        for (Card i:hand) {
            if(i.getRank() > 10){
                switch (i.getRank()){
                    case 11:
                        tempHand.append("J of ");
                        break;
                    case 12:
                        tempHand.append("Q of ");
                        break;
                    case 13:
                        tempHand.append("K of ");
                        break;
                    case 14:
                        tempHand.append("A of ");
                        break;
                    case 15:
                        tempHand.append("2 of ");
                        break;
                }
            }
            else{
                tempHand.append(i.getRank());
                tempHand.append(" of ");
            }
            switch (i.getSuit()){
                case 1:
                    tempHand.append("spades ");
                    break;
                case 2:
                    tempHand.append("hearts ");
                    break;
                case 3:
                    tempHand.append("clubs ");
                    break;
                case 4:
                    tempHand.append("diamonds ");
                    break;
            }
        }
        return tempHand.toString();
    }
}
