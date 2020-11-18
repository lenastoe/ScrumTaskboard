package model;

import java.util.ArrayList;
import java.util.List;

public class ProductBacklogItem {

    private static int ongoingID = 0;
    private String name;
    private String description;
    private int id;
    private ArrayList<Task> tasks = new ArrayList<>();

    public ProductBacklogItem(String name, String description) {
        id = ongoingID++;
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

    public ArrayList<Task> getTasks() { return tasks; }

    //
    public void addTask(Task t) { tasks.add(t); }

    public void deleteTask(int taskID) {
        int i = 0;
        for(Task t : tasks) {
            if (t.getId() == taskID) {
                tasks.remove(i);
                return;
            }
            i++;
        }
    }

    public void clearTasks(){
        tasks.clear();
    }

    public Task searchTask(int taskID) {
            for (Task newTask : tasks) {
                if (newTask.getId() == taskID) {
                    return newTask;
                }
            }
        return null;
    }


    @Override
    public String toString() {
        String s = "id: " + id + "\n" + name;
        if (!description.equals("")) s += "\n" + "Beschreibung: " + description;
        return s;
    }
}
