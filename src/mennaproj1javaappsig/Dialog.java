/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mennaproj1javaappsig;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author menism38
 */
public class Dialog extends JDialog {

    private final JTextField CName;
    private final JTextField DateField;
    private final JLabel CustomerName;
    private final JLabel InvoiceDate;
    private final JButton NewRow;
    private final JButton DeleteSelected;   
    
public Dialog(MennaJFrame Invframe) {
        CustomerName = new JLabel("CustomerName");
        CName = new JTextField(35);
        InvoiceDate = new JLabel("InvoiceDate");
        DateField = new JTextField(35);
        NewRow = new JButton("NewRow");
        DeleteSelected = new JButton("DeleteSelected");
        
        NewRow.setActionCommand("createInvNewRow");
        DeleteSelected.setActionCommand("createInvDeleteSelected");
        
        NewRow.addActionListener((ActionListener) Invframe.getInvlistener());
        DeleteSelected.addActionListener((ActionListener) Invframe.getInvlistener());
        setLayout(new GridLayout(5, 7));
        
        add(InvoiceDate);
        add(DateField);
        add(CustomerName);
        add(CName);
        add(NewRow);
        add(DeleteSelected);
        
        pack();
        
    }   

    public JTextField getCName() {
        return CName;
    }

    public JTextField getDateField() {
        return DateField;
    }
    
}
