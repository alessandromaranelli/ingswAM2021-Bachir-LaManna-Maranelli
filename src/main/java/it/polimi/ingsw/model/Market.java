package it.polimi.ingsw.model;

import Exceptions.ModelException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Market implements Serializable {
    private Marble[][] marketTable;
    private Marble marbleInExcess;

    public Market() {
        ArrayList<Marble> marbles=buildMarbles();
        marketTable= builtMarketTable(marbles);
        marbleInExcess= marbles.remove(0);

    }

    public Market(Marble[][] marketTable, Marble marbleInExcess) {
        this.marketTable = marketTable;
        this.marbleInExcess = marbleInExcess;
    }

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

    public Marble[][] builtMarketTable(ArrayList<Marble> marbles){
        Marble[][] marketTable= new Marble[3][4];
        for (int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                marketTable[i][j]=marbles.remove(0);
            }
        }
        return marketTable;
    }

    public Marble[][] getMarketTable() {
        return marketTable;
    }

    public Marble getMarbleInExcess() {
        return marbleInExcess;
    }

    public ArrayList<Marble> chooseRow(int row) throws ModelException {
        if(row>3||row<0) throw new ModelException("Wrong dimension");
        ArrayList<Marble> marbles= new ArrayList<>();
        for (int i=0;i<4;i++){
            marbles.add(marketTable[row][i]);
        }
        return marbles;
    }

    public ArrayList<Marble> chooseColumn(int column) throws ModelException {
        ArrayList<Marble> marbles= new ArrayList<>();
        if(column>2||column<0) throw new ModelException("Wrong dimension");
        for (int i=0;i<3;i++){
            marbles.add(marketTable[i][column]);
        }
        return marbles;

    }

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