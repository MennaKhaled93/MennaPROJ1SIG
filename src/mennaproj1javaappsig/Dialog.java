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
import java.awt.event.ActionListener;

/**
 *
 * @author menism38
 */
public class Dialog extends JDialog {

    private  JTextField CName;
    private  JTextField DateField;
    private  JLabel CustomerName;
    private  JLabel InvoiceDate;
    private  JButton NewRow;
    private  JButton DeleteSelected;   
    
public Dialog(MennaJFrame Invframe) {
        CustomerName = new JLabel("CustomerName");
        CName = new JTextField(35);
        InvoiceDate = new JLabel("InvoiceDate");
        DateField = new JTextField(35);
        NewRow = new JButton("OK");
        DeleteSelected = new JButton("Cancel");
        
        NewRow.setActionCommand("NewRow");
        DeleteSelected.setActionCommand("createInvCancel");
        
        NewRow.addActionListener(Invframe.getInvlistener());
        DeleteSelected.addActionListener(Invframe.getInvlistener());

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
