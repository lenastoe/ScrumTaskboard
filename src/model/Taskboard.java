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

    public List<Task> getTasks() {
        return tasks;
    }

}
