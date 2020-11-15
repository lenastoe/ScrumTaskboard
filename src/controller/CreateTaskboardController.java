package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Taskboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateTaskboardController extends Controller {

    @FXML
    public TextField title;

    @FXML
    ChoiceBox departments;

    ObservableList<Object> dep = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dep.add("Marketing");
        dep.add("Einkauf");
        dep.add("Medizintechnik");
        departments.setItems(dep);
    }
}
