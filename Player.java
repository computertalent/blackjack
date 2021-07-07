/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;

/**
 *
 * @author propelahed
 */
public class Player{
    
    protected ArrayList<Card> hand;
    
    Player(){
        hand = new ArrayList<Card>();
    }
    
    public void hit(Card card){
        hand.add(card);
    }
    
    public void showHand(){
        for (Card card: hand){
            System.out.println(card.getSymbol());
        }
    }
    
    public ArrayList<Card> getHand(){
        return hand;
    }
    
    
}
