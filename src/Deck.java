import java.util.Collections;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by eric on 2/27/2017.
 */
public class Deck {
    private Vector<Card> AllCards;
    private int size;
    Deck(){
        int index = 0;
        AllCards = new Vector<Card>(52, 4);
        for(int i = 3; i < 16; i++){ //initializes all spades
            AllCards.insertElementAt(new Card(i, 1), index);
            index++;
        }
        for(int i = 3; i < 16; i++){ //initializes all hearts
            AllCards.insertElementAt(new Card(i, 2), index);
            index++;
        }
        for(int i = 3; i < 16; i++){ //initializes all clubs
            AllCards.insertElementAt(new Card(i, 3), index);
            index++;
        }
        for(int i = 3; i < 16; i++){ //initializes all diamonds
            AllCards.insertElementAt(new Card(i, 4), index);
            index++;
        }
        size = 52;
        this.Shuffle();
    }
    private void Shuffle(){
        Collections.shuffle(AllCards);
    }

    public void Deal(Player[] players){
        //if cards are unable to be dealt out evenly, add 2's (spades) to the deck until it is possible.
        while(size % players.length != 0){
            AllCards.addElement(new Card(15, 1));
            size++;
        }
        this.Shuffle();
        while(size >= players.length){
            for(int i = 0; i < players.length; i++){
                players[i].hand.addElement(AllCards.remove(0));
                size--;
            }
        }
    }

}
