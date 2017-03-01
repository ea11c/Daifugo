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
