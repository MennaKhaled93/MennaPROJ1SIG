/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mennaproj1javaappsig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author menism38
 */
public class Header {
    private  int InvoiceNo;
    private  String CustomerName;
    private  Date InvoiceDate;
    private ArrayList<InvoiceRow> Rows;  

public Header(int InvoiceNo, String CustomerName, Date InvoiceDate) {
        this.InvoiceNo = InvoiceNo;
        this.CustomerName = CustomerName;
        this.InvoiceDate = InvoiceDate;
    }
public Date getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(Date InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public int getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(int InvoiceNo) {
        this.InvoiceNo = InvoiceNo;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

 @Override
    public String toString() {
        String str = "InvoiceHeader{" + "invNum=" + InvoiceNo + ", customerName=" + CustomerName + ", invDate=" + InvoiceDate + '}';
        for (InvoiceRow line : getRows()) {
            str += "\n\t" + line;
        }
        return str;
    }

    public ArrayList<InvoiceRow> getRows() {
        if (Rows == null)
            Rows = new ArrayList<>(); 
        return Rows;
    }

    public void setRows(ArrayList<InvoiceRow> Rows) {
        this.Rows = Rows;
    }

    public double getInvTotal() {
        double total = 0.0;
        for (InvoiceRow line : getRows()) {
            total += line.getLineTotal();
        }
        return total;
    }
    
    public void addInvLine(InvoiceRow Row) {
        getRows().add(Row);
    }
    
    public String getDataAsCSV() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getInvoiceNo() + "," + df.format(getInvoiceDate()) + "," + getCustomerName();
    }
    
}
