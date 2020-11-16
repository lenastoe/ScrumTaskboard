package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
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
import java.time.LocalDate;
import java.util.Optional;

import static java.time.LocalDate.*;

public class TaskboardController extends Controller {

//    buttons backlogItem

    @FXML
    MenuItem createBacklogItemButton;

    @FXML
    MenuItem changeBacklogItemButton;

    @FXML
    MenuItem deleteBacklogItemButton;

//    ------------------------------------

    @FXML
    ListView<ProductBacklogItem> backlogList;

    @FXML
    VBox open;

    @FXML
    VBox active;

    @FXML
    VBox done;

    // opens a dialog to enter a name for a new backlog item
    // by josef
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
        TextField bezeichnung = (TextField) createBacklogItemDialogPane.lookup("#bezeichnung");
        bezeichnung.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });

        // what happens if ok button pressed
        Optional<ButtonType> clickedButton = dialog.showAndWait();

        if (clickedButton.get() == ButtonType.OK) {
            TextArea beschreibung = (TextArea) createBacklogItemDialogPane.lookup("#beschreibung");
            ProductBacklogItem pbi = new ProductBacklogItem(bezeichnung.getText(), beschreibung.getText());
            getModel().getTaskboard().addProductBacklogItem(pbi);
            String s = pbi.getName();
            backlogList.getItems().add(pbi);
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
        expEffort.setPromptText("Geschätzter Aufwand");
        TextField priority = new TextField();
        priority.setPromptText("low, medium or high");

        grid.add(new Label("Bezeichnung:"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Geschätzter Aufwand:"), 0, 1);
        grid.add(expEffort, 1, 1);
        grid.add(new Label("Priorität:"), 0, 2);
        grid.add(priority, 1, 2 );

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

                Task task = new Task(title.getText(), Priority.notSet);
                if (priority.getText().equalsIgnoreCase("low")) {
                    task.setPriority(Priority.low);
                } else if (priority.getText().equalsIgnoreCase("medium")) {
                    task.setPriority(Priority.medium);
                } else if (priority.getText().equalsIgnoreCase("high")) {
                    task.setPriority(Priority.high);
                } else {
                    task.setPriority(Priority.notSet);
                }
                getModel().getTaskboard().addTask(task);
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
        for(Task task : getModel().getTaskboard().getTasks()){
            drawTask(task);
        }
    }

    //show new Task
    public void drawTask(Task task){
        Label id = new Label(Integer.toString(task.getId()));
        Label title = new Label(task.getName());
        Label priority = new Label(task.getPriority().toString());
        id.setStyle("-fx-label-padding: 5px");
        title.setStyle("-fx-label-padding: 5px");
        HBox box = new HBox();

        if (priority.getText().equalsIgnoreCase("low")) {
            task.setPriority(Priority.low);
            box.setStyle("-fx-background-color: green");
        } else if (priority.getText().equalsIgnoreCase("medium")) {
            task.setPriority(Priority.medium);
            box.setStyle("-fx-background-color: yellow");
        } else if (priority.getText().equalsIgnoreCase("high")) {
            task.setPriority(Priority.high);
            box.setStyle("-fx-background-color: red");
        } else {
            box.setStyle("-fx-background-color: white");
        }

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

    // opens a dialog to edit Task
    public void editTaskButtonPushed(ActionEvent e) throws IOException {
        Dialog<ButtonType> dialog2 = new Dialog<>();
        dialog2.setTitle("Bestehenden Task ändern");

        // Set the button types.
        ButtonType okButtonType = ButtonType.OK;
        dialog2.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Create labels and fields.
        GridPane grid2 = new GridPane();
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(20, 150, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Bezeichnung");
        TextField expEffort = new TextField();
        expEffort.setPromptText("Geschätzter Aufwand");
        TextField priority = new TextField();
        priority.setPromptText("low, medium or high");
        DatePicker date = new DatePicker(LocalDate.of(2020, 11, 15));
        TextField name = new TextField();
        name.setPromptText("Vor- und Nachname");
        TextField hoursPerDay = new TextField();
        hoursPerDay.setPromptText("Stunden pro Tag");
        TextField cumulativeEffort = new TextField();
        cumulativeEffort.setPromptText("kumulierter Gesamtaufwand");
        TextField reamingEffort = new TextField();
        reamingEffort.setPromptText("geschätzter Restaufwand");
        TextField status = new TextField();
        status.setPromptText("Offen, aktiv oder erledigt");

        grid2.add(new Label("Bezeichnung:"), 0, 0);
        grid2.add(title, 1, 0);
        grid2.add(new Label("Geschätzer Aufwand:"), 0, 1);
        grid2.add(expEffort, 1, 1);
        grid2.add(new Label("Priorität:"), 0, 2);
        grid2.add(priority, 1, 2 );
        grid2.add(new Label("Datum:"), 0, 3);
        grid2.add(date, 1, 3);
        grid2.add(new Label("Vor- und Nachname:"), 0, 4);
        grid2.add(name, 1, 4);
        grid2.add(new Label("Stunden pro Tag:"), 0, 5);
        grid2.add(hoursPerDay, 1, 5);
        grid2.add(new Label("Kumulierter Gesamtaufwand:"), 0, 6);
        grid2.add(cumulativeEffort, 1, 6);
        grid2.add(new Label("Geschätzter Restaufwand:"), 0, 7);
        grid2.add(reamingEffort, 1, 7);
        grid2.add(new Label("Status:"), 0, 8);
        grid2.add(status, 1, 8);

        dialog2.getDialogPane().setContent(grid2);

        Optional<ButtonType> clickedButton = dialog2.showAndWait();

        clickedButton.ifPresent(buttonType -> {
            if(buttonType == ButtonType.OK){
                //noch offen
            }
            if(buttonType == ButtonType.CANCEL){
                dialog2.close();
            }
        });
    }

    @FXML
    public void initialize() {
        initializeBacklogItemHandling();
    }

    private void initializeBacklogItemHandling() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("ändern");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // handle ändern
                if (backlogList.getSelectionModel().getSelectedItem() != null) {
                    // create view
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CreateBacklogItemScene.fxml"));
                    DialogPane changeBacklogItemDialogPane = null;
                    try {
                        changeBacklogItemDialogPane = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // set view
                    Dialog<ButtonType> dialog = new Dialog<>();
                    dialog.setDialogPane(changeBacklogItemDialogPane);
                    dialog.setTitle("Product Backlog Item ändern");

                    // disable ok-button
                    Node okButton = changeBacklogItemDialogPane.lookupButton(ButtonType.OK);

                    // enable ok-button if "bezeichnung"-textfield not empty
                    TextField bezeichnung = (TextField) changeBacklogItemDialogPane.lookup("#bezeichnung");
                    bezeichnung.textProperty().addListener((observable, oldValue, newValue) -> {
                        okButton.setDisable(newValue.trim().isEmpty());
                    });

                    // set text to selectedBacklogItemText
                    ProductBacklogItem pbi = backlogList.getSelectionModel().getSelectedItem();
                    TextArea beschreibung = (TextArea) changeBacklogItemDialogPane.lookup("#beschreibung");
                    bezeichnung.setText(pbi.getName());
                    beschreibung.setText(pbi.getDescription());

                    // what happens if ok button pressed
                    Optional<ButtonType> clickedButton = dialog.showAndWait();

                    if (clickedButton.get() == ButtonType.OK) {
                        pbi.setName(bezeichnung.getText());
                        pbi.setDescription(beschreibung.getText());
                        backlogList.refresh();
                    }
                }

            }
        });
        MenuItem item2 = new MenuItem("löschen");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //handle löschen
                if (backlogList.getSelectionModel().getSelectedItem() != null) {
                    int i = backlogList.getSelectionModel().getSelectedIndex();
                    ProductBacklogItem pbi = backlogList.getSelectionModel().getSelectedItem();
                    backlogList.getSelectionModel().clearSelection();
                    backlogList.getItems().remove(i);
                    getModel().getTaskboard().removeProductBacklogItem(pbi.getId());
                }
            }
        });
        contextMenu.getItems().addAll(item1, item2);

        backlogList.setContextMenu(contextMenu);
        backlogList.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(backlogList, event.getScreenX(), event.getScreenY());
                event.consume();
            }

        });

        SelectionModel<ProductBacklogItem> backlogListSelectionModel = backlogList.getSelectionModel();
        backlogListSelectionModel.selectedItemProperty().addListener(new ChangeListener<ProductBacklogItem>() {
            @Override
            public void changed(ObservableValue<? extends ProductBacklogItem> observableValue, ProductBacklogItem productBacklogItem, ProductBacklogItem t1) {

            }

        });
    }


}
