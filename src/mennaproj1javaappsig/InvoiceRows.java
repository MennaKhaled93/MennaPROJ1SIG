/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mennaproj1javaappsig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author menism38
 */
public class InvoiceRows extends AbstractTableModel{

    private final List<InvoiceRow> Rows;
    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    
    public InvoiceRows(List<InvoiceRow> Rows) {
        this.Rows = Rows;
    }

    public List<InvoiceRow> getRows() {
        return Rows;
    }
    

    @Override
    public int getRowCount() {
        return Rows.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Item Name";
            case 1:
                return "Item Price";
            case 2:
                return "Count";
            case 3:
                return "Line Total";
            default:
                return "";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Double.class;
            case 2:
                return Integer.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    InvoiceRow row = Rows.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return row.getItemName();
            case 1:
                return row.getItemPrice();
            case 2:
                return row.getItemCount();
            case 3:
                return row.getLineTotal();
            default:
                return "";
            }
}


        
}
