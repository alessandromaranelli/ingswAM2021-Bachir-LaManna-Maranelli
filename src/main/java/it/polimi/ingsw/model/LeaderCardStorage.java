package it.polimi.ingsw.model;

import java.util.Map;

public class LeaderCardStorage extends LeaderCard {
    private Resource requirement;
    private Resource type;

    public LeaderCardStorage(int victoryPoints, String description, Resource requirement, Resource type) {
        super(victoryPoints, description);
        this.requirement = requirement;
        this.type = type;
    }
    //requirement quantity sempre 5 e vp 3

    @Override
    public void activateEffect(PersonalBoard personalboard) {
        personalboard.getWareHouse().addStorage(new Storage(2,type));
    }

    @Override
    public boolean verifyRequirements(PersonalBoard personalBoard) {
        return personalBoard.getWareHouse().controlForStorage(requirement,5);
    }
}
