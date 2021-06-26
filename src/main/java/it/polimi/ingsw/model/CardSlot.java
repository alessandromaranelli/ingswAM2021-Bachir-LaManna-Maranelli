package it.polimi.ingsw.model;

import java.util.*;
import Exceptions.*;

/**
 * The type Card slot. There are three card slots in the personal board and each card slot contains a Stack of DevelopmentCard
 * (max 3 DevelopmentCards)
 */
public class CardSlot {
    private List<Stack<DevelopmentCard>> slots;
    private final int max;

    /**
     * Instantiates three new Card slots.
     */
    public CardSlot(){
        slots = new ArrayList<>();
        Stack<DevelopmentCard> stack = new Stack<>();
        slots.add(stack);
        stack = new Stack<>();
        slots.add(stack);
        stack = new Stack<>();
        slots.add(stack);
        max = 3;
    }

    /**
     * Get all the slots.
     *
     * @return the slots
     */
    public List<Stack<DevelopmentCard>> getSlots() {
        return slots;
    }

    /**
     * Get a slot.
     *
     * @param i the number of the slot requested
     * @return the slot
     */
    public Stack<DevelopmentCard> getSlot(int i){
        return slots.get(i-1);
    }

    /**
     * Get top card of the slot.
     *
     * @param i the number of the slot requested
     * @return the top card of the slot
     * @throws ModelException if the slot is empty
     */
    public DevelopmentCard getTopCardofSlot(int i) throws ModelException{
        if(getSlot(i).isEmpty() == true) throw new ModelException("Slot " + i + " is empty");
        return getSlot(i).peek();
    }

    /**
     * Control if a DevelopmentCard can be added to the slot
     *
     * @param card the DevelopmentCard
     * @param i the number of the slot requested
     * @return a boolean
     * @throws ModelException
     */

    public boolean controlCardToAdd(DevelopmentCard card, int i) throws ModelException{
        if(getSlot(i).isEmpty() == true){
            if(card.getLevel() == 1) return true;
            else return false;
        }
        if(getSlot(i).size() >= max) return false;
        if(getTopCardofSlot(i).getLevel() != card.getLevel()-1) return false;

        else return true;
    }

    /**
     * Add a DevelopmentCard to a slot.
     *
     * @param card the card
     * @param i the slot requested
     * @throws ModelException if the DevelopmentCard can't be added to the slot
     */
    public void addCardToSlot(DevelopmentCard card, int i) throws ModelException{
        if(controlCardToAdd(card, i) == true){
            getSlot(i).push(card);
        }
        else throw new ModelException("Card can't be added to slot " + i);
    }

    /**
     * Count victory point of all the slots
     *
     * @return the victory points
     */
    public int countVictoryPoint(){
        int n = 0;
        for(Stack<DevelopmentCard> i : slots){
            for(DevelopmentCard c : i){
                n = n + c.getVictoryPoints();
            }
        }
        return n;
    }

    /**
     * Count the DevelopmentCards in all the slots
     *
     * @return the number of DevelopmentCards
     */
    public int countCards(){
        int n = 0;
        for(Stack<DevelopmentCard> i : slots){
            for(DevelopmentCard c : i){
                n++;
            }
        }
        return n;
    }

    /**
     * Control if the requirements of a LeaderCard are satisfied
     *
     * @param color1 the first color of the requirements
     * @param color2 the second color of the requirements
     * @return the boolean
     */
    public boolean controlForReduction(Color color1, Color color2){
        int n1 = 0;
        int n2 = 0;
        for(Stack<DevelopmentCard> i : slots){
            for(DevelopmentCard c : i){
                if(c.getColor() == color1) n1++;
                else if(c.getColor() == color2) n2++;
            }
        }
        if(n1 >= 1 && n2 >= 1) return true;
        else return false;
    }

    /**
     * Control if the requirements of a LeaderCard are satisfied
     *
     * @param map of the requirements
     * @return the boolean
     */
    public boolean controlForWhiteMarble(Map<Color,Integer> map){
        Map<Color,Integer> map1= new HashMap<>();
        map1.put(Color.GREEN,0);
        map1.put(Color.YELLOW,0);
        map1.put(Color.BLUE,0);
        map1.put(Color.PURPLE,0);
        map1.putAll(map);

        for(Stack<DevelopmentCard> i : slots){
            for(DevelopmentCard c : i){
                if(map1.get(c.getColor())>0) map1.put(c.getColor(),map1.get(c.getColor())-1);
            }
        }
        if(map1.values().stream().filter(x -> x!=0).count()==0) return true;
        else return false;
    }

    /**
     * Control if the requirements of a LeaderCard are satisfied.
     *
     * @param color the color of the requirements
     * @param level the level of the requirements
     * @return the boolean
     */
    public boolean controlForSpecialProduction(Color color, int level){
        int n = 0;
        for(Stack<DevelopmentCard> i : slots){
            for(DevelopmentCard c : i){
                if(c.getColor() == color && c.getLevel() == level) n++;
            }
        }
        if(n >= 1) return true;
        else return false;
    }
}
