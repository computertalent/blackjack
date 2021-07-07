/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

/**
 *
 * @author propelahed
 */
public class Blackjack {
    
  public static void showHands(Player player, Dealer dealer, boolean hideCard){   
      if (!hideCard){
          sleep(1);
      }
      flushScreen();
      System.out.println("Your hand:");
      player.showHand();
      System.out.println("\nDealer's hand:");
      dealer.showHand(hideCard);
      System.out.println();
  }
  
  public static void sleep(int seconds){
      try {
        TimeUnit.SECONDS.sleep(seconds);
      } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
      }
  }
  
  public static void flushScreen(){ //throws IOException, InterruptedException{
//      System.out.print("\033[H\033[2J");  
//      System.out.flush(); 
 //       new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else{
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 
            }
        } catch (IOException | InterruptedException ex) {}
  }
  
  public static void splashIntro(){
      flushScreen();
      System.out.print("\n\n\n\u2665\u2660 WELCOME TO BLACKJACK! \u2663\u2666\n");
      sleep(1);
     
  }
  
  public static void splashOutro(){
      flushScreen();
      System.out.println("\n\n\n\u2665\u2660 GOODBYE! \u2663\u2666\n");
      sleep(1);
      flushScreen();
  }
  
  public static void displayScore(int playerWins, int dealerWins){
      System.out.print("\nYOUR TOTAL WINS: " + playerWins + "   ");
      System.out.println("DEALER TOTAL WINS: " + dealerWins);
  }
  
  public static boolean wantsToContinue(Scanner in){
      System.out.print("\nNEW GAME? (Y/N): ");
      return (getUserChoice(in));
  }
  
  public static boolean wantsToHit(Scanner in){
      System.out.print("HIT? (Y/N): ");
      return (getUserChoice(in));
  }
  
  public static boolean getUserChoice(Scanner in){
      boolean choice = false;
      char input = in.next().charAt(0);
     
      if (input == 'Y' || input == 'y'){
          choice = true;
      }
      else if (input != 'N' && input != 'n'){
          getUserChoice(in);
      }
      return choice;
  }
  
  public static int checkScore(Player player){
      
      int score =0;
      int numAces = 0;
      ArrayList<Card> hand = player.getHand();

      for (Card card: hand){
          if (card.getRank() == Rank.ACE){
              numAces++;
          }
          score+=card.getValue();
      }
      
      if (score > 21){  //sets highest possible score under 21 if has ace(s)
          while (numAces > 0){
              score-=10;
              if (score <= 21){
                  break;
              }else{
                  numAces--;
              } 
          }
      }
      return score;
  }
    
  public static void main(String[] args) throws UnsupportedEncodingException {
    
    PrintStream out = new PrintStream(System.out, true, "UTF-8");
    Scanner in = new Scanner(System.in);
    Player player = new Player();
    Dealer dealer = new Dealer(2, player);
    int playerWins = 0;
    int dealerWins = 0;
    int playerScore = 0;
    int dealerScore = 0;
    boolean gameOver = false;
    
    splashIntro();
    
    do{
        dealer.startNewGame();
        
        do{
            showHands(player, dealer, true);
            playerScore = checkScore(player);
            
            if (playerScore > 21){
                dealerWins++;
                System.out.println("DEALER WINS.");
                gameOver = true;
            }
            else if (playerScore == 21){
                playerWins++;
                System.out.println("YOU WIN!");
                gameOver = true;
            }
            else{
                if (wantsToHit(in)){
                    player.hit(dealer.drawCard());
                }
                else{   //dealer's turn
                    do{
                        showHands(player, dealer, false);   //reveal dealer's 1st card
                        dealerScore = checkScore(dealer);
                        
                        if (dealerScore > 21){
                            playerWins++;
                            System.out.println("YOU WIN!");
                            gameOver = true;
                        }
                        else if (dealerScore == 21){
                            dealerWins++;
                            System.out.println("DEALER WINS.");
                            gameOver = true;
                        }
                        else if (dealerScore >= 17){  //dealer does not hit at 17 or over              
                            if (dealerScore > playerScore){
                                dealerWins++;
                                System.out.println("DEALER WINS.");
                                gameOver= true;
                            }
                            else if (dealerScore < playerScore){
                                playerWins++;
                                System.out.println("YOU WIN!");
                                gameOver = true;
                            }
                            else{ //dealerScore == playerScore
                                System.out.println("PUSH.");
                                gameOver = true;
                            }
                        }
                        else{   //dealer has less than 17, must hit           
                            dealer.hit(dealer.drawCard());
                        } 
                        
                    }while (gameOver == false);
                }
            } 
        }while(gameOver == false);

        displayScore(playerWins, dealerWins);        
        dealer.clearHands();
        gameOver = false;
        
    }while(wantsToContinue(in));
    
    splashOutro();

  }
}
