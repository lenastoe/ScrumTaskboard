package model;

public enum Priority {

    NOTSET ("keine"), HIGH ("hoch"), MEDIUM ("mittel"), LOW ("niedrig");

    private final String german;

    Priority(String german) {
        this.german = german;
    }

    public String getGerman() {
        return german;
    }

    public static Priority getPriority(String germanName) {
        Priority priority = Priority.NOTSET;
        for (Priority p : values()) {
            if (p.german.equals(germanName)) priority = p;
        }
        return priority;
    }

}
