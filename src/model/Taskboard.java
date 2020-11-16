package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Taskboard {

    private String title;

    private ArrayList<ProductBacklogItem> pbItems = new ArrayList<>();

    public String getTitle() { return title; }

    public void setTitle(String s){
        this.title = s;
    }

    public void addProductBacklogItem(ProductBacklogItem pbi) { pbItems.add(pbi); }

    public ArrayList<ProductBacklogItem> getPbItems() { return pbItems; }

    public ProductBacklogItem getPbItem(int id) {
        for (ProductBacklogItem pbi : pbItems) {
            if (pbi.getId() == id) {
                return pbi;
            }
        }
        return null;
    }

    public void removeProductBacklogItem(int id) {
        int i = 0;
        for (ProductBacklogItem pbi : pbItems) {
            if (pbi.getId() == id) {
                pbItems.remove(i);
                return;
            }
            i++;
        }
    }

}
