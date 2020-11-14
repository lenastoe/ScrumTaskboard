package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.Optional;

public class TaskboardController {

    Taskboard tb;

    @FXML
    Button createNewTaskButton;

    @FXML
    MenuItem createBacklogItemButton;

    @FXML
    VBox backlogField;


    public void createNewTaskButtonPushed(ActionEvent e) throws IOException {
        backlogField.getChildren().add(new TextField("saldkfjlkjsd"));
    }

    // opens a textfield to enter a name for a new backlog item
    public void createBacklogItemButtonPushed(ActionEvent e) throws IOException  {
        // create view
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("CreateBacklogItemScene.fxml"));
        DialogPane createBacklogItemDialogPane = fxmlLoader.load();

        // set view
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(createBacklogItemDialogPane);
        dialog.setTitle(createBacklogItemDialogPane.getHeaderText());

        // disable ok-button
        Node okButton = createBacklogItemDialogPane.lookupButton(ButtonType.OK);
        okButton.setDisable(true);

        // enable ok-button if "bezeichnung"-textfield not empty
        TextField textField = (TextField) createBacklogItemDialogPane.lookup("#bezeichnung");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });

        // what happens if ok button pressed
        Optional<ButtonType> clickedButton = dialog.showAndWait();

        clickedButton.ifPresent(buttonType -> {
            if(buttonType == ButtonType.OK) ;//dua wos

        });
        if (clickedButton.get() == ButtonType.OK) {

        }

    }

    @FXML
    public void initialize() {

    }


}
