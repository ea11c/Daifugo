import java.util.Collections;
import java.util.Vector;

/**
 * Created by eric on 2/27/2017.
 */
public class Deck {
    private Vector<Card> deck = new Vector<Card>(52);
    private int size;
    Deck(){
        //adds all 52 cards to the deck
        //Image files obtained from http://nifty.stanford.edu/2004/EstellCardGame/

        //2's are the highest rank card, so have an internal value of 15
        int rIndex = 3;
        while(rIndex < 16){
            deck.addElement(new Card("spades", rIndex));
            deck.addElement(new Card("clubs", rIndex));
            deck.addElement(new Card("diamonds", rIndex));
            deck.addElement(new Card("hearts", rIndex));
            rIndex++;
        }
        size = 52;
        this.Shuffle();
    }
    private void Shuffle(){
        Collections.shuffle(deck);
    }

    public void Deal(Player[] players){
        //if cards are unable to be dealt out evenly, add 2's (spades) to the deck until it is possible.
        while(size % players.length != 0){
            deck.addElement(new Card("spades", 15));
            size++;
        }
        this.Shuffle();
        while(size >= players.length){
            for(int i = 0; i < players.length; i++){
                players[i].hand.addElement(deck.remove(0));
                size--;
            }
        }
        for(int i = 0; i<players.length; i++){
            players[i].hand.sort(Card::compareTo);
        }
    }

}
