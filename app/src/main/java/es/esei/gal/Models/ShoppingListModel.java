package es.esei.gal.Models;

import java.util.ArrayList;

public class ShoppingListModel {
    String listName;
    ArrayList<ItemModel> itemList;
    Boolean IsDone;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ArrayList<ItemModel> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<ItemModel> itemList) {
        this.itemList = itemList;
    }

    public Boolean getDone() {
        return IsDone;
    }

    public void setDone(Boolean done) {
        IsDone = done;
    }

    public ShoppingListModel(String listName, ArrayList<ItemModel> itemList, Boolean isDone) {
        this.listName = listName;
        this.itemList = itemList;
        IsDone = isDone;
    }
}
