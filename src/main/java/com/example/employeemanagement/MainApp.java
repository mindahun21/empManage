package com.example.employeemanagement;


import com.example.employeemanagement.Controllers.HomeController;
import com.example.employeemanagement.Controllers.LoginController;
import com.example.employeemanagement.DemoDb.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class MainApp extends Application {
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {                   //(1)
        this.primaryStage = primaryStage;
        showLoginPage();
    }

    public void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setMainApp(this);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showHomePage(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            HomeController homeController = loader.getController();
            homeController.setMainApp(this);
            homeController.setEmployee(employee);

            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}