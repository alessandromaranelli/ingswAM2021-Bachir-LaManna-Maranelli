package it.polimi.ingsw.model;

import it.polimi.ingsw.server.Controller;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int currentPlayerID;
    private CPU cpu;
    private boolean soloMatch = false;
    private Table table;
    private ArrayList<VaticanReportSection> vaticanReportSections= new ArrayList<>();

    public Game() throws FileNotFoundException {
        this.table = new Table();
        VaticanReportSection vaticanReportSection= new VaticanReportSection(5,1,players);
        vaticanReportSections.add(vaticanReportSection);
        vaticanReportSection= new VaticanReportSection(12,2,players);
        vaticanReportSections.add(vaticanReportSection);
        vaticanReportSection= new VaticanReportSection(19,3,players);
        vaticanReportSections.add(vaticanReportSection);
    }

    public void start(){
        if (players.size()==1){
            cpu= new CPU(this);
            soloMatch=true;
        }
        setCurrentPlayer(players.get(0));
    }

    public boolean isSoloMatch() {
        return soloMatch;
    }

    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    public void createNewPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public CPU getCpu() {
        return cpu;
    }

    public Table getTable() {
        return table;
    }

    public Player getPlayerById(int Id){
        return players.get(Id-1);
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
        this.currentPlayerID = player.getPlayerID();
        currentPlayer.setAsCurrentPlayer();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Object nextPlayer() {
        if (soloMatch){
            currentPlayerID = (currentPlayerID == players.size()) ? 1 : currentPlayerID+1;
            setCurrentPlayer(players.get(currentPlayerID-1));
            return cpu.actionCpu();
        }
        currentPlayerID = (currentPlayerID == players.size()) ? 1 : currentPlayerID+1;
        setCurrentPlayer(players.get(currentPlayerID-1));
        return currentPlayer;
    }

    public boolean isGameAboutToFinish(){
        if (vaticanReportSections.get(2).isPassed()) return true;
        for (Player p : players){
            if (p.getPersonalBoard().getCardSlot().countCards()>=7) return true;
        }
        return false;
    }

    public boolean hasLorenzoWon(){
        if (vaticanReportSections.get(2).isPassed()) return true;
        int counter;
        for (Color c : Color.values()){
            counter=0;
            for (DevelopmentCardDeck developmentCardDeck: getTable().getDevelopmentCardDecks()){
                if(developmentCardDeck.getColor()==c && developmentCardDeck.isEmpty()) counter++;
            }
            if(counter==3) return true;
        }
        return false;
    }
}
