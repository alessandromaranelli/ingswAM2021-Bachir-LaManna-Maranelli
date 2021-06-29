package it.polimi.ingsw.model;
import com.google.gson.Gson;
import it.polimi.ingsw.client.GUI.DevelCardsSlotsPanel;

import java.io.*;
import java.util.ArrayList;

/**
 * The type Json parser. This class is used to build the decks of DevelopmentCards and
 * LeaderCards when the game starts.
 */
public class JsonParser {
    Gson gson = new Gson();
    File file;
    InputStream inputStream;
    Reader reader;

    /**
     * Deserialize DevelopmentCards.
     *
     * @return the array list of 12 DevelopmentCardDecks
     * @throws FileNotFoundException the file not found exception
     */
    public ArrayList<DevelopmentCardDeck> deserializeDevelopment() throws FileNotFoundException {
        inputStream = JsonParser.class.getResourceAsStream("/cards.json");
        //file = new File("src/main/resources/cards.json");
        //inputStream = new FileInputStream(file);
        reader= new InputStreamReader(inputStream);
        DevelopmentCard[] card= gson.fromJson(reader, DevelopmentCard[].class);
        ArrayList<DevelopmentCard> developmentCards= new ArrayList<>();
        ArrayList<DevelopmentCardDeck> developmentCardDecks = new ArrayList<>();
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
        return developmentCardDecks;
    }

    /**
     * Deserialize LeaderCards.
     *
     * @return the LeaderCardDeck
     * @throws FileNotFoundException the file not found exception
     */
    public LeaderCardDeck deserializeLeaders() throws FileNotFoundException {
        inputStream = JsonParser.class.getResourceAsStream("/ReductionLeaderCardsData.json");
        //file = new File("src/main/resources/ReductionLeaderCardsData.json");
        //inputStream= new FileInputStream(file);
        reader = new InputStreamReader(inputStream);
        LeaderCard []leaderCard = gson.fromJson(reader, LeaderCardReduction[].class);
        LeaderCardDeck leaderCardDeck  = new LeaderCardDeck();
        for(LeaderCard lc : leaderCard){
            leaderCardDeck.addCard(lc);
        }

        inputStream = JsonParser.class.getResourceAsStream("/SpecialProductionLeaderCardsData.json");
        //file = new File("src/main/resources/SpecialProductionLeaderCardsData.json");
        //inputStream= new FileInputStream(file);
        reader = new InputStreamReader(inputStream);
        leaderCard = gson.fromJson(reader, LeaderCardSpecialProduction[].class);
        for(LeaderCard lc : leaderCard){
            leaderCardDeck.addCard(lc);
        }

        inputStream = JsonParser.class.getResourceAsStream("/StorageLeaderCardsData.json");
        //file = new File("src/main/resources/StorageLeaderCardsData.json");
        //inputStream= new FileInputStream(file);
        reader = new InputStreamReader(inputStream);
        leaderCard = gson.fromJson(reader, LeaderCardStorage[].class);
        for(LeaderCard lc : leaderCard){
            leaderCardDeck.addCard(lc);
        }

        inputStream = JsonParser.class.getResourceAsStream("/WhiteMarbleLeaderCardsData.json");
        //file = new File("src/main/resources/WhiteMarbleLeaderCardsData.json");
        //inputStream= new FileInputStream(file);
        reader = new InputStreamReader(inputStream);
        leaderCard = gson.fromJson(reader, LeaderCardWhiteMarble[].class);
        for(LeaderCard lc : leaderCard){
            leaderCardDeck.addCard(lc);
        }
        leaderCardDeck.randomize();
        return leaderCardDeck;
    }

}
