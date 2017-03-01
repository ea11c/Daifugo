/**
 * Created by eric on 2/27/2017.
 */
public class Card {
    private final int rank; //2 =15, A = 14, K = 13, Q = 12, J = 11, 10 = 10, 9 = 9, ..., 3 = 3
    private final int suit; //1 = Spades, 2 = Hearts, 3 = Clubs, 4 = Diamonds
    private final String img = null; //represents file path
    Card(int r, int s){
        if(s > 0 && s < 5)
            suit = s;
        else
            suit = 0;
        if(r > 2 && r < 16)
            rank = r;
        else
            rank = 0;
    }

    int getRank(){
        return rank;
    }
    int getSuit(){
        return suit;
    }
}
