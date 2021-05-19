package it.polimi.ingsw.model;

/**
 * GreyMarble is a class that implements Marble. When a GreyMarble is taken from the market,
 * a resource of the type 'stone' can be added to the player's storages.
 */

public class GreyMarble implements Marble {

    /**
     * method whenDrawn adds a 'stone' to the resources that can be put in the storages
     * @param personalBoard - the player's personal board.
     */
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getWareHouse().addResource(Resource.STONE,1);

    }
    public String toString(){
        return "grey";
    }
}