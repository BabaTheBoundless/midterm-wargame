import java.util.Random;
//PROBLEM IS THAT CARDS CAN BE EQUAL TO 0 FOR SOME REASON

public class HA{

    public static void changeAfterRound(int[] winner, int[] loser, int winCount, int loserCount){ //adds and removes cards after round
        int winnerOne = winner[0];
        int loserOne = loser[0];
        for (int i = 1; i < winner.length; i++){
            winner[i-1] = winner[i];
        }
        winner[winCount - 1] = winnerOne;


        for (int i = 1; i < loser.length; i++){
            loser[i-1] = loser[i];
        }
        winner[winCount] = loserOne;


    }
    
    public static String[] instanceOfCard(String[] suits, String[] cardValues){  //creates card
        String[] newDeck = new String[suits.length * cardValues.length];
        for (int i = 0; i < suits.length; i++){
            String suit = suits[i];
            for (int j = 0; j < cardValues.length; j++){
                String cardValue = cardValues[j];
                String card = cardValue + " of " + suit;
                newDeck[cardValues.length * i + j] = card;
            }
        }
        return newDeck;
    }


    public static String[] randomDeck(String[] playDeck){ //randomizes deck
        String temp = new String("");
        Random random = new Random();
        for (int i = playDeck.length - 1; i > 0; i--){
            int randomIndex = random.nextInt(i + 1);
            temp = playDeck[i];
            playDeck[i] = playDeck[randomIndex];
            playDeck[randomIndex] = temp;
        }
        return playDeck;
    }
    
    public static void main(String[] args){
        String[] suits = { "hearts", "spades", "diamonds", "clubs" };
        String[] cardValues = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" };
        String[] deck = instanceOfCard(suits, cardValues);
        String[] playDeck = randomDeck(deck);
        int totalCount = 0;
       /*/ for (int i = 0; i < playDeck.length; i++){
            System.out.println(playDeck[i]);
        }*/
        int[] playerDeck = new int[playDeck.length];
        int[] cpuDeck = new int[playDeck.length];
        int[] cardDeckNum = WarLibrary.getCardValue(playDeck);

        playerDeck = WarLibrary.dealCardsToPlayer(cardDeckNum);
        cpuDeck = WarLibrary.dealCardsToCPU(cardDeckNum);
        int playerScore = 26;
        int cpuScore = 26;
        /*for (int i = 0; i < 26; i++){
            System.out.println("player1 card " + playerDeck[i]);
            System.out.println("cpu card " + cpuDeck[i] + "\n");
            
        }*/
        while (playerScore < 52 && cpuScore < 52 ){
            totalCount++;
            
            System.out.println("\nNEWLOOP");
            
            System.out.println("player " + playerDeck[0]);
            System.out.println("cpu " + cpuDeck[0]);
            System.out.println("player1 score " + playerScore);
            System.out.println("cpu score " + cpuScore);
            if (playerDeck[0] > cpuDeck[0]){
                changeAfterRound(playerDeck, cpuDeck, playerScore, cpuScore);
                System.out.println("p1 card win");
                playerScore++;
                cpuScore--;
                continue;

            }
            if (cpuDeck[0] > playerDeck[0]){
                changeAfterRound(cpuDeck, playerDeck, cpuScore, playerScore);
                System.out.println("CPU card win");
                cpuScore++;
                playerScore--;
                continue;
            }
            else{
                int randInt = (int) (Math.random() * 10) + 1;
                if (randInt > 5){
                    changeAfterRound(playerDeck, cpuDeck, playerScore, cpuScore);
                    System.out.println("tie P1 wins");
                    playerScore++;
                    cpuScore--;
                    continue;
                }
                else{
                    changeAfterRound(cpuDeck, playerDeck, cpuScore, playerScore);
                    System.out.println("tie CPU wins");
                    playerScore--;
                    cpuScore++;
                    continue;

                }

            }    
        }
        if (playerScore > cpuScore){
            System.out.println("player wins with " + playerScore + " cpu has + " + cpuScore);
            System.out.println(WarLibrary.printWinner(playerScore, cpuScore, totalCount));
        }
        if (cpuScore > playerScore){
            System.out.println("cpu wins with " + cpuScore + " score player has " + playerScore);
            System.out.println(WarLibrary.printWinner(cpuScore, playerScore, totalCount));
        }

        


    }
}