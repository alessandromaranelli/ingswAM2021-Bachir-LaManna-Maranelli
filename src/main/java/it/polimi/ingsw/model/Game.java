package it.polimi.ingsw.model;

import java.io.FileNotFoundException;
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

    public void createCPU(){
        if (players.size()==1){
            cpu= new CPU(this);
            soloMatch=true;
        }
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

    public void nextPlayer() {
        if (soloMatch){
            cpu.actionCpu();
        }
        currentPlayerID = (currentPlayerID == players.size()) ? 1 : currentPlayerID+1;
        setCurrentPlayer(players.get(currentPlayerID-1));
    }
}
