package model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    // if we decide providing more than 1 taskboard
//     private List<Taskboard> taskBoards = new ArrayList<>();

    private static Taskboard tb = new Taskboard();

    // if we decide providing more than 1 taskboard
//     public void addTaskboard(Taskboard tb) {
//         taskBoards.add(tb);
//     }

    // if we decide providing more than 1 taskboard
//    public List<Taskboard> getTaskBoards() {
//        return taskBoards;
//    }

//    public void setTaskboard(Taskboard tb) {
//        this.tb = tb;
//    }

    public Taskboard getTaskboard() {
        return tb;
    }

}
