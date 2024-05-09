package com.example.employeemanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {                   //(1)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root =loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("materialFx Test");
        primaryStage.show();
    }
    public static void main(String[] args) throws SQLException {

        launch();
    }
}