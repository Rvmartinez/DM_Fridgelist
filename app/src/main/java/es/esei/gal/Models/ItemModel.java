package es.esei.gal.Models;

public class ItemModel {
    String itemName;
    Integer itemId;
    Double price;



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ItemModel( Integer itemId,String itemName, Double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.price = price;
    }
}
