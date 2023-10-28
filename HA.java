import java.util.Random;
//can increase StdDraw to make program go by faster

public class HA{

    public static void changeAfterRound(int[] winner, int[] loser, int winCount, int loserCount, String[] winnerString, String[] loserString){ //adds and removes cards after round
        int winnerOne = winner[0];
        int loserOne = loser[0];
        String winnerStringOne = winnerString[0];
        String loserStringOne = loserString[0];
        for (int i = 1; i < winner.length; i++){
            winner[i-1] = winner[i];
            winnerString[i-1] = winnerString[i];
        }
        winner[winCount - 1] = winnerOne;
        winnerString[winCount - 1] = winnerStringOne;


        for (int i = 1; i < loser.length; i++){
            loser[i-1] = loser[i];
            loserString[i-1] = loserString[i];
        }
        winner[winCount] = loserOne;
        winnerString[winCount] = loserStringOne;
    }

    public static String[] cardStringPlayer(String[] randomDeck){ //gets a deck in string form
        String[] playerDeckString = new String[52];
        for (int i = 0; i < 26; i++){
            playerDeckString[i] = randomDeck[i];
        }
        return playerDeckString;
    }
    public static String[] cardStringCPU(String[] randomDeck){
        String[] cpuDeckString = new String[52];
        for (int i = 0; i < 26; i++){
            cpuDeckString[i] = randomDeck[i + 26];
        }
        return cpuDeckString;
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
        double pause = Double.parseDouble(args[0]);
        StdDraw.setPenRadius(.1);
        StdDraw.enableDoubleBuffering();
        double cardWidth = .35;
        double cardHeight = .5;



        String[] suits = { "hearts", "spades", "diamonds", "clubs" };
        String[] cardValues = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" };
        String[] deck = instanceOfCard(suits, cardValues);
        String[] playDeck = randomDeck(deck);
        int totalCount = 0;
       /*/ for (int i = 0; i < playDeck.length; i++){
            System.out.println(playDeck[i]);
        }*/
        String winnerText;

        int[] playerDeck = new int[playDeck.length];
        int[] cpuDeck = new int[playDeck.length];
        int[] cardDeckNum = WarLibrary.getCardValue(playDeck);

        String[] playerDeckString = cardStringPlayer(playDeck);
        String[] cpuDeckString = cardStringCPU(playDeck);

        playerDeck = WarLibrary.dealCardsToPlayer(cardDeckNum);
        cpuDeck = WarLibrary.dealCardsToCPU(cardDeckNum);

        int playerScore = 26;
        int cpuScore = 26;
        String lastRoundWin = new String("Nobody");

        while (playerScore < 52 && cpuScore < 52 ){
            StdDraw.show();
           
    
            StdDraw.clear();
            totalCount++;
            String cpuDrawCard = WarLibrary.getDrawSuit(cpuDeckString[0]);
            String playerDrawCard = WarLibrary.getDrawSuit(playerDeckString[0]);
    
            StdDraw.picture(.25, .7, "assets/" + playerDrawCard + ".png", cardWidth, cardHeight);
            StdDraw.picture(.75, .7, "assets/" + cpuDrawCard + ".png", cardWidth, cardHeight);
            if (playerDeck[0] > cpuDeck[0]){
                changeAfterRound(playerDeck, cpuDeck, playerScore, cpuScore, playerDeckString, cpuDeckString);
                WarLibrary.printWinnerText("Player wins!", playerScore, cpuScore, pause);
                playerScore++;
                cpuScore--;
                StdDraw.text(.5, .35, lastRoundWin + " won the last round");
                lastRoundWin = "Player";
                continue;

            }
            if (cpuDeck[0] > playerDeck[0]){
                changeAfterRound(cpuDeck, playerDeck, cpuScore, playerScore, cpuDeckString, playerDeckString);
                WarLibrary.printWinnerText("CPU wins!", playerScore, cpuScore, pause);
                cpuScore++;
                playerScore--;
                StdDraw.text(.5, .35, lastRoundWin + " won the last round");
                lastRoundWin = "CPU";
                continue;
            }
            else{
                int randInt = (int) (Math.random() * 10) + 1;
                if (randInt >= 5){ // player wins tie
                    changeAfterRound(playerDeck, cpuDeck, playerScore, cpuScore, playerDeckString, cpuDeckString);
                    WarLibrary.printWinnerText("Player wins!", playerScore, cpuScore, pause);
                    playerScore++;
                    cpuScore--;
                    StdDraw.text(.5, .35, lastRoundWin + " won the last round");
                    lastRoundWin = "Player";
                    continue;
                }
                else{ // cpu wins tie
                    changeAfterRound(cpuDeck, playerDeck, cpuScore, playerScore, cpuDeckString, playerDeckString);
                    WarLibrary.printWinnerText("CPU wins!", playerScore, cpuScore, pause);
                    cpuScore++;
                    playerScore--;
                    StdDraw.text(.5, .35, lastRoundWin + " won the last round");
                    lastRoundWin = "CPU";
                    continue;
                }
            }    
        }
        StdDraw.clear();
        if (playerScore > cpuScore){
            System.out.println(WarLibrary.printWinner(playerScore, cpuScore, totalCount));
            StdDraw.text(.5, .5, "Player wins in " + totalCount + " turns!");
            
        }
        if (cpuScore > playerScore){
            System.out.println(WarLibrary.printWinner(playerScore, cpuScore, totalCount));
            StdDraw.text(.5, .5, "CPU wins in " + totalCount + " turns!");
        }
        StdDraw.show();

    }
}