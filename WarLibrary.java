import java.util.Random;
public class WarLibrary {

    public static int[] getCardValue(String[] cardDeck){ //gets card value from string
        int[] cardDeckNUMBERS = new int[cardDeck.length];
        for (int i = 0; i < cardDeckNUMBERS.length; i++) {
            String nums = cardDeck[i].substring(0, 2);
            String noSpace = nums.replaceAll("\\s", "");
            cardDeckNUMBERS[i] = Integer.parseInt(noSpace);
        }
        return cardDeckNUMBERS;

    }

    public static void inCaseOfTie(int num1, int num2){ //returns value in case of tie
        num1++;
    }

    public static String printWinner(int playerCount, int cpuCount, int totalTurns){ //returns string of winner
        if (playerCount > cpuCount){
            return "Player wins in " + totalTurns + " turns!";
        }
        else{
            return "CPU wins in " + totalTurns + " turns!";
        }
    }

    public static int[] dealCardsToPlayer(int cardDeck[]){ //deals first half to deck to player
        int[] playerArray = new int[cardDeck.length];
        
        // first half of deck is stored in playerArray1
        for (int i = 0; i < 26; i++) {
            playerArray[i] = cardDeck[i];

        }
        return playerArray;
    }
    
    public static int[] dealCardsToCPU(int cardDeck[]){ //deals second half of deck to player
        int[] cpuArray = new int[cardDeck.length];
        // second half of deck is stored in playerArray2
        for (int i = 0; i < 26; i++) {
            cpuArray[i] = cardDeck[26 + i];
        }
        return cpuArray;


    }
}

   
    

