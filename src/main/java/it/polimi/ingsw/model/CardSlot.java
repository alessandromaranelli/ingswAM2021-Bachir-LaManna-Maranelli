package it.polimi.ingsw.model;

import java.util.*;
import Exceptions.*;

public class CardSlot {
    private List<Stack<DevelopmentCard>> slots;
    private final int max;

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

    public List<Stack<DevelopmentCard>> getSlots() {
        return slots;
    }

    public Stack<DevelopmentCard> getSlot(int i){
        return slots.get(i-1);
    }

    public DevelopmentCard getTopCardofSlot(int i) throws ModelException{
        if(getSlot(i).isEmpty() == true) throw new ModelException("Slot " + i + " is empty");
        return getSlot(i).peek();
    }

    //controlla se una carta può essere aggiunta a un determinato slot
    public boolean controlCardToAdd(DevelopmentCard card, int i) throws ModelException{
        if(getSlot(i).isEmpty() == true){
            if(card.getLevel() == 1) return true;
            else return false;
        }
        if(getSlot(i).size() >= max) return false;
        if(getTopCardofSlot(i).getLevel() != card.getLevel()-1) return false;

        else return true;
    }

    public void addCardToSlot(DevelopmentCard card, int i) throws ModelException{
        if(controlCardToAdd(card, i) == true){
            getSlot(i).push(card);
        }
        else throw new ModelException("Card can't be added to slot " + i);
    }

    public int countVictoryPoint(){
        int n = 0;
        for(Stack<DevelopmentCard> i : slots){
            for(DevelopmentCard c : i){
                n = n + c.getVictoryPoints();
            }
        }
        return n;
    }

    public int countCards(){
        int n = 0;
        for(Stack<DevelopmentCard> i : slots){
            for(DevelopmentCard c : i){
                n++;
            }
        }
        return n;
    }

    //controlli per i requisiti delle carte leader

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
