package es.esei.gal.Models;

import java.util.ArrayList;

public class FridgeListModel {
    String listName;
    ArrayList<ItemModel> fridgeList;
    Boolean IsDone;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ArrayList<ItemModel> getFridgeList() {
        return fridgeList;
    }

    public void setFridgeList(ArrayList<ItemModel> fridgeList) {
        this.fridgeList = fridgeList;
    }

    public Boolean getDone() {
        return IsDone;
    }

    public void setDone(Boolean done) {
        IsDone = done;
    }

    public FridgeListModel(String listName, ArrayList<ItemModel> fridgeList, Boolean isDone) {
        this.listName = listName;
        this.fridgeList = fridgeList;
        IsDone = isDone;
    }
}
