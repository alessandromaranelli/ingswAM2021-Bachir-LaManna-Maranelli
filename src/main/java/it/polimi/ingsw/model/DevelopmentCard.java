package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.Map;

/**
 * The DevelopmentCards are characterized by a color, a level, a number of victoryPoints and a map of
 * requirements necessary to buy it. Every developmentCard is useful in the production phase: it provides
 * a productionOutput in change of a productionInput.
 */
public class DevelopmentCard implements Serializable {
    private Color color;
    private int level;
    private int victoryPoints;
    private Map<Resource, Integer> requirements;
    private Map<Resource, Integer> productionInput;
    private Map<Resource, Integer> productionOutput;
    private int faithPoint;                                 //faithPoints prodotti dalla carta

    /**
     * Instantiates a new Development card.
     */
    public DevelopmentCard(){};

    /**
     * Instantiates a new Development card.
     *
     * @param color        the color
     * @param level        the level
     * @param vp           the victoryPoints
     * @param requirements the requirements
     * @param input        the input
     * @param output       the output
     * @param fp           the faithPoint
     */
    public DevelopmentCard(Color color, int level, int vp, Map<Resource, Integer> requirements, Map<Resource, Integer> input, Map<Resource, Integer> output, int fp){                //aggiunto per fare test in altre classi
        this.color = color;
        this.level = level;
        this.victoryPoints = vp;
        this.requirements = requirements;
        this.productionInput = input;
        this.productionOutput = output;
        this.faithPoint = fp;
    }

    /**
     * Gets requirements.
     *
     * @return the requirements
     */
    public Map<Resource, Integer> getRequirements() {
        return requirements;
    }

    /**
     * Gets production input.
     *
     * @return the production input
     */
    public Map<Resource, Integer> getProductionInput() {
        return productionInput;
    }

    /**
     * Gets production output.
     *
     * @return the production output
     */
    public Map<Resource, Integer> getProductionOutput() {
        return productionOutput;
    }

    /**
     * Get faith point.
     *
     * @return the int representing the faithPoints
     */
    public int getFaithPoint(){
        return faithPoint;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets victory points.
     *
     * @return the victory points
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

}
