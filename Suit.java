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
public enum Suit {
    HEARTS/*("\x03"),*/  ("♥"),
    SPADES/*("\x06"),*/  ("♠"), 
    CLUBS/*("\x05"),*/  ("♣"), 
    DIAMONDS/*("\x04");*/  ("♦");
    
    private final String symbol;
    
    Suit(final String symbol){
        this.symbol = symbol;
    }
    
    public String getSymbol() { 
        return symbol; 
    }
    
}
