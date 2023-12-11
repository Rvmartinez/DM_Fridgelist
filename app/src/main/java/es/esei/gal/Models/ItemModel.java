package es.esei.gal.Models;

public class ItemModel {
    String itemName;
    Integer itemId;
    Boolean isWeighed;
    Double price;


    public Boolean getWeighed() {
        return isWeighed;
    }

    public void setWeighed(Boolean weighed) {
        isWeighed = weighed;
    }


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

    public ItemModel(String itemName, Integer itemId, Double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.price = price;
    }
}
