package model;

public class ProductBacklogItem {

    private static int ongoingID = 0;
    private String name;
    private String description;
    private int id;

    public ProductBacklogItem(String name, String description) {
        id = ongoingID;
        ongoingID++;
        this.name = name;
        this.description = description;
    }

    //getter
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    //setter
    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String s = "id: " + id + "\n" + name;
        if (!description.equals("")) s += "\n" + "beschreibung: " + description;
        return s;
    }
}
