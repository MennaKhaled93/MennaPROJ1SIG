/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mennaproj1javaappsig;

/**
 *
 * @author menism38
 */
public class InvoiceRow {
 private String itemName;
    private double itemPrice;
    private int itemCount;
    private Header header;  
    
     public InvoiceRow(String itemName, double itemPrice, int itemCount, Header header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
    }
       
public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "itemName=" + itemName + ", itemprice=" + itemPrice + ", itemCount=" + itemCount + '}';
    }
    
    public double getLineTotal() {
        return itemCount * itemPrice;
    }
    
    public String getDataAsCSV() {
        return "" + getHeader().getInvoiceNo() + "," + getItemName() + "," + getItemPrice() + "," + getItemCount();
    }
}
