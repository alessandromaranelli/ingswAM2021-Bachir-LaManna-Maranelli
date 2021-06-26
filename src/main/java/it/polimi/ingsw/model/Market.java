package it.polimi.ingsw.model;

import Exceptions.ModelException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Market. It is a matrix of marbles and it has a marble in excess
 */
public class Market implements Serializable {
    private Marble[][] marketTable;
    private Marble marbleInExcess;

    /**
     * Instantiates a new Market.
     */
    public Market() {
        ArrayList<Marble> marbles=buildMarbles();
        marketTable= builtMarketTable(marbles);
        marbleInExcess= marbles.remove(0);

    }

    /**
     * Instantiates a new Market.
     *
     * @param marketTable    the market table
     * @param marbleInExcess the marble in excess
     */
    public Market(Marble[][] marketTable, Marble marbleInExcess) {
        this.marketTable = marketTable;
        this.marbleInExcess = marbleInExcess;
    }

    /**
     * Build marbles array list.
     *
     * @return the array list
     */
    public ArrayList<Marble> buildMarbles(){
        ArrayList<Marble> marbles=new ArrayList<>();
        marbles.add(new YellowMarble());
        marbles.add(new RedMarble());
        marbles.add(new BlueMarble());
        marbles.add(new WhiteMarble());
        marbles.add(new PurpleMarble());
        marbles.add(new PurpleMarble());
        marbles.add(new WhiteMarble());
        marbles.add(new WhiteMarble());
        marbles.add(new WhiteMarble());
        marbles.add(new BlueMarble());
        marbles.add(new GreyMarble());
        marbles.add(new YellowMarble());
        marbles.add(new GreyMarble());
        Collections.shuffle(marbles);
        return marbles;
    }

    /**
     * Built market table.
     *
     * @param marbles the marbles
     * @return the market table
     */
    public Marble[][] builtMarketTable(ArrayList<Marble> marbles){
        Marble[][] marketTable= new Marble[3][4];
        for (int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                marketTable[i][j]=marbles.remove(0);
            }
        }
        return marketTable;
    }

    /**
     * Get market table.
     *
     * @return the market table.
     */
    public Marble[][] getMarketTable() {
        return marketTable;
    }

    /**
     * Get marble in excess.
     *
     * @return the marble in excess
     */
    public Marble getMarbleInExcess() {
        return marbleInExcess;
    }

    /**
     * Choose a row.
     *
     * @param row the row
     * @return the marbles in the row
     * @throws ModelException if the row doesn't exists
     */
    public ArrayList<Marble> chooseRow(int row) throws ModelException {
        if(row>2||row<0) throw new ModelException("Wrong dimension");
        ArrayList<Marble> marbles= new ArrayList<>();
        for (int i=0;i<4;i++){
            marbles.add(marketTable[row][i]);
        }
        return marbles;
    }

    /**
     * Choose a columns.
     *
     * @param column the column
     * @return the marbles in the columns
     * @throws ModelException if the column doesn't exists
     */
    public ArrayList<Marble> chooseColumn(int column) throws ModelException {
        ArrayList<Marble> marbles= new ArrayList<>();
        if(column>3||column<0) throw new ModelException("Wrong dimension");
        for (int i=0;i<3;i++){
            marbles.add(marketTable[i][column]);
        }
        return marbles;

    }

    /**
     * Reorganize the market table after a row or a column is chosen. The marble in excess is added to that row or
     * column and the last marble in the row or column becomes the new marble in excess
     *
     * @param resources the marbles in the row or column
     * @param dim       the number of the row or column
     */
    public void reorganize(ArrayList<Marble> resources, int dim){
        Marble inExcess=this.getMarbleInExcess();
        if (resources.size()==3){
            marbleInExcess=marketTable[0][dim];
            for (int i=0;i<2;i++){
                marketTable[i][dim]=marketTable[i+1][dim];
            }
            marketTable[2][dim]=inExcess;
        }
        else{
            marbleInExcess=marketTable[dim][0];
            for (int i=0;i<3;i++){
                marketTable[dim][i]=marketTable[dim][i+1];
            }
            marketTable[dim][3]=inExcess;
        }
    }
}