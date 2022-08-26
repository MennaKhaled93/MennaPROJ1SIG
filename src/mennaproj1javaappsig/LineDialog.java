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

    private final JTextField NameText;
    private final JTextField CountText;
    private final JTextField PriceText;
    private final JLabel NameLabel;
    private final JLabel Count;
    private final JLabel Price;
    private final JButton NewRow;
    private final JButton DeleteSelected;
 
public LineDialog(MennaJFrame Invframe) {
        NameText = new JTextField(35);
        NameLabel = new JLabel("NameLabel");
        
        CountText = new JTextField(35);
        Count = new JLabel("Count");
        
        PriceText = new JTextField(35);
        Price = new JLabel("Price");
        
        NewRow = new JButton("NewRow");
        DeleteSelected = new JButton("DeleteSelected");
        
        NewRow.setActionCommand("NewRow");
        DeleteSelected.setActionCommand("DeleteSelected");
        
        NewRow.addActionListener(Invframe.getInvListener());
        DeleteSelected.addActionListener(Invframe.getInvListener());
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
