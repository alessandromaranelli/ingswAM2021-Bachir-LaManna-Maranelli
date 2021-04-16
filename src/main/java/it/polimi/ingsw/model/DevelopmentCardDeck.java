package it.polimi.ingsw.model;//import java.util.List;
import Exceptions.ModelException;
import com.google.gson.Gson;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;

public class DevelopmentCardDeck {
    private Stack<DevelopmentCard> developmentCards;
    private Color color;
    private int level;

    public DevelopmentCardDeck(ArrayList<DevelopmentCard> developmentCardArrayList, Color color, int level){
        this.color = color;
        this.level = level;
        this.developmentCards = new Stack<>();
        for(DevelopmentCard developmentCard : developmentCardArrayList){
            developmentCards.push(developmentCard);
        }
        Collections.shuffle(developmentCards);
    }

    public Color getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }

    public Stack<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public DevelopmentCard viewTopCard(){
        return developmentCards.peek();
    }

    public void removeFromTop(){
        developmentCards.pop();
    }

    public Map<Resource, Integer> verifyRequirement(){
        return developmentCards.peek().getRequirements();
    }

}
