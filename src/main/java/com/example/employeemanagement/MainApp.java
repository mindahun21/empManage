package com.example.employeemanagement;

import com.example.employeemanagement.Controllers.*;
import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;

public class MainApp extends Application {
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showLoginPage();
    }

    public void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            URL cssUrl = getClass().getResource("style.css");
            if (cssUrl == null) {
                System.err.println("Could not find style.css");
            } else {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }
            LoginController loginController = loader.getController();
            loginController.setMainApp(this);
            primaryStage.setScene(scene);
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
            Scene scene = new Scene(root);
            URL cssUrl = getClass().getResource("style.css");
            if (cssUrl == null) {
                System.err.println("Could not find style.css");
            } else {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }
            HomeController homeController = loader.getController();
            homeController.setMainApp(this);
            homeController.setEmployee(employee);

            FXMLLoader dashboardLoader = new FXMLLoader();
            dashboardLoader.setLocation(getClass().getResource("/com/example/employeemanagement/dashboard.fxml"));
            Parent dashboardRoot = dashboardLoader.load();
            DashboardController dashboardController = dashboardLoader.getController();
            dashboardController.setHomeController(homeController);
            homeController.setPane(dashboardRoot);

            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showStaffPage(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("staffPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            URL cssUrl = getClass().getResource("style.css");
            if (cssUrl == null) {
                System.err.println("Could not find style.css");
            } else {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }
            StaffPageController staffPageController = loader.getController();
            staffPageController.setMainApp(this);
            staffPageController.setEmployee(employee);

            FXMLLoader dashboardLoader = new FXMLLoader();
            dashboardLoader.setLocation(getClass().getResource("/com/example/employeemanagement/staffDashboard.fxml"));
            Parent dashboardRoot = dashboardLoader.load();
            StaffDashboardController dashboardController = dashboardLoader.getController();
            dashboardController.setStaffPageController(staffPageController);
            staffPageController.setPane(dashboardRoot);

            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}


