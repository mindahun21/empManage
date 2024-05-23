package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Helper;
import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.MainApp;
import com.example.employeemanagement.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Map;


public class LoginController {
    private MainApp mainApp;

    @FXML
    private TextField loginEmail;
    @FXML
    private PasswordField loginPassword;


    @FXML
    private void handleLogin(ActionEvent event) {
        String email = loginEmail.getText();
        String password = loginPassword.getText();

        Map<String, Object> criteria = Map.of(
                "email", email,
                "password",password
                );

        Employee employee = Database.findObjectByCriteria(Employee.class, criteria);
        if (employee != null) {
            if(employee.getRole() == Employee.Role.Admin){
                mainApp.showHomePage(employee);
            }else{
                mainApp.showStaffPage(employee);
            }
        } else {
            Helper.showAlert(Alert.AlertType.ERROR,"Login Error", "Invalid email or password.", "Please check your login credentials and try again.");
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


}
