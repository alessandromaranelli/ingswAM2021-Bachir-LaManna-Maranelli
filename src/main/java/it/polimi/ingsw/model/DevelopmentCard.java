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

    public DevelopmentCard(Color color, int level, int vp, Map<Resource, Integer> requirements, Map<Resource, Integer> input, Map<Resource, Integer> output, int fp){                //aggiunto per fare test in altre classi
        this.color = color;
        this.level = level;
        this.victoryPoints = vp;
        this.requirements = requirements;
        this.productionInput = input;
        this.productionOutput = output;
        this.faithPoint = fp;
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
