import java.util.Scanner;
import java.util.Random;

public class midtermWar {
    public static void main(String[] args) {

        // make each deck have a length of 52
        // keep pointer of where your current end of card is
        // arrays are immutable, so you can't actually
        // create two other arrays
        // use array copy to index for half of main deck

        // declaration
        Scanner myScanner = new Scanner(System.in);
        int suitCount = 0;
        int suitCount2 = 0;
        int zeroNum = 0;
        int totalScore = 0;
        int player1 = 26;
        int player2 = 26;
        int totalScore1 = 0;
        int totalScore2 = 0;
        int timesPlayed = Integer.parseInt(args[0]);
        boolean playOrNot = timesPlayed > 1 ? true : false;
        String playAgain = "y";

        if (playOrNot == false) {
            System.out.println("ERROR: TERMINATING");
        }
        // makes my suits and card values arrays
        String[] suits = { "hearts", "spades", "diamon", "clubss" };
        String[] cardValues = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" };

        int cardDeckSize = suits.length * cardValues.length;
        String[] cardDeck = new String[cardDeckSize];

        // creates instance of card
        for (int i = 0; i < suits.length; i++) {
            String suit = suits[i];
            for (int j = 0; j < cardValues.length; j++) {
                String cardValue = cardValues[j];
                String card = cardValue + " of " + suit;
                cardDeck[cardValues.length * i + j] = card;
            }
        }
        // store card value in flat array
        // deck is representationao of index in card

        // essentially clones cardDeck into randomDeck, which will soon be randomized
        String[] randomDeck = new String[cardDeck.length];
        for (int i = 0; i < cardDeck.length; i++) {
            randomDeck[i] = cardDeck[i];

        }
        outerLoop: while (timesPlayed > 0 && playAgain.equals("y")) {
            Random random = new Random();
            // randomizes deck
            String temp = new String("");
            for (int i = cardDeck.length - 1; i > 0; i--) {

                int randomIndex = random.nextInt(i + 1);
                temp = randomDeck[i];
                randomDeck[i] = randomDeck[randomIndex];
                randomDeck[randomIndex] = temp;

            }

            // store card value in flat array
            int[] cardDeckNUMBERS = new int[randomDeck.length];
            for (int i = 0; i < cardDeckNUMBERS.length; i++) {
                String nums = randomDeck[i].substring(0, 2);
                String noSpace = nums.replaceAll("\\s", "");
                cardDeckNUMBERS[i] = Integer.parseInt(noSpace);

            }
            // gets suits into their own array
            int[] suitsNumbers = new int[randomDeck.length];
            String[] suitsAlone = new String[randomDeck.length];
            for (int i = 0; i < cardDeckNUMBERS.length; i++) {
                String finalSuits = randomDeck[i].substring(randomDeck[i].length() - 6);
                suitsAlone[i] = finalSuits;
                if (suitsAlone[i].equals("diamon")) {
                    suitsNumbers[i] = 4;
                } else if (suitsAlone[i].equals("hearts")) {
                    suitsNumbers[i] = 3;
                } else if (suitsAlone[i].equals("spades")) {
                    suitsNumbers[i] = 2;
                } else if (suitsAlone[i].equals("clubss")) {
                    suitsNumbers[i] = 1;
                }

            }
            // creates array to store players cards
            int[] playerArray1 = new int[cardDeck.length];
            int[] playerArray2 = new int[cardDeck.length];
            // first half of deck is stored in playerArray1
            for (int i = 0; i < 26; i++) {
                playerArray1[i] = cardDeckNUMBERS[i];

            }
            System.out.println();
            // second half of deck is stored in playerArray2
            for (int i = 0; i < 26; i++) {
                playerArray2[i] = cardDeckNUMBERS[26 + i];

            }
            int totalCount = 0;
            int iterator = 0;
            int count = 0;
            int playerIndex = 0;
            player1 = 26;
            player2 = 26;

            while (player1 < 52 && player2 < 52) {

                if (iterator == 52) {
                    iterator = 0;
                }

                // instance of card1 uses player1 index but adds player2 index down below
                int card1 = playerArray1[playerIndex];
                int card2 = playerArray2[playerIndex];

                // if player 1 wins
                if (card1 > card2) {
                    // move player1[i] to left and add player1[0] and card2 to back of array
                    int holdValue = playerArray1[0];

                    for (int i = 0; i < playerArray1.length - 1; i++) {
                        playerArray1[i] = playerArray1[i + 1];

                    }
                    for (int i = 0; i < playerArray1.length - 1; i++) {
                        if (playerArray1[i] == 0) {
                            zeroNum = i;
                            break;
                        }
                    }
                    // moves suitsNumbers to left in case of tie
                    int tempSuits = suitsNumbers[0];
                    for (int i = 0; i < playerArray1.length - 1; i++) {
                        suitsNumbers[i] = suitsNumbers[i + 1];

                    }
                    suitsNumbers[suitsNumbers.length - 1] = tempSuits;
                    // move both card1 and card2 back to deck
                    playerArray1[zeroNum] = holdValue;
                    playerArray1[zeroNum + 1] = card2;

                    // do the same for player2

                    for (int i = 0; i < playerArray2.length - 1; i++) {
                        playerArray2[i] = playerArray2[i + 1];
                    }

                    player1 += 1;
                    player2 -= 1;

                }
                // if player 2 wins
                else if (card2 > card1) {
                    // move player2[i] to left and add player2[0] and card1 to back of array

                    int holdValue = playerArray2[0];

                    for (int i = 0; i < playerArray2.length - 1; i++) {
                        playerArray2[i] = playerArray2[i + 1];

                    }
                    for (int i = 0; i < playerArray2.length - 1; i++) {
                        if (playerArray2[i] == 0) {
                            zeroNum = i;
                            break;
                        }
                    }
                    // add suitsNumbers to back of array in case of tie
                    int tempSuits = suitsNumbers[0];
                    for (int i = 0; i < playerArray1.length - 1; i++) {
                        suitsNumbers[i] = suitsNumbers[i + 1];

                    }
                    suitsNumbers[suitsNumbers.length - 1] = tempSuits;
                    // add card1 and card2 to back of array
                    playerArray2[zeroNum] = holdValue;
                    playerArray2[zeroNum + 1] = card1;
                    // do the same for player1

                    for (int i = 0; i < playerArray1.length - 1; i++) {
                        playerArray1[i] = playerArray1[i + 1];

                    }

                    player2 += 1;
                    player1 -= 1;

                }
                // if there is a tie
                if (card1 == card2) {
                    if (suitsNumbers[playerIndex] > suitsNumbers[playerIndex + 26]) {
                        // if player1 wins, move everything to left and add card1 and 2 to back of array
                        int holdValue = playerArray1[0];

                        for (int i = 0; i < playerArray1.length - 1; i++) {
                            playerArray1[i] = playerArray1[i + 1];

                        }
                        for (int i = 0; i < playerArray1.length - 1; i++) {
                            if (playerArray1[i] == 0) {
                                zeroNum = i;
                                break;
                            }
                        }
                        int tempSuits = suitsNumbers[0];
                        for (int i = 0; i < playerArray1.length - 1; i++) {
                            suitsNumbers[i] = suitsNumbers[i + 1];

                        }
                        suitsNumbers[suitsNumbers.length - 1] = tempSuits;

                        playerArray1[zeroNum] = holdValue;
                        playerArray1[zeroNum + 1] = card2;

                        // do the same for player2

                        for (int i = 0; i < playerArray2.length - 1; i++) {
                            playerArray2[i] = playerArray2[i + 1];
                        }

                        player1 += 1;
                        player2 -= 1;

                    } else if (suitsNumbers[playerIndex + 26] > suitsNumbers[playerIndex]) {
                        int holdValue = playerArray2[0];

                        for (int i = 0; i < playerArray2.length - 1; i++) {
                            playerArray2[i] = playerArray2[i + 1];

                        }
                        for (int i = 0; i < playerArray2.length - 1; i++) {
                            if (playerArray2[i] == 0) {
                                zeroNum = i;
                                break;
                            }
                        }

                        int tempSuits = suitsNumbers[0];
                        for (int i = 0; i < playerArray1.length - 1; i++) {
                            suitsNumbers[i] = suitsNumbers[i + 1];

                        }
                        suitsNumbers[suitsNumbers.length - 1] = tempSuits;

                        playerArray2[zeroNum] = holdValue;
                        playerArray2[zeroNum + 1] = card1;
                        // do the same for player1

                        for (int i = 0; i < playerArray1.length - 1; i++) {
                            playerArray1[i] = playerArray1[i + 1];

                        }

                        player2 += 1;
                        player1 -= 1;

                    } else { // incase suits have a tie

                        double r = Math.random();
                        double r2 = Math.random();
                        r = (int) (r * 4 + 1);
                        r2 = (int) (r2 * 4 + 1);
                        if (r > r2) {

                            // player1 wins
                            int holdValue = playerArray1[0];

                            for (int i = 0; i < playerArray1.length - 1; i++) {
                                playerArray1[i] = playerArray1[i + 1];

                            }
                            for (int i = 0; i < playerArray1.length - 1; i++) {
                                if (playerArray1[i] == 0) {
                                    zeroNum = i;

                                }
                            }
                            int tempSuits = suitsNumbers[0];
                            for (int i = 0; i < playerArray1.length - 1; i++) {
                                suitsNumbers[i] = suitsNumbers[i + 1];

                            }
                            suitsNumbers[suitsNumbers.length - 1] = tempSuits;

                            playerArray1[zeroNum] = holdValue;
                            playerArray1[zeroNum + 1] = card2;

                            // do the same for player2

                            for (int i = 0; i < playerArray2.length - 1; i++) {
                                playerArray2[i] = playerArray2[i + 1];
                            }

                            player1 += 1;
                            player2 -= 1;
                        } else {
                            int holdValue = playerArray2[0];

                            for (int i = 0; i < playerArray2.length - 1; i++) {
                                playerArray2[i] = playerArray2[i + 1];

                            }
                            for (int i = 0; i < playerArray2.length - 1; i++) {
                                if (playerArray2[i] == 0) {
                                    zeroNum = i;

                                }
                            }

                            int tempSuits = suitsNumbers[0];
                            for (int i = 0; i < playerArray1.length - 1; i++) {
                                suitsNumbers[i] = suitsNumbers[i + 1];

                            }
                            suitsNumbers[suitsNumbers.length - 1] = tempSuits;

                            playerArray2[zeroNum] = holdValue;
                            playerArray2[zeroNum + 1] = card1;
                            // do the same for player1

                            for (int i = 0; i < playerArray1.length - 1; i++) {
                                playerArray1[i] = playerArray1[i + 1];

                            }

                            player2 += 1;
                            player1 -= 1;

                        }

                    }
                }
                // iterates count
                count++;
                iterator++;
                totalCount++;

            }

            timesPlayed--;
            // print statements for who wins. will take in scanner input to see if they want
            // to play again
            if (player1 >= 51) {
                System.out
                        .println("Game " + (totalScore1 + totalScore2 + 1) + ": Player wins in " + count
                                + " turns");
                totalScore1++;

            } else if (player2 >= 51) {
                System.out
                        .println("Game " + (totalScore1 + totalScore2 + 1) + ": CPU wins in " + count + " turns");
                totalScore2++;
            }
            if (timesPlayed > 0) {
                System.out.println("Play again? (y/n)");
                playAgain = myScanner.next();
                if (playAgain.equals("n") || (timesPlayed == 0)) {
                    System.out.println("Thanks for playing!!!!");
                    System.out.println("Player wins: " + totalScore1 + "\nCPU wins: " + totalScore2);
                    break outerLoop;
                }

            } else if (timesPlayed == 0) {
                System.out.println("Thanks for playing!!!!");
                System.out.println("Player wins: " + totalScore1 + "\nCPU wins: " + totalScore2);
                break outerLoop;
            }

        }

    }
}