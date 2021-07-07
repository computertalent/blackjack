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
public class Card {
    
    private Suit suit;
    private Rank rank;
    
    Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }
    
    public Rank getRank(){
        return rank;
    }
    
    public int getValue(){
        return rank.getValue();
    }
    
    public Suit getSuit(){
        return suit;
    }
    
    public String getSymbol(){
        return (rank.getSymbol() + " " + suit.getSymbol());
    }
    
}
