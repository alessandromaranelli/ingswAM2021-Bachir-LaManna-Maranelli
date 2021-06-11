package it.polimi.ingsw.client.GUI;
import it.polimi.ingsw.client.LightModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FaithTrackPanel extends JPanel{
    private List<JLabel> jLabelList;

    public FaithTrackPanel(LightModel lightModel){
        setBackground(Color.RED);
        setLayout(new FlowLayout());
        //setPreferredSize(new Dimension(1000, 100));

        jLabelList = new ArrayList<>();
        for(int i=0; i < 25; i++){
            jLabelList.add(new JLabel());
            jLabelList.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            jLabelList.get(i).setForeground(Color.BLACK);
            if(i == lightModel.getPosition()){
                jLabelList.get(i).setText("X");
            }
            else{
                jLabelList.get(i).setText("" + i);
            }

            add(jLabelList.get(i));
        }

        setVisible(true);
    }
}
