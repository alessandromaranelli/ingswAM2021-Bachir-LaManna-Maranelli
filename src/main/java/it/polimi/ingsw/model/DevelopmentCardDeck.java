package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;

/**
 * The type Development card deck consists of a stack of developmentCards of the same level and color.
 */
public class DevelopmentCardDeck {
    private Stack<DevelopmentCard> developmentCards;
    private Color color;
    private int level;

    /**
     * Instantiates a new Development card deck.
     *
     * @param developmentCardArrayList the list of DevelopmentCardst
     * @param color                    the color
     * @param level                    the level
     */
    public DevelopmentCardDeck(ArrayList<DevelopmentCard> developmentCardArrayList, Color color, int level){
        this.color = color;
        this.level = level;
        this.developmentCards = new Stack<>();
        for(DevelopmentCard developmentCard : developmentCardArrayList){
            developmentCards.push(developmentCard);
        }
        Collections.shuffle(developmentCards);
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets developmentCards.
     *
     * @return the stack of developmentCards
     */
    public Stack<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    /**
     * View top card developmentCard.
     *
     * @return the top developmentCard of the deck
     */
    public DevelopmentCard viewTopCard(){
        return developmentCards.peek();
    }

    /**
     * Removes the top developmentCard of the deck.
     */
    public void removeFromTop(){
        developmentCards.pop();
    }

    /**
     * Getter method to check the requirements of the top developmentCard of the deck.
     *
     * @return the map<Resource,Integer>
     */
    public Map<Resource, Integer> verifyRequirement(){
        return developmentCards.peek().getRequirements();
    }

    public boolean isEmpty(){
        return developmentCards.isEmpty();
    }

}
