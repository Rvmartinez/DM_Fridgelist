package es.esei.gal.Models;

public class MasterListModel {
    Integer id;
    String name;
    String category;

    public MasterListModel(Integer id, String name,String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
