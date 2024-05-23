package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.lang.reflect.Method;

public class HomeController {
    private MainApp mainApp;
    private Employee employee;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Label username;

    @FXML
    private void initialize() {
        if (employee != null) {
            username.setText("@" + employee.getFirstName());
        } else {
            System.err.println("Employee is not set in HomeController");
        }
//        showDashboard();
    }

    public void setPane(Node pane) {
        mainStackPane.getChildren().setAll(pane);
    }

    @FXML
    public void showDashboard() {
        loadPane("/com/example/employeemanagement/dashboard.fxml");
    }

    @FXML
    public void showManageSalary() {
        loadPane("/com/example/employeemanagement/manageSalary.fxml");
    }

    @FXML
    public void showManageStaff() {
        loadPane("/com/example/employeemanagement/manageStaff.fxml");
    }

    @FXML
    public void showAddStaff() {
        loadPane("/com/example/employeemanagement/addStaff.fxml");
    }

    @FXML
    public void showManageDept() {
        loadPane("/com/example/employeemanagement/manageDept.fxml");
    }

    @FXML
    public void showAddDept() {
        loadPane("/com/example/employeemanagement/addDept.fxml");
    }

    @FXML
    public void handleLogout() {
        mainApp.showLoginPage();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (username != null) {
            username.setText("@" + employee.getFirstName());
        }
    }

    private void loadPane(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Node pane = loader.load();
            setPane(pane);

            Object controller = loader.getController();
            Method setHomeControllerMethod = null;
            try {
                setHomeControllerMethod = controller.getClass().getMethod("setHomeController", HomeController.class);
            } catch (NoSuchMethodException e) {
                // Method not found, not all controllers need to have this method
            }

            if (setHomeControllerMethod != null) {
                setHomeControllerMethod.invoke(controller, this);
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
