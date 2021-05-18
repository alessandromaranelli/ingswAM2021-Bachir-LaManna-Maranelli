package it.polimi.ingsw.model;

/**
 * LeaderCard that gives the player an additional type of production to be executed at every turn as a development card.
 * The requested development card is of level 2, and the production consists of one resource to be paid, indicated on the card,
 * and as reward a resource chosen by the player and a faith point
 */
public class LeaderCardSpecialProduction extends LeaderCard {
    private final Color color;
    private final Resource cost;

    /**
     * Constructor with all attributes
     * @param victoryPoints final points given by the card
     * @param description brief description of requirements and power
     * @param color color of requested development card
     * @param cost cost of the special production
     */
    public LeaderCardSpecialProduction(int victoryPoints, String description,Color color, Resource cost) {
        super(victoryPoints, description);
        this.color = color;
        this.cost = cost;
    }

    //level sempre 2
    //vicpoints sempre 4

    /**
     * Creates a SpecialProduction in the player's Production with it's cost
     * @param personalboard
     */
    @Override
    public void activateEffect(PersonalBoard personalboard) {
        personalboard.getProduction().addSpecialProduction(cost);
    }

    /**
     * asserts that the player has the requested development card
     * @param personalBoard
     * @return true if requirements are met
     */
    @Override
    public boolean verifyRequirements(PersonalBoard personalBoard) {
        return personalBoard.getCardSlot().controlForSpecialProduction(color,2);
    }

    public Color getColor() {
        return color;
    }

    public Resource getCost() {
        return cost;
    }
}
