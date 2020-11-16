package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

import java.io.IOException;
import java.util.Optional;

public class HomeController extends Controller {

    @FXML
    Pane titlePane;

    @FXML
    Pane centerPane;

    @FXML
    Button createTaskboardButton;


    //wechselt zur TaskboardAnsicht
    public void createTaskboardButtonPushed(ActionEvent e) throws IOException {
        //Pop up
        // create view
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("CreateTaskboardScene.fxml"));
        DialogPane createTaskboardDialogPane = fxmlLoader.load();

        // set view
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(createTaskboardDialogPane);
        dialog.setTitle(createTaskboardDialogPane.getHeaderText());

        // disable ok-button
        Node okButton = createTaskboardDialogPane.lookupButton(ButtonType.OK);
        okButton.setDisable(true);

        // enable ok-button if "title"-textfield not empty
        TextField title = (TextField) createTaskboardDialogPane.lookup("#title");
        title.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });

        // what happens if ok button pressed
        Optional<ButtonType> clickedButton = dialog.showAndWait();

        clickedButton.ifPresent(buttonType -> {
            if(buttonType == ButtonType.OK){

                //saveTitle
                String s = title.getText();
                getModel().getTaskboard().setTitle(s);

                //change to TaskboardScene
                Parent taskboardViewParent = null;
                try {
                    taskboardViewParent = FXMLLoader.load(getClass().getResource("TaskboardScene.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Scene taskboardScene = new Scene(taskboardViewParent);

                Taskboard tb = new Taskboard();

                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(taskboardScene);
                window.show();
            }

            if(buttonType == ButtonType.CANCEL){
                dialog.close();
            }

        });
    }

    @FXML
    public void initialize() {
        titlePane.setStyle("-fx-background-color: #0B243B");
        centerPane.setStyle("-fx-background-color: #F0EBE8");

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");

        // dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {

        });
    }
}
