package model;

public class ProductBacklogItem {

    private static int id = 0;
    private String name;
    private String description;

    public ProductBacklogItem(String name) {
        this.id++;
        this.name=name;
        this.description="";
    }

    //getter
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public static int getId() {
        return id;
    }

    //setter
    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
