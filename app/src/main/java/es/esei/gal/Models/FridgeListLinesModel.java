package es.esei.gal.Models;

public class FridgeListLinesModel {
    int itemId,qty;
    String itemName;
    Double price;
    Boolean isWeighted;

    public FridgeListLinesModel(int itemId, String itemName, Double price,boolean isWeighted,int qty) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.isWeighted = isWeighted;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Boolean getWeighted() {
        return isWeighted;
    }

    public void setWeighted(Boolean weighted) {
        isWeighted = weighted;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
