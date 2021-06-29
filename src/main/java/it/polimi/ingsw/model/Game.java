package it.polimi.ingsw.model;

import it.polimi.ingsw.server.Controller;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game. It contains the player, it knows the current player, it knows if the game is for only one player
 * and it contains the Table
 */
public class Game implements Serializable{
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int currentPlayerID;
    private CPU cpu;
    private boolean soloMatch = false;
    private Table table;
    private ArrayList<VaticanReportSection> vaticanReportSections= new ArrayList<>();

    /**
     * Instantiates a new Game.
     *
     * @throws FileNotFoundException file not found exception
     */
    public Game() throws FileNotFoundException {
        this.table = new Table();
        VaticanReportSection vaticanReportSection= new VaticanReportSection(5,1,players);
        vaticanReportSections.add(vaticanReportSection);
        vaticanReportSection= new VaticanReportSection(12,2,players);
        vaticanReportSections.add(vaticanReportSection);
        vaticanReportSection= new VaticanReportSection(19,3,players);
        vaticanReportSections.add(vaticanReportSection);
    }

    /**
     * Start game setting the current player.
     */
    public void start(){
        if (players.size()==1){
            cpu= new CPU(this);
            soloMatch=true;
        }
        setCurrentPlayer(players.get(0));
    }

    /**
     * Is solo match boolean.
     *
     * @return the boolean
     */
    public boolean isSoloMatch() {
        return soloMatch;
    }

    /**
     * Gets vatican report sections.
     *
     * @return the vatican report sections
     */
    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    /**
     * Create a new player.
     *
     * @param player the player
     */
    public void createNewPlayer(Player player) {
        players.add(player);
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets cpu.
     *
     * @return the cpu
     */
    public CPU getCpu() {
        return cpu;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Get player by id player.
     *
     * @param Id the id player
     * @return the player
     */
    public Player getPlayerById(int Id){
        return players.get(Id-1);
    }

    /**
     * Sets current player.
     *
     * @param player the player
     */
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
        this.currentPlayerID = player.getPlayerID();
        currentPlayer.setAsCurrentPlayer();
    }

    /**
     * Gets current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get next Player and set the new current player.
     *
     * @return the player
     */
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

    /**
     * Is game about to finish boolean. After this, all the players will do only one turn
     *
     * @return the boolean
     */
    public boolean isGameAboutToFinish(){
        if (vaticanReportSections.get(2).isPassed()) return true;
        for (Player p : players){
            if (p.getPersonalBoard().getCardSlot().countCards()>=7) return true;
        }
        return false;
    }

    /**
     * Has lorenzo won boolean.
     *
     * @return the boolean
     */
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
