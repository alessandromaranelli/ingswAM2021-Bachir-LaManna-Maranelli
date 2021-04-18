package it.polimi.ingsw.model;

public class LeaderCardSpecialProduction extends LeaderCard {
    private final Color color;
    private final Resource cost;

    public LeaderCardSpecialProduction(int victoryPoints, String description,Color color, Resource cost) {
        super(victoryPoints, description);
        this.color = color;
        this.cost = cost;
    }

    //level sempre 2
    //vicpoints sempre 4
    @Override
    public void activateEffect(PersonalBoard personalboard) {
        personalboard.getProduction().addSpecialProduction(cost);
    }

    @Override
    public boolean verifyRequirements(PersonalBoard personalBoard) {
        return personalBoard.getCardSlot().controlForSpecialProduction(color,2);
    }

}
