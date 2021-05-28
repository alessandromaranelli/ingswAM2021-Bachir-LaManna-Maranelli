package it.polimi.ingsw.model;

import java.util.Map;

/**
 * LeaderCard that gives the player the possibility to change a white Marble drawn from the market with the resource specified on the card.
 * The requirements are 2 development cards (one level2 and one level1) and it has 5 victory points
 */
public class LeaderCardWhiteMarble extends LeaderCard {
    private final Map<Color, Integer> requirement;
    private final Resource resource;

    public LeaderCardWhiteMarble(int victoryPoints, String description, Map<Color, Integer> requirement, Resource resource, String path) {
        super(victoryPoints, description, path);
        this.requirement = requirement;
        this.resource = resource;
    }

    //la prima sempre 2, la seconda 1 e vp sempre 5

    /**
     * Indicates in the player's personal board the resource that can be exchanged with a white marble
     * @param personalboard
     */
    @Override
    public void activateEffect(PersonalBoard personalboard) {
        personalboard.addWhiteMarble(resource);
    }

    /**
     * Checks if the player has in his slots the necessary development cards to activate the effect
     * @param personalBoard
     * @return true if the requirements are satisfied
     */
    @Override
    public boolean verifyRequirements(PersonalBoard personalBoard) {
        return personalBoard.getCardSlot().controlForWhiteMarble(requirement);
    }

    public Map<Color, Integer> getRequirement() {
        return requirement;
    }

    public Resource getResource() {
        return resource;
    }
}
