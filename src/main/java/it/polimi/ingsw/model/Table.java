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

        Gson gson = new Gson();
        File file = new File("C:\\Users\\Alessandro\\IdeaProjects\\runMe\\src\\main\\java\\cards.json");
        InputStream inputStream = new FileInputStream(file);
        Reader reader= new InputStreamReader(inputStream);
        DevelopmentCard[] card= gson.fromJson(reader, DevelopmentCard[].class);
        ArrayList<DevelopmentCard> developmentCards= new ArrayList<>();
        this.developmentCardDecks = new ArrayList<>();
        DevelopmentCardDeck developmentCardDeck;
        for (int i=0; i<card.length;){
            developmentCards=new ArrayList<>();
            for (int j=0;j<4;j++){
                developmentCards.add(card[i]);
                i++;
            }
            developmentCardDeck=new DevelopmentCardDeck(developmentCards,card[i-1].getColor(),card[i-1].getLevel());
            developmentCardDecks.add(developmentCardDeck);
        }
        file = new File("C:\\Users\\Alessandro\\IdeaProjects\\runMe\\src\\main\\java\\ReductionLeaderCardsData.json");
        inputStream= new FileInputStream(file);
        reader = new InputStreamReader(inputStream);
        LeaderCard []leaderCard = gson.fromJson(reader, LeaderCardReduction[].class);
        leaderCardDeck  = new LeaderCardDeck();
        for(LeaderCard lc : leaderCard){
            leaderCardDeck.addCard(lc);
        }
        file = new File("C:\\Users\\Alessandro\\IdeaProjects\\runMe\\src\\main\\java\\SpecialProductionLeaderCardsData.json");
        inputStream= new FileInputStream(file);
        reader = new InputStreamReader(inputStream);
        leaderCard = gson.fromJson(reader, LeaderCardReduction[].class);
        for(LeaderCard lc : leaderCard){
            leaderCardDeck.addCard(lc);
        }
        file = new File("C:\\Users\\Alessandro\\IdeaProjects\\runMe\\src\\main\\java\\StorageLeaderCardsData.json");
        inputStream= new FileInputStream(file);
        reader = new InputStreamReader(inputStream);
        leaderCard = gson.fromJson(reader, LeaderCardReduction[].class);
        for(LeaderCard lc : leaderCard){
            leaderCardDeck.addCard(lc);
        }
        file = new File("C:\\Users\\Alessandro\\IdeaProjects\\runMe\\src\\main\\java\\WhiteMarbleLeaderCardsData.json");
        inputStream= new FileInputStream(file);
        reader = new InputStreamReader(inputStream);
        leaderCard = gson.fromJson(reader, LeaderCardReduction[].class);
        for(LeaderCard lc : leaderCard){
            leaderCardDeck.addCard(lc);
        }
        leaderCardDeck.randomize();

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
