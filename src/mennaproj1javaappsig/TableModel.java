/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mennaproj1javaappsig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author menism38
 */
public class TableModel extends AbstractTableModel {

    private  List<Header> Lists;
    private  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
       
    public TableModel(List<Header> Lists) {
        this.Lists = Lists;
    }

    public List<Header> getLists() {
        return Lists;
    }
    
    @Override
    public int getRowCount() {
        return Lists.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
  @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Invoice Num";
            case 1:
                return "Customer Name";
            case 2:
                return "Invoice Date";
            case 3:
                return "Invoice Total";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
   Header row = Lists.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return row.getInvoiceNo();
            case 1:
                return row.getCustomerName();
            case 2:
                return df.format(row.getInvoiceDate());
            case 3:
                return row.getInvTotal();
            default:
                return "";
        }
        
    }
}
