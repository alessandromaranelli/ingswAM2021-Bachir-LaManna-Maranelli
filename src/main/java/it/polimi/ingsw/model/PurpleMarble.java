package it.polimi.ingsw.model;

/**
 * PurpleMarble is a class that implements Marble. When a PurpleMarble is taken from the market,
 * a resource of the type 'servant' can be added to the player's storages.
 */

public class PurpleMarble implements Marble {

    /**
     * method whenDrawn adds a 'servant' to the resources that can be put in the storages
     * @param personalBoard - the player's personal board.
     */
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
        personalBoard.getWareHouse().addResource(Resource.SERVANT,1);
    }
    public String toString(){
        return "purple";
    }
}