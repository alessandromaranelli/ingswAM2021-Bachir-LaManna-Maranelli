package it.polimi.ingsw.messages.answers;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.TurnState;

import java.util.List;
import java.util.Map;

public class ShowPlayerSituationMsg extends AnswerMsg{
    private String nickname;
    private Map<Resource,Integer> chest;
    private Integer[] storages;
    private List<Resource> resourceList;
    private int position;
    private Boolean[] popeFavours;
    private List<LeaderCard> leaderCardsPlayed;
    private TurnState phase;
    private String message;

    public ShowPlayerSituationMsg(String nickname, Map<Resource, Integer> chest, Integer[] storages, List<Resource> resourceList, int position, Boolean[] popeFavours, List<LeaderCard> leaderCardsPlayed, TurnState phase, String message) {
        this.nickname=nickname;
        this.chest = chest;
        this.storages = storages;
        this.resourceList=resourceList;
        this.position = position;
        this.popeFavours = popeFavours;
        this.leaderCardsPlayed = leaderCardsPlayed;
        this.phase = phase;
        this.message=message;
    }

    @Override
    public void processMessage(LightModel lightModel) {
        lightModel.show(phase,nickname,chest,storages,resourceList,position,popeFavours,leaderCardsPlayed);
    }

    @Override
    public void printMessage() {
        System.out.println(message);
    }
}
