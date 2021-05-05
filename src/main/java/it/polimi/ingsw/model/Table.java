package it.polimi.ingsw.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Exceptions.ModelException;

/**
 * The type Table contains the marketTable, the LeaderCardDeck and 12 DevelopmentCardDecks. It is visible from
 * all the players.
 */
public class Table {
    private Market market;
    private LeaderCardDeck leaderCardDeck;
    private ArrayList<DevelopmentCardDeck> developmentCardDecks;

    /**
     * Instantiates a new Table.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public Table() throws FileNotFoundException{
        market = new Market();
        JsonParser jsonParser= new JsonParser();
        developmentCardDecks=jsonParser.deserializeDevelopment();
        leaderCardDeck=jsonParser.deserializeLeaders();

    }

    /**
     * Getter method to see the market.
     *
     * @return the market
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Gets a developmentCardDeck.
     *
     * @param color the color of the deck
     * @param level the level of the deck
     * @return the developmentCardDeck
     */
    public DevelopmentCardDeck getDevelopmentCardDeck(Color color, int level) {
        DevelopmentCardDeck deck = developmentCardDecks.get(0);
        for (DevelopmentCardDeck developmentCardDeck : developmentCardDecks){
            if(developmentCardDeck.getLevel() == level && developmentCardDeck.getColor().equals(color)){
                deck = developmentCardDeck;
                break;
            }
        }
        return deck;
    }

    /**
     * Gets the top card of a developmentCardDeck.
     *
     * @param color the color of the deck
     * @param level the level of the deck
     * @return the development card
     * @throws ModelException the model exception
     */
    public DevelopmentCard viewDevelopmentCard(Color color, int level) throws ModelException {
        DevelopmentCardDeck deck = developmentCardDecks.get(0);
        for (DevelopmentCardDeck developmentCardDeck : developmentCardDecks){
            if(developmentCardDeck.getLevel() == level && developmentCardDeck.getColor().equals(color)){
                deck = developmentCardDeck;
                break;
            }
        }
        if(deck.getDevelopmentCards().isEmpty()) throw new ModelException("Empty Deck");
        return deck.viewTopCard();
    }

    /**
     * Remove the top card of a developmentCardDeck.
     *
     * @param color the color of the deck
     * @param level the level of the deck
     * @throws ModelException the model exception
     */
    public void removeDevelopmentCard(Color color, int level) throws ModelException {
        DevelopmentCardDeck deck = developmentCardDecks.get(0);
        for (DevelopmentCardDeck developmentCardDeck : developmentCardDecks){
            if(developmentCardDeck.getLevel() == level && developmentCardDeck.getColor().equals(color)){
                deck = developmentCardDeck;
                break;
            }
        }
        if(deck.getDevelopmentCards().isEmpty()) throw new ModelException("Empty Deck");
        deck.removeFromTop();
    }

    //metodo usato per passare alla view tutte le possibili carte che si possono comprare
    public List<DevelopmentCard> getTopDevelopmentcards(){
        List<DevelopmentCard> topDevelopmentCards = new ArrayList<>();
        for(DevelopmentCardDeck d : developmentCardDecks){
            if(d.getDevelopmentCards().isEmpty() == false) topDevelopmentCards.add(d.viewTopCard());
        }
        return topDevelopmentCards;
    }


    /**
     * Getter method to get the LeaderCardDeck.
     *
     * @return the LeaderCardDeck
     */
    public LeaderCardDeck getLeaderCardDeck() {
        return leaderCardDeck;
    }

}
