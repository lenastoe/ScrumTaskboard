package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private List<entry> entries;
    private static int countId = 0;
    private int id;
    private String name;
    private double expEffort = 0.0;
    private Priority priority;
    private Status status;

    public Task(String name, Priority p) {
        this.countId++;
        this.id = countId;
        this.name=name;
        entries= new ArrayList<entry>();
        status = Status.offen;
        priority = p;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getExpEffort() {
        return expEffort;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() { return priority; }

    //setter
    public void setName(String name) {
        this.name = name;
    }

    public void setExpEffort(double expEffort) {
        this.expEffort = expEffort;
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }

    public void setStatus(Status s){
        this.status = s;
    }

    //list-functions (add, edit)

    public List<entry> getList() {
        return entries;
    }

    //TO-DO: von der GUI Eingabe einlesen und Entry erstellen, diesen dann zur Liste adden
    public void add(entry e) {
        entries.add(e);
    }

    //inner class entry (table entries)
    public class entry{
        private LocalDate date;
        private String editorName;
        private double workTime;
        private double cumEffort;
        private double restEffort;

        public entry(LocalDate date, String editorName, double cumEffort) {
            if (date == null) {
                this.date=LocalDate.now();
            } else {
                this.date=date;
            }
            this.editorName=editorName;
            this.cumEffort=cumEffort;
        }

        //getter
        public double getCumEffort() {
            return cumEffort;
        }

        public double getRestEffort() {
            return restEffort;
        }

        public double getWorkTime() {
            return workTime;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getEditorName() {
            return editorName;
        }

        //setter
        public void setCumEffort(double cumEffort) {
            this.cumEffort = cumEffort;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public void setEditorName(String editorName) {
            this.editorName = editorName;
        }

        public void setRestEffort(double restEffort) {
            this.restEffort = restEffort;
        }

        public void setWorkTime(double workTime) {
            this.workTime = workTime;
        }
    }
}
