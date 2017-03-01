import java.util.Scanner;

/**
 * Created by eric on 3/1/2017.
 */
public class Game {
    public static void main(String[] args){
        //main game loop, gets number of players then adjusts deck accordingly
        int numPlayers;
        Scanner input = new Scanner(System.in);
        System.out.println("How many players?");
        numPlayers = input.nextInt();
        Player[] players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            players[i] = new Player(i + 1);
        }
        Deck deck = new Deck();
        deck.Deal(players);

        while(true){
            //players must play cards in ascending order
        }
    }
}
