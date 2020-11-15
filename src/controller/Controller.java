package controller;

import model.Model;

public class Controller {

    private static Model m;

    public Controller() {
        m = new Model();
    }

    public Model getModel() {
        return m;
    }

}
