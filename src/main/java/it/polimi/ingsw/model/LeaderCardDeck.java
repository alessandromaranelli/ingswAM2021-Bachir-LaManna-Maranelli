package it.polimi.ingsw.model;

import Exceptions.ModelException;

import javax.naming.SizeLimitExceededException;
import java.io.Serializable;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * A deck that at the beginning has 16 leaderCards from which each player draws 4 of them from the top.
 * It is created at the beginning of the match and the order is random.
 */
public class LeaderCardDeck implements Serializable {
    private Stack<LeaderCard> deck;

    /**
     * Class constructor: creates an empty stack that is filled with the parser
     */
    public LeaderCardDeck(){
        deck = new Stack<LeaderCard>();
    }


    //USATO SOLO NEI TEST
    /**
     * Getter for the cards deck
     * @return
     */
    public Stack<LeaderCard> getDeck() {
        return deck;
    }

    /**
     * return the top card, consequently removes it from the deck
     * @return first leader
     * @throws ModelException
     */
    public LeaderCard draw() throws ModelException {
        if (deck.size() == 0) throw new ModelException("Leader cards finished!");
        return deck.pop();
    }

    /**
     * used to add on the top, at the beginning, one by one, the 16 cards.
     * @param leaderCard parsed from json
     */
    public void addCard(LeaderCard leaderCard){
        deck.push(leaderCard);
    }

    /**
     *Gets the number of cards in the deck
     */
    public int getQuantity (){
        return deck.size();
    }

    /**
     * Shuffles the deck
     */
    public void randomize() {
        Collections.shuffle(deck);
    }
}

