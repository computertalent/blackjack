/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

/**
 *
 * @author propelahed
 */
public class Dealer extends Player {
    
    private Deck deck;
    private Player player;
    
    Dealer(int numDecks, Player player){
        deck = new Deck(numDecks);
        this.player = player;
    }
    
    public void showHand(boolean hideCard){
        
        int firstCardToShow = 0;
        
        if (hideCard){
            firstCardToShow = 1;
            System.out.println("----");    
        }
        
        for (int i=firstCardToShow; i<hand.size(); i++){
            System.out.println(hand.get(i).getSymbol());
        }
    }
    
    public void startNewGame(){
        deck.shuffle();
        player.hit(drawCard());
        hit(drawCard());
        player.hit(drawCard());
        hit(drawCard());
    }
    
    public Card drawCard(){
        return deck.drawCard();
    }
    
    public void clearHands(){
        hand.clear();
        player.getHand().clear();
    }
     
       
    
}
