package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * LeaderCard that lets you pay a developmentCard with a discount of the indicated resource.
 * Every leaderCard of this type has 2 victory points and requires two development cards to be activated
 */
public class LeaderCardReduction extends LeaderCard {
    private final Color color1;
    private final Color color2;
    private final Resource reduction;

    /**
     * Constructor with all attributes
     * @param victoryPoints final points given by the card
     * @param description brief description of requirements and power
     * @param color1 the color of one development card needed for activation
     * @param color2 the color of one development card needed for activation
     * @param reduction the resource to be subtracted from the development card cost
     */
    public LeaderCardReduction(int victoryPoints, String description, Color color1, Color color2, Resource reduction, String path) {
        super(victoryPoints, description, path);
        this.color1 = color1;
        this.color2 = color2;
        this.reduction = reduction;
    }

    /**
     * Adds in the player's personal board the resource to discounted
     * @param personalboard
     */
    @Override
    public void activateEffect(PersonalBoard personalboard) {
        personalboard.addReduction(reduction);
    }

    /**
     * Verifies the presence in the card slot of the to development cards required
     * @param personalBoard
     * @return true if meeting requirements
     */
    @Override
    public boolean verifyRequirements(PersonalBoard personalBoard) {
        return personalBoard.getCardSlot().controlForReduction(color1,color2);
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public Resource getReduction() {
        return reduction;
    }
}
