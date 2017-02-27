import java.util.Random;

/**
 * Created by eric on 2/27/2017.
 */
public class Deck {
    private final Card[] AllCards;
    private int size;
    Deck(){
        AllCards = new Card[52];
        for(int i = 0; i < 13; i++){
            AllCards[i] = new Card(i + 3, 1); //initializes spades
        }
        for(int i = 13; i < 26; i++){
            AllCards[i] = new Card(i - 10, 2); //initializes hearts
        }
        for(int i = 27; i < 40; i++){
            AllCards[i] = new Card(i - 24, 3); //initializes clubs
        }
        for(int i = 40; i < 53; i++){
            AllCards[i] = new Card(i - 37, 4); //initializes diamonds
        }
        size = 52;
    }
    void DrawCard(Player player){
        Random rand = new Random();
        int card = rand.nextInt(size);
        player.hand.addElement(AllCards[card]);
        size = size - 1;

    }
}
