/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author propelahed
 */
public class Deck {
    
    private ArrayList<Card> Deck;
    private int numDecks;
    private int currentCardIndex = 0;
    
    Deck(int numDecks){
        this.numDecks = numDecks;
        Deck = new ArrayList<Card>();
        buildDeck();
    }
    
    private void buildDeck(){
        for (int i=0; i<numDecks; i++){
           for (Rank rank : Rank.values()) {     
                for (Suit suit: Suit.values()){
                    Card card = new Card(rank, suit);
                    Deck.add(card);
                }
            }
        }
    }
    
    public void shuffle(){
        Collections.shuffle(Deck);
        currentCardIndex = 0;       
    }
    
    public Card drawCard(){
       return Deck.get(currentCardIndex++);
    } 
    
}
