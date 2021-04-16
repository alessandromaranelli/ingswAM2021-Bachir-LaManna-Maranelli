package it.polimi.ingsw.model;

import java.util.Map;

public class LeaderCardWhiteMarble extends LeaderCard {
    private Map<Color, Integer> requirement;
    private Resource resource;

    public LeaderCardWhiteMarble(int victoryPoints, String description, Map<Color, Integer> requirement, Resource resource) {
        super(victoryPoints, description);
        this.requirement = requirement;
        this.resource = resource;
    }

    //la prima sempre 2, la seconda 1 e vp sempre 5
    @Override
    public void activateEffect(PersonalBoard personalboard) {
        personalboard.addWhiteMarble(resource);
    }

    @Override
    public boolean verifyRequirements(PersonalBoard personalBoard) {
        return personalBoard.getCardSlot().controlForWhiteMarble(requirement);
    }
}
