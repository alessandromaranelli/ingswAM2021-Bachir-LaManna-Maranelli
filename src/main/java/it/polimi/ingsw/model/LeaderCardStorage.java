package it.polimi.ingsw.model;

import java.util.Map;

/**
 * LeaderCard that adds and additional storage, that can contain to resources of the indicated type.
 * Has 3 victory points. Needs 5 resources of the indicated type.
 */
public class LeaderCardStorage extends LeaderCard {
    private final Resource requirement;
    private final Resource type;

    /**
     * Class constructor with all class attributes
     * @param victoryPoints final points given by the card
     * @param description brief description of requirements and power
     * @param requirement the type of resource needed for activation
     * @param type the type of resource that can be put inside the additional storage
     */
    public LeaderCardStorage(int victoryPoints, String description, Resource requirement, Resource type, String path) {
        super(victoryPoints, description, path);
        this.requirement = requirement;
        this.type = type;
    }
    //requirement quantity sempre 5 e vp 3

    /**
     * creates a new Storage of the specified type that can contain 2 resources
     * @param personalboard
     */
    @Override
    public void activateEffect(PersonalBoard personalboard) {
        personalboard.getWareHouse().addStorage(new Storage(2,type));
    }

    /**
     * Cheks that the player has all the necessary resources to satisfy the requirements
     * @param personalBoard
     * @return true if meets the need
     */
    @Override
    public boolean verifyRequirements(PersonalBoard personalBoard) {
        return personalBoard.getWareHouse().controlForStorage(requirement,5);
    }

    public Resource getRequirement() {
        return requirement;
    }

    public Resource getType() {
        return type;
    }
}
