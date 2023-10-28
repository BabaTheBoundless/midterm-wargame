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
    public static String getDrawSuit(String stringWithNumbers){ //will get suit of number and change name to be same as card graphics for display
        if (!stringWithNumbers.substring(1, 2).equals(" ") ){
            String number = stringWithNumbers.substring(0, 2);
            if (number.equals("11")) stringWithNumbers = "jack" + stringWithNumbers.substring(2);
            else if  (number.equals("12")) stringWithNumbers = "queen" + stringWithNumbers.substring(2);
            else if (number.equals("13")) stringWithNumbers =  "king" + stringWithNumbers.substring(2);
            else if (number.equals("14")) stringWithNumbers = "ace" + stringWithNumbers.substring(2);
        }
        String playerStringDraw = stringWithNumbers.replaceAll("\\s", "_");
        return playerStringDraw;

    }
    public static void printWinnerText(String winnerText, int playerScore, int cpuScore){ //prints the round winner 
        StdDraw.text(.25, .25, "Player Cards: " + playerScore);
        StdDraw.text(.75, .25, "CPU Cards: " + cpuScore);
        StdDraw.text(.5, .15, winnerText);
        StdDraw.pause(600);
    }
}

   
    

