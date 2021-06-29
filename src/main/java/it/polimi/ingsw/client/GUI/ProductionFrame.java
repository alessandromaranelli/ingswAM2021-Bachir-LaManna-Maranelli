package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.LightModel;
import it.polimi.ingsw.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Production frame. It displays the total cost and the total gains of the productions
 */
public class ProductionFrame extends JFrame {
    /**
     * The Light model.
     */
    LightModel lightModel;
    private JPanel panel;
    private JPanel productionInput;
    private JPanel productionOutput;
    private JLabel input;
    private JLabel output;
    private List<ResourceLabel> totalCost;
    private List<ResourceLabel> totalGains;

    /**
     * Instantiates a new Production frame.
     *
     * @param lightModel the light model
     */
    public ProductionFrame(LightModel lightModel){
        this.lightModel = lightModel;
        panel = new JPanel();
        productionInput = new JPanel();
        productionOutput = new JPanel();
        totalCost = new ArrayList<>();
        totalGains = new ArrayList<>();

        input = new JLabel("Total cost");
        input.setHorizontalAlignment(SwingConstants.CENTER);
        input.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        panel.add(input);

        for(Resource r : lightModel.getTotalCost().keySet()){
            ResourceLabel rl = new ResourceLabel(r);
            rl.setQuantity(lightModel.getTotalCost().get(r));
            totalCost.add(rl);
        }
        for(int i=0; i < totalCost.size(); i++){
            productionInput.add(totalCost.get(i));
        }
        panel.add(productionInput);

        output = new JLabel("Total gains");
        output.setHorizontalAlignment(SwingConstants.CENTER);
        output.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        panel.add(output);

        for(Resource r : lightModel.getTotalGain().keySet()){
            ResourceLabel rl = new ResourceLabel(r);
            rl.setQuantity(lightModel.getTotalGain().get(r));
            totalGains.add(rl);
        }
        for(int i=0; i < totalGains.size(); i++){
            productionOutput.add(totalGains.get(i));
        }
        panel.add(productionOutput);

        setContentPane(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setVisible(true);;
        pack();
    }
}
