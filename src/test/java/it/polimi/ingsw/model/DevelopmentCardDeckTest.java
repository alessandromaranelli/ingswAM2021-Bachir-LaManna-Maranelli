package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DevelopmentCardDeckTest {

    Table table= new Table();

    DevelopmentCardDeckTest() throws FileNotFoundException {
    }

    @Test
    void getColor() {
        assertEquals (Color.BLUE, table.getDevelopmentCardDeck(Color.BLUE, 1).getColor());
    }

    @Test
    void getLevel() {
        assertEquals (2, table.getDevelopmentCardDeck(Color.BLUE, 2).getLevel());
    }

    @Test
    void getDevelopmentCards() {
        ArrayList<DevelopmentCard> developmentCardArraylist= new ArrayList<>();
        Stack<DevelopmentCard> developmentCardStack= new Stack<>();
        DevelopmentCard d=new DevelopmentCard();
        developmentCardArraylist.add(d);
        developmentCardStack.add(d);
        DevelopmentCardDeck developmentCardDeck= new DevelopmentCardDeck(developmentCardArraylist,Color.GREEN,2);
        assertEquals(developmentCardStack,developmentCardDeck.getDevelopmentCards());
    }

    @Test
    void viewTopCard(){
        Map<Resource, Integer> map=new HashMap<>();
        ArrayList<DevelopmentCard> developmentCards= new ArrayList<>();
        map.put(Resource.SHIELD,3);
        map.put(Resource.STONE,4);
        DevelopmentCard developmentCard=new DevelopmentCard(Color.GREEN,1,2,map,map,map,3);
        developmentCards.add(developmentCard);
        DevelopmentCardDeck developmentCardDeck= new DevelopmentCardDeck(developmentCards,Color.GREEN,1);
        assertEquals (developmentCard,developmentCardDeck.viewTopCard());

    }

    @Test
    void verifyRequirement() {
        Map<Resource, Integer> map=new HashMap<>();
        ArrayList<DevelopmentCard> developmentCards= new ArrayList<>();
        map.put(Resource.SHIELD,3);
        map.put(Resource.STONE,4);
        DevelopmentCard developmentCard=new DevelopmentCard(Color.GREEN,1,2,map,map,map,3);
        developmentCards.add(developmentCard);
        DevelopmentCardDeck developmentCardDeck= new DevelopmentCardDeck(developmentCards,Color.GREEN,1);
        assertEquals (map,developmentCardDeck.verifyRequirement());
    }

    @Test
    void removeFromTop(){
        table.getDevelopmentCardDeck(Color.BLUE,2).removeFromTop();
        assertEquals(3,table.getDevelopmentCardDeck(Color.BLUE,2).getDevelopmentCards().size());

    }
}