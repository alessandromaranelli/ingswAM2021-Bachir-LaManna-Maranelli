package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WareHousePanel extends JPanel {
    List<JLabel> storageLabels;
    JLabel coinChest;
    JLabel servantChest;
    JLabel shieldChest;
    JLabel stoneChest;

    public WareHousePanel(LightModel lightModel){
        setBackground(Color.ORANGE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        storageLabels = new ArrayList<>();
        for(int i=0; i < lightModel.getStorageType().size(); i++){
            storageLabels.add(new JLabel());
        }
        coinChest = new JLabel();
        servantChest = new JLabel();
        shieldChest = new JLabel();
        stoneChest = new JLabel();

        for(int i=1; i <= storageLabels.size(); i++){
            storageLabels.get(i-1).setText("Storage " + i + " : " + lightModel.getStorageQuantity().get(i-1) + " " + lightModel.getStorageType().get(i-1).toString());
            storageLabels.get(i-1).setFont(new Font("Comic Sans", Font.PLAIN, 25));
        }
        coinChest.setText("Chest coins: " + lightModel.getChest().get(Resource.COIN));
        coinChest.setFont(new Font("Comic Sans", Font.PLAIN, 25));

        servantChest.setText("Chest servants: " + lightModel.getChest().get(Resource.SERVANT));
        servantChest.setFont(new Font("Comic Sans", Font.PLAIN, 25));

        shieldChest.setText("Chest shields: " + lightModel.getChest().get(Resource.SHIELD));
        shieldChest.setFont(new Font("Comic Sans", Font.PLAIN, 25));

        stoneChest.setText("Chest stones: " + lightModel.getChest().get(Resource.STONE));
        stoneChest.setFont(new Font("Comic Sans", Font.PLAIN, 25));

        for(int i=0; i < storageLabels.size(); i++){
            add(storageLabels.get(i));
        }
        add(coinChest);
        add(servantChest);
        add(shieldChest);
        add(stoneChest);
        setVisible(true);
    }

}
