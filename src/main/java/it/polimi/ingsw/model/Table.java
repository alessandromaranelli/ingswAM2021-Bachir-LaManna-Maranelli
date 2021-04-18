package it.polimi.ingsw.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import Exceptions.ModelException;
import com.google.gson.Gson;

public class Table {
    private Market market;
    private LeaderCardDeck leaderCardDeck;
    private ArrayList<DevelopmentCardDeck> developmentCardDecks;

    public Table() throws FileNotFoundException{
        market = new Market();
        JsonParser jsonParser= new JsonParser();
        developmentCardDecks=jsonParser.deserializeDevelopment();
        leaderCardDeck=jsonParser.deserializeLeaders();

    }

    public Market getMarket() {
        return market;
    }

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


    public LeaderCardDeck getLeaderCardDeck() {
        return leaderCardDeck;
    }

}
