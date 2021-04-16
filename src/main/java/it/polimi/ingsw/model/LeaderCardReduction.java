package it.polimi.ingsw.model;

import java.util.ArrayList;

public class LeaderCardReduction extends LeaderCard {
    private Color color1;
    private Color color2;
    private Resource reduction;

    //vp sempre 2

    public LeaderCardReduction(int victoryPoints, String description, Color color1, Color color2, Resource reduction) {
        super(victoryPoints, description);
        this.color1 = color1;
        this.color2 = color2;
        this.reduction = reduction;
    }

    @Override
    public void activateEffect(PersonalBoard personalboard) {
        personalboard.addReduction(reduction);
    }

    @Override
    public boolean verifyRequirements(PersonalBoard personalBoard) {
        return personalBoard.getCardSlot().controlForReduction(color1,color2);
    }

}
