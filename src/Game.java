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
        int currentRank = 0;
        Card temp;
        int numPassed = 0;
        int numOut = 0; //tracks number of players that have emptied their hand
        while(numOut < numPlayers - 1){
            //players must play cards in ascending order
            int cardIndex;
            numPassed = 0;
            currentRank = 0;
            for(Player i:players){
                i.Passed = false;
            }
            while(numPassed < numPlayers) {
                for (Player i : players) {
                    if(i.outOrder == -1) {
                        System.out.println("Player " + i.turnOrder + "'s turn");
                        System.out.println("The card to beat is a " + currentRank);
                        cardIndex = i.TakeTurn(currentRank);
                        if (cardIndex != -1) {
                            if (i.Passed) {
                                numPassed--;
                            }
                            i.Passed = false;
                            temp = i.hand.elementAt(cardIndex);
                            i.hand.remove(cardIndex);
                            if (i.hand.size() == 0) {
                                System.out.println("Congratulations, player " + i.turnOrder + " has gone out!");
                                i.outOrder = numOut + 1;
                                numOut++;
                            }
                            currentRank = temp.getRank();
                        }
                        else {
                            if (!i.Passed)
                                numPassed++;
                            i.Passed = true;
                            System.out.println("Player " + i.turnOrder + " passed.");
                        }
                    }
                }
            }
        }
    }
}
