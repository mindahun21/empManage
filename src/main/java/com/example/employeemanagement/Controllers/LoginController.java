package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.DemoDb.Employee;
import com.example.employeemanagement.MainApp;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        Employee employee = new Employee();
        mainApp.showHomePage(employee);
    }

}
