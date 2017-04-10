import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by eric on 3/1/2017.
 */
public class Game {
    public static void play(int Players){
        //main game loop, gets number of players then adjusts deck accordingly
        int numPlayers = Players;
        Player[] players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++){
            players[i] = new Player(i + 1);
        }

        int currentRank;
        Card temp;
        int numPassed;
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
                        cardIndex = i.TakeTurn(currentRank);
                        if (cardIndex != -1) {
                            if (i.Passed) {
                                numPassed--;
                            }
                            i.Passed = false;
                            temp = i.hand.elementAt(cardIndex);
                            i.hand.remove(cardIndex);
                            if (i.hand.size() == 0) {
                                //player has gone out
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
