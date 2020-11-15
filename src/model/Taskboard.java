package model;

import java.util.ArrayList;
import java.util.List;

public class Taskboard {

    private String title;

    private List<Task> tasks = new ArrayList<>();
    private List<ProductBacklogItem> pbItems = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void addProductBacklogItem(ProductBacklogItem pbi) { pbItems.add(pbi); }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<ProductBacklogItem> getPbItems() { return pbItems; }

}
