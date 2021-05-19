package it.polimi.ingsw.model;

/**
 * WhiteMarble is a class that implements Marble. When a WhiteMarble is taken from the market,
 * if the player has played a LeaderCard with the WhiteMarble ability, he can add a resource
 * of the type indicated by the LeaderCard to the storages, otherwise the WhiteMarble does not
 * have any effect.
 */

public class WhiteMarble implements Marble {

    /**
     * method whenDrawn checks if the player has played at least a LeaderCard with the WhiteMarble
     * ability. If not, the method returns, if there is a LeaderCard a resource of the type indicated
     * by the LeaderCard is added to the resources that can be put in the storages. If there are two
     * LeaderCards with the same ability, the player can choose the resource to add.
     * @param personalBoard - the player's personal board
     */
    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
       if(personalBoard.getWhiteMarble().isEmpty()) return;
       if(personalBoard.getWhiteMarble().size() == 1){
           personalBoard.getWareHouse().addResource(personalBoard.getWhiteMarble().get(0), 1);
       }
       else personalBoard.addWhiteMarbleToManage();
    }
    public String toString(){
        return "white";
    }
}