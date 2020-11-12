package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskboardController {

    @FXML
    Button createNewTaskButton;

    @FXML
    MenuItem createNewTaskboard;

    @FXML
    VBox backlogField;

    public void createNewTaskButtonPushed(ActionEvent e) throws IOException {
        backlogField.getChildren().add(new TextField("saldkfjlkjsd"));
    }

    public void createNewTaskboardButtonPushed() {
        Stage secondStage = new Stage();
        Scene scene = new Scene(new HBox());
        secondStage.setScene(scene);
        secondStage.show();
    }

    @FXML
    public void initialize() {

    }


}
