package it.polimi.ingsw.model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class DevelopmentCardDeckTest {

    Table table= new Table();

    public DevelopmentCardDeckTest() throws FileNotFoundException {
    }

    @Test
    public void getColor() {
        assertEquals (Color.BLUE, table.getDevelopmentCardDeck(Color.BLUE, 1).getColor());
    }

    @Test
    public void getLevel() {
        assertEquals (2, table.getDevelopmentCardDeck(Color.BLUE, 2).getLevel());
    }

    @Test
    public void getDevelopmentCards() {
        ArrayList<DevelopmentCard> developmentCardArraylist= new ArrayList<>();
        Stack<DevelopmentCard> developmentCardStack= new Stack<>();
        DevelopmentCard d=new DevelopmentCard();
        developmentCardArraylist.add(d);
        developmentCardStack.add(d);
        DevelopmentCardDeck developmentCardDeck= new DevelopmentCardDeck(developmentCardArraylist,Color.GREEN,2);
        assertEquals(developmentCardStack,developmentCardDeck.getDevelopmentCards());
    }

    @Test
    public void viewTopCard(){
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
    public void verifyRequirement() {
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
    public void removeFromTop(){
        table.getDevelopmentCardDeck(Color.BLUE,2).removeFromTop();
        assertEquals(3,table.getDevelopmentCardDeck(Color.BLUE,2).getDevelopmentCards().size());

    }
}