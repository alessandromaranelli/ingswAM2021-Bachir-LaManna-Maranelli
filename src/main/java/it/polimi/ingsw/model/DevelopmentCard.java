package it.polimi.ingsw.model;

import java.util.Map;

public class DevelopmentCard {
    private Color color;
    private int level;
    private int victoryPoints;
    private Map<Resource, Integer> requirements;
    private Map<Resource, Integer> productionInput;
    private Map<Resource, Integer> productionOutput;
    private int faithPoint;                                 //faithPoints prodotti dalla carta

    public DevelopmentCard(){};

    public DevelopmentCard(Color c, int lv, int vp, Map<Resource, Integer> rq, Map<Resource, Integer> input, Map<Resource, Integer> output, int fp){                //aggiunto per fare test in altre classi
        color = c;
        level = lv;
        victoryPoints = vp;
        requirements = rq;
        productionInput = input;
        productionOutput = output;
        faithPoint = fp;
    }

    public Map<Resource, Integer> getRequirements() {
        return requirements;
    }

    public Map<Resource, Integer> getProductionInput() {
        return productionInput;
    }

    public Map<Resource, Integer> getProductionOutput() {
        return productionOutput;
    }

    public int getFaithPoint(){
        return faithPoint;
    }

    public Color getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

}
