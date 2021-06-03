package it.polimi.ingsw;

import it.polimi.ingsw.client.GUI.MarketFrame;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        JFrame jFrame=new JFrame();
        JLabel jLabel=new JLabel();
        jLabel.setText("Jimmy");
        jFrame.add(jLabel);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}
