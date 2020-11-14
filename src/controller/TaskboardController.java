package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class TaskboardController {

    @FXML
    Button createNewTaskButton;

    @FXML
    MenuItem createBacklogItemButton;

    @FXML
    VBox backlogField;

    @FXML
    VBox open;

    @FXML
    VBox active;

    @FXML
    VBox done;


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

    // opens a dialog to create a new Task
    public void createTaskButtonPushed(ActionEvent e) throws IOException  {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Neuen Task erstellen");

        // Set the button types.
//        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType okButtonType = ButtonType.OK;
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);


        // Create labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Bezeichnung");
        TextField expEffort = new TextField();
        expEffort.setPromptText("Geschätzer Aufwand");

        grid.add(new Label("Bezeichnung:"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Geschätzer Aufwand:"), 0, 1);
        grid.add(expEffort, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // disable ok-button
//        Node okButton = dialog.getDialogPane().lookupButton(ButtonType.OK);
//        okButton.setDisable(true);

        // Enable/Disable ok button depending on whether a title was entered.
//        TextField textField = (TextField) dialog.getDialogPane().lookup("#bezeichnung");
//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//            okButton.setDisable(newValue.trim().isEmpty());
//        });


        // what happens if ok button pressed
        Optional<ButtonType> clickedButton = dialog.showAndWait();

        clickedButton.ifPresent(buttonType -> {
            if(buttonType == ButtonType.OK){
                Task task = new Task(title.getText(), Priority.low);
                Taskboard.tasks.add(task);
                drawAllTasks();
            }
            if(buttonType == ButtonType.CANCEL){
                dialog.close();
            }
        });
    }

    //draw Tasks
    public void drawAllTasks(){
        open.getChildren().clear();
        active.getChildren().clear();
        done.getChildren().clear();
        for(Task task : Taskboard.tasks){
            drawTask(task);
        }
    }

    //show new Task
    public void drawTask(Task task){
        Label id = new Label(Integer.toString(task.getId()));
        Label title = new Label(task.getName());
        id.setStyle("-fx-label-padding: 5px");
        title.setStyle("-fx-label-padding: 5px");
        HBox box = new HBox();

        box.setStyle("-fx-background-color: white");
        box.getChildren().add(id);
        box.getChildren().add(title);
        VBox.setMargin(box,new Insets(0,0,20,20));

        if(task.getStatus() == Status.open){
            open.getChildren().add(box);
        }else if(task.getStatus() == Status.active){
            active.getChildren().add(box);
        }else if(task.getStatus() == Status.done){
            done.getChildren().add(box);
        }
    }

    @FXML
    public void initialize() {

    }


}
