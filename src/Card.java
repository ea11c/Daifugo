import javax.swing.*;

/**
 * Created by eric on 2/27/2017.
 */
public class Card implements Comparable<Card> {
    int Rank;
    char Suit;
    private ImageIcon Img;
    private ImageIcon BackImg;
    public Card(String S, int R){
        Suit = S.charAt(0);
        Rank = R;
        Img = new ImageIcon("src/Imgs/" + Rank + "_of_" + S + ".png");
        BackImg = new ImageIcon("src/Imgs/Back.png");
    }
    @Override
    public int compareTo(Card OtherCard){
        //-int if this > other, 0 if this == other, +int if this < other for a descending sort
        int result;
        if(this.Rank > OtherCard.Rank)
            result = -1;
        else if(this.Rank == OtherCard.Rank)
            result = 0;
        else
            result = 1;
        return result;
    }

    public int getRank(){
        return Rank;
    }
    public char getSuit(){
        return Suit;
    }
    public ImageIcon getImg(){ return Img;}
    public ImageIcon getBackImg(){return BackImg;}
}