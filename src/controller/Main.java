package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        primaryStage.setTitle("Bad Managing");
        Scene firstScene = new Scene(root);
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}