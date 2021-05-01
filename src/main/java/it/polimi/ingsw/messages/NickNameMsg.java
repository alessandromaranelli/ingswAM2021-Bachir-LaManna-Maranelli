package it.polimi.ingsw.messages;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Controller;

import java.io.IOException;

public class NickNameMsg extends CommandMsg {

    private String nickName;

    public NickNameMsg(String nickName) {
        this.nickName = nickName;
    }

    public void processMessage(ClientHandler clientHandler, Controller controller) throws IOException {
        for (Player p : controller.getGame().getPlayers()){
            if (p.getNickname()==nickName){
                try {
                    clientHandler.getOutput().writeObject("NickName già preso. Riprova!");
                } catch (IOException e) {
                    System.out.println("non sono riuscito a stampare" );
                    e.printStackTrace();
                }
                return;
            }
        }
        if (controller.getGame().getPlayers().size()==0){
            controller.getGame().getPlayers().add(new Player(nickName,1,controller.getGame()));
            clientHandler.setPlayerID(1);
            clientHandler.getOutput().writeObject("You joined the server.");
            clientHandler.getOutput().writeObject("You are player Number " + clientHandler.getPlayerID());
            clientHandler.getOutput().writeObject("Please insert the number of players of the game");
        }
        else{
            controller.getGame().getPlayers().add(new Player(nickName,controller.getGame().getPlayers().size(),controller.getGame()));
            clientHandler.setPlayerID(controller.getGame().getPlayers().size());
            clientHandler.getOutput().writeObject("You joined the server.");
            clientHandler.getOutput().writeObject("You are player Number " + clientHandler.getPlayerID());

        }
        clientHandler.setReady();
    }
}