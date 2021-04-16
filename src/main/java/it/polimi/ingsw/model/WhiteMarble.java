package it.polimi.ingsw.model;

public class WhiteMarble implements Marble {

    @Override
    public void whenDrawn(PersonalBoard personalBoard) {
       if(personalBoard.getWhiteMarble().isEmpty()) return;
       if(personalBoard.getWhiteMarble().size() == 1){
           personalBoard.getWareHouse().addResource(personalBoard.getWhiteMarble().get(0), 1);
       }
       else personalBoard.addWhiteMarbleToManage();
    }

}