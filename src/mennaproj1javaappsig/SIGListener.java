/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mennaproj1javaappsig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class SIGListener implements ActionListener,ListSelectionListener {

    private MennaJFrame Invframe;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    
    SIGListener(MennaJFrame Invframe) {
        this.Invframe = Invframe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
            case "Create New Invoice":
                displayInvDialog();
                break;
            case "Delete Invoice":
                DeleteInv();
                break;
            case "New Row":
                NewLine();
                break;
            case "Delete Selected Invoice":
                RemoveRow();
                break;
            case "Load File":
                loadFile();
                break;
            case "Save File":
                SaveFile();
                break;
            case "createInvCancel":
                DeleteLine();
                break;
            case "NewRow":
                NewInvoice();
                break;
            case "DeleteSelected":
                LineDelete();
                break;
            case "createLineOK":
                createLine();
                break;
        }    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    System.out.println("Invoice has beed Selected");
        RowSelected();   
    }
    
        private void RowSelected() 
    {
      int selectedRowIndex = Invframe.getInvTable().getSelectedRow();
        if (selectedRowIndex >= 0) {
            Header row = Invframe.getSIGTableModel().getLists().get(selectedRowIndex);
            Invframe.getCustName().setText(row.getCustomerName());
            Invframe.getDate().setText(df.format(row.getInvoiceDate()));
            Invframe.getInvNum().setText("" + row.getInvoiceNo());
            Invframe.getTotal().setText("" + row.getInvTotal());
            ArrayList<InvoiceRow> lines = row.getRows();
            Invframe.setInvoiceLines(new InvoiceRows(lines));
            Invframe.getInvRows().setModel(Invframe.getSIGInvoiceRows());
            Invframe.getSIGInvoiceRows().fireTableDataChanged();
        }
    }


    private void displayInvDialog() {
      Invframe.setHDialog(new Dialog(Invframe));
        Invframe.getHDialog().setVisible(true);    }

    private void DeleteInv() {
       int invIndex = Invframe.getInvTable().getSelectedRow();
        Header header = Invframe.getSIGTableModel().getLists().get(invIndex);
        Invframe.getSIGTableModel().getLists().remove(invIndex);
        Invframe.getSIGTableModel().fireTableDataChanged();
        Invframe.setInvoiceLines(new InvoiceRows(new ArrayList<InvoiceRow>()));
        Invframe.getInvRows().setModel(Invframe.getSIGInvoiceRows());
        Invframe.getSIGInvoiceRows().fireTableDataChanged();
        Invframe.getCustName().setText("");
        Invframe.getDate().setText("");
        Invframe.getInvNum().setText("");
        Invframe.getTotal().setText("");
        DisplayInv();    }

    private void NewLine() {
    Invframe.setLine(new LineDialog(Invframe));
    Invframe.getLine().setVisible(true);    }

    private void RemoveRow() 
    {
        int lineIndex = Invframe.getInvRows().getSelectedRow();
        InvoiceRow line = Invframe.getSIGInvoiceRows().getRows().get(lineIndex);
        Invframe.getSIGInvoiceRows().getRows().remove(lineIndex);
        Invframe.getSIGInvoiceRows().fireTableDataChanged();
        Invframe.getSIGTableModel().fireTableDataChanged();
        Invframe.getTotal().setText("" + line.getHeader().getInvTotal());
        DisplayInv();   
    }

    private void loadFile() {
       JOptionPane.showMessageDialog(Invframe, "Select Header file", "Attension!", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser openFile = new JFileChooser();
        int result = openFile.showOpenDialog(Invframe);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = openFile.getSelectedFile();
            try {
                FileReader headerFr = new FileReader(headerFile);
                BufferedReader headerBr = new BufferedReader(headerFr);
                String headerLine = null;

                while ((headerLine = headerBr.readLine()) != null) {
                    String[] headerParts = headerLine.split(",");
                    String invNumStr = headerParts[0];
                    String invDateStr = headerParts[1];
                    String custName = headerParts[2];

                    int invNum = Integer.parseInt(invNumStr);
                    Date invDate = df.parse(invDateStr);

                    Header inv = new Header(invNum, custName, invDate);
                    Invframe.getLists().add(inv);
                }

                JOptionPane.showMessageDialog(Invframe, "Please Select lines file", "Attension", JOptionPane.INFORMATION_MESSAGE);
                result = openFile.showOpenDialog(Invframe);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = openFile.getSelectedFile();
                    BufferedReader linesBr = new BufferedReader(new FileReader(linesFile));
                    String linesLine = null;
                    while ((linesLine = linesBr.readLine()) != null) {
                        String[] lineParts = linesLine.split(",");
                        String invNumStr = lineParts[0];
                        String itemName = lineParts[1];
                        String itemPriceStr = lineParts[2];
                        String itemCountStr = lineParts[3];

                        int invNum = Integer.parseInt(invNumStr);
                        double itemPrice = Double.parseDouble(itemPriceStr);
                        int itemCount = Integer.parseInt(itemCountStr);
                        Header header = findInvByNum(invNum);
                        InvoiceRow invLine = new InvoiceRow(itemName, itemPrice, itemCount, header);
                        header.getRows().add(invLine);
                    }
                    Invframe.setTableModel(new TableModel(Invframe.getSIGList()));
                    Invframe.getInvTable().setModel(Invframe.getSIGTableModel());
                    Invframe.getInvTable().validate();
                }
                System.out.println("Check");
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Invframe, "Please enter correct Date Format!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Invframe, "Please enter correct Number Format !\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Invframe, "File Error!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(Invframe, "Read Error!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        DisplayInv();    }

        private Header findInvByNum(int InvNum) {
        Header header = null;
        for (Header inv : Invframe.getLists()) {
            if (InvNum == inv.getInvoiceNo()) {
                header = inv;
                break;
            }
        }
        return header;
    }
    private void SaveFile() {
    String headers = "";
        String lines = "";
        for (Header header : Invframe.getLists()) {
            headers += header.getDataAsCSV();
            headers += "\n";
            for (InvoiceRow line : header.getRows()) {
                lines += line.getDataAsCSV();
                lines += "\n";
            }
        }
        JOptionPane.showMessageDialog(Invframe, "Select the file to save header data in!", "Attension", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(Invframe);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fileChooser.getSelectedFile();
            try {
                FileWriter hFW = new FileWriter(headerFile);
                hFW.write(headers);
                hFW.flush();
                hFW.close();

                JOptionPane.showMessageDialog(Invframe, "Select the file to save lines data!", "Attension", JOptionPane.WARNING_MESSAGE);
                result = fileChooser.showSaveDialog(Invframe);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fileChooser.getSelectedFile();
                    FileWriter lFW = new FileWriter(linesFile);
                    lFW.write(lines);
                    lFW.flush();
                    lFW.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Invframe, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(Invframe, "Data saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void DeleteLine() {
        Invframe.getHDialog().setVisible(false);
        Invframe.getHDialog().dispose();
        Invframe.setHDialog(null);    }

    private void NewInvoice() {
       String custName = Invframe.getHDialog().getCName().getText();
        String invDateStr = Invframe.getHDialog().getDateField().getText();
        Invframe.getHDialog().setVisible(false);
        Invframe.getHDialog().dispose();
        Invframe.setHDialog(null);
        try {
            Date invDate = df.parse(invDateStr);
            int invNum = getSeconcInvNumber();
            Header invoiceHeader = new Header(invNum, custName, invDate);
            Invframe.getLists().add(invoiceHeader);
            Invframe.getSIGTableModel().fireTableDataChanged();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(Invframe, "Please enter correct date format!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        DisplayInv();    
    }
   private int getSeconcInvNumber() {
        int max = 0;
        for (Header header : Invframe.getLists()) {
            if (header.getInvoiceNo() > max) {
                max = header.getInvoiceNo();
            }
        }
        return max + 1;
    }
   
    private void LineDelete() {
        Invframe.getLine().setVisible(false);
        Invframe.getLine().dispose();
        Invframe.setLine(null);    
    }

    private void createLine() {
       String itemName = Invframe.getLine().getNameText().getText();
        String itemCountStr = Invframe.getLine().getCountText().getText();
        String itemPriceStr = Invframe.getLine().getPriceText().getText();
        Invframe.getLine().setVisible(false);
        Invframe.getLine().dispose();
        Invframe.setLine(null);
        int itemCount = Integer.parseInt(itemCountStr);
        double itemPrice = Double.parseDouble(itemPriceStr);
        int headerIndex = Invframe.getInvTable().getSelectedRow();
        Header invoice = Invframe.getSIGTableModel().getLists().get(headerIndex);

        InvoiceRow invoiceLine = new InvoiceRow(itemName, itemPrice, itemCount, invoice);
        invoice.addInvLine(invoiceLine);
        Invframe.getSIGInvoiceRows().fireTableDataChanged();
        Invframe.getSIGTableModel().fireTableDataChanged();
        Invframe.getTotal().setText("" + invoice.getInvTotal());
        DisplayInv();
    }


    private void DisplayInv() 
    
    {
        System.out.println("-----------------------");
        for (Header header : Invframe.getSIGList()) {
            System.out.println(header);
        }
        System.out.println("-----------------------");
    }

}
