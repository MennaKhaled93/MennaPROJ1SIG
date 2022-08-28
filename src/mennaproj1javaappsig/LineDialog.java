/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mennaproj1javaappsig;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author menism38
 */
public class LineDialog extends JDialog{

    private  JTextField NameText;
    private  JTextField CountText;
    private  JTextField PriceText;
    private  JLabel NameLabel;
    private  JLabel Count;
    private  JLabel Price;
    private  JButton NewRow;
    private  JButton DeleteSelected;
 
public LineDialog(MennaJFrame Invframe) {
        NameText = new JTextField(35);
        NameLabel = new JLabel("NameLabel");
        
        CountText = new JTextField(35);
        Count = new JLabel("Count");
        
        PriceText = new JTextField(35);
        Price = new JLabel("Price");
        
        NewRow = new JButton("OK");
        DeleteSelected = new JButton("Cancel");
        
        NewRow.setActionCommand("createLineOK");
        DeleteSelected.setActionCommand("DeleteSelected");
        
        NewRow.addActionListener(Invframe.getInvlistener());
        DeleteSelected.addActionListener(Invframe.getInvlistener());
        setLayout(new GridLayout(5, 3));
        
        add(NameLabel);
        add(NameText);
        add(Count);
        add(CountText);
        add(Price);
        add(PriceText);
        add(NewRow);
        add(DeleteSelected);
        
        pack();
    }

     public JTextField getNameText() {
        return NameText;
    }

    public JTextField getCountText() {
        return CountText;
    }

    public JTextField getPriceText() {
        return PriceText;
    }   
    
    
}
