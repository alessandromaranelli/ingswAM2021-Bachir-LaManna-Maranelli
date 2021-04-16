package it.polimi.ingsw.model;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class MarketTest {

    @Test
    void getMarketTable() {
        Marble[][] marketTable= new Marble[3][4];
        marketTable[0][0]=new YellowMarble();
        marketTable[0][1]=new RedMarble();
        marketTable[0][2]=new BlueMarble();
        marketTable[0][3]=new WhiteMarble();
        marketTable[1][0]=new PurpleMarble();
        marketTable[1][1]=new PurpleMarble();
        marketTable[1][2]=new WhiteMarble();
        marketTable[1][3]=new WhiteMarble();
        marketTable[2][0]=new WhiteMarble();
        marketTable[2][1]=new BlueMarble();
        marketTable[2][2]=new GreyMarble();
        marketTable[2][3]=new YellowMarble();
        Marble marbleInExcess= new GreyMarble();
        Market market=new Market(marketTable,marbleInExcess);
        assertEquals(marketTable,market.getMarketTable());
    }

    @Test
    void Reorganize1() {
        Marble[][] marketTable= new Marble[3][4];
        marketTable[0][0]=new YellowMarble();
        marketTable[0][1]=new RedMarble();
        marketTable[0][2]=new BlueMarble();
        marketTable[0][3]=new WhiteMarble();
        marketTable[1][0]=new PurpleMarble();
        marketTable[1][1]=new PurpleMarble();
        marketTable[1][2]=new WhiteMarble();
        marketTable[1][3]=new WhiteMarble();
        marketTable[2][0]=new WhiteMarble();
        marketTable[2][1]=new BlueMarble();
        marketTable[2][2]=new GreyMarble();
        marketTable[2][3]=new YellowMarble();
        Marble marbleInExcess= new GreyMarble();
        Marble marbleTest= marbleInExcess;
        Market market=new Market(marketTable,marbleInExcess);
        ArrayList<Marble> marbleArrayList= new ArrayList<>();
        for (int i=0;i<4;i++){
            marbleArrayList.add(marketTable[1][i]);
        }
        market.reorganize(marbleArrayList,1);
        assertEquals(marbleTest,market.getMarketTable()[1][3]);
        assertEquals(marbleArrayList.get(0),market.getMarbleInExcess());
        assertEquals(marbleArrayList.get(1),market.getMarketTable()[1][0]);
        assertEquals(marbleArrayList.get(2),market.getMarketTable()[1][1]);
        assertEquals(marbleArrayList.get(3),market.getMarketTable()[1][2]);

    }

    @Test
    void Reorganize2() {
        Marble[][] marketTable= new Marble[3][4];
        marketTable[0][0]=new YellowMarble();
        marketTable[0][1]=new RedMarble();
        marketTable[0][2]=new BlueMarble();
        marketTable[0][3]=new WhiteMarble();
        marketTable[1][0]=new PurpleMarble();
        marketTable[1][1]=new PurpleMarble();
        marketTable[1][2]=new WhiteMarble();
        marketTable[1][3]=new WhiteMarble();
        marketTable[2][0]=new WhiteMarble();
        marketTable[2][1]=new BlueMarble();
        marketTable[2][2]=new GreyMarble();
        marketTable[2][3]=new YellowMarble();
        Marble marbleInExcess= new GreyMarble();
        Marble marbleTest= marbleInExcess;
        Market market=new Market(marketTable,marbleInExcess);
        ArrayList<Marble> marbleArrayList= new ArrayList<>();
        for (int i=0;i<3;i++){
            marbleArrayList.add(marketTable[i][2]);
        }
        market.reorganize(marbleArrayList,2);
        assertEquals(marbleTest,market.getMarketTable()[2][2]);
        assertEquals(marbleArrayList.get(0),market.getMarbleInExcess());
        assertEquals(marbleArrayList.get(1),market.getMarketTable()[0][2]);
        assertEquals(marbleArrayList.get(2),market.getMarketTable()[1][2]);
    }

    @Test
    void chooseRow() {
        Marble[][] marketTable= new Marble[3][4];
        marketTable[0][0]=new YellowMarble();
        marketTable[0][1]=new RedMarble();
        marketTable[0][2]=new BlueMarble();
        marketTable[0][3]=new WhiteMarble();
        marketTable[1][0]=new PurpleMarble();
        marketTable[1][1]=new PurpleMarble();
        marketTable[1][2]=new WhiteMarble();
        marketTable[1][3]=new WhiteMarble();
        marketTable[2][0]=new WhiteMarble();
        marketTable[2][1]=new BlueMarble();
        marketTable[2][2]=new GreyMarble();
        marketTable[2][3]=new YellowMarble();
        Marble marbleInExcess= new GreyMarble();
        Market market=new Market(marketTable,marbleInExcess);
        ArrayList<Marble> marbleArrayList= new ArrayList<>();
        marbleArrayList=market.chooseRow(1);
        assertEquals(marbleArrayList.get(0),market.getMarketTable()[1][0]);
        assertEquals(marbleArrayList.get(1),market.getMarketTable()[1][1]);
        assertEquals(marbleArrayList.get(2),market.getMarketTable()[1][2]);
        assertEquals(marbleArrayList.get(3),market.getMarketTable()[1][3]);

    }

    @Test
    void chooseColumn() {
        Marble[][] marketTable= new Marble[3][4];
        marketTable[0][0]=new YellowMarble();
        marketTable[0][1]=new RedMarble();
        marketTable[0][2]=new BlueMarble();
        marketTable[0][3]=new WhiteMarble();
        marketTable[1][0]=new PurpleMarble();
        marketTable[1][1]=new PurpleMarble();
        marketTable[1][2]=new WhiteMarble();
        marketTable[1][3]=new WhiteMarble();
        marketTable[2][0]=new WhiteMarble();
        marketTable[2][1]=new BlueMarble();
        marketTable[2][2]=new GreyMarble();
        marketTable[2][3]=new YellowMarble();
        Marble marbleInExcess= new GreyMarble();
        Market market=new Market(marketTable,marbleInExcess);
        ArrayList<Marble> marbleArrayList= new ArrayList<>();
        marbleArrayList=market.chooseColumn(2);
        assertEquals(marbleArrayList.get(0),market.getMarketTable()[0][2]);
        assertEquals(marbleArrayList.get(1),market.getMarketTable()[1][2]);
        assertEquals(marbleArrayList.get(2),market.getMarketTable()[2][2]);
    }

}