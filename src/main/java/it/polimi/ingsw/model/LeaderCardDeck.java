package it.polimi.ingsw.model;

import Exceptions.ModelException;

import javax.naming.SizeLimitExceededException;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class LeaderCardDeck {
    private Stack<LeaderCard> deck;
    public LeaderCardDeck(){
        deck = new Stack<LeaderCard>();
    }
    public Stack<LeaderCard> getDeck() {
        return deck;
    }

    public LeaderCard draw() throws ModelException {
        if (deck.size() == 0) throw new ModelException("Leader cards finished!");
        return deck.pop();
    }
    public void addCard(LeaderCard leaderCard){
        deck.push(leaderCard);
    }
    public int getQuantity (){
        return deck.size();
    }

    public void randomize() {
        Collections.shuffle(deck);
    }
}

