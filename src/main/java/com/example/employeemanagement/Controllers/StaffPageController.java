package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.MainApp;
import com.example.employeemanagement.Models.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.lang.reflect.Method;

public class StaffPageController {

    private MainApp mainApp;
    private Employee employee;

    @FXML
    private StackPane mainStackPane;
    @FXML
    private Label username;


    @FXML
    private void initialize() {

    }
    public void setPane(Node pane) {
        mainStackPane.getChildren().setAll(pane);
    }

    @FXML
    public void showApplyLeave(){loadPane("/com/example/employeemanagement/applyLeave.fxml");}
    @FXML
    public void showSalary(){loadPane("/com/example/employeemanagement/staffSalary.fxml");}
    @FXML
    public void showDashboard() {
        loadPane("/com/example/employeemanagement/staffDashboard.fxml");
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
    public Employee getEmployee(){
        return this.employee;
    }

    private void loadPane(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Node pane = loader.load();
            setPane(pane);

            Object controller = loader.getController();
            Method setStaffControllerMethod = null;
            try {
                setStaffControllerMethod = controller.getClass().getMethod("setStaffPageController", StaffPageController.class);
            } catch (NoSuchMethodException e) {
                // Method not found, not all controllers need to have this method
            }

            if (setStaffControllerMethod != null) {
                setStaffControllerMethod.invoke(controller, this);
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}

