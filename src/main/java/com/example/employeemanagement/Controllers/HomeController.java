package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.DemoDb.Employee;
import com.example.employeemanagement.MainApp;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class HomeController {
    private MainApp mainApp;
    private Employee employee;

    @FXML
    private StackPane mainStackPane;
    @FXML
    private Pane dashboardPane;
    @FXML
    private Pane manageSalaryPane;
    @FXML
    private Pane manageStaffPane;
    @FXML
    private Pane addStaffPane;
    @FXML
    private Pane manageDeptPane;
    @FXML
    private Pane addDeptPane;

    @FXML
    private void showDashboard(){
        hideAllPanes();
        dashboardPane.setVisible(true);
    }
    @FXML
    private void showManageSalary(){
        hideAllPanes();
        manageSalaryPane.setVisible(true);
    }
    @FXML
    private void showManageStaff(){
        hideAllPanes();
        manageStaffPane.setVisible(true);
    }
    @FXML
    private void showAddStaff(){
        hideAllPanes();
        addStaffPane.setVisible(true);
    }
    @FXML
    private void showManageDept(){
        hideAllPanes();
        manageDeptPane.setVisible(true);
    }
    @FXML
    private void showAddDept(){
        hideAllPanes();
        addDeptPane.setVisible(true);
    }
    @FXML
    private void handleLogout(){
        mainApp.showLoginPage();
    }



    private void hideAllPanes() {
        dashboardPane.setVisible(false);
        manageDeptPane.setVisible(false);
        manageSalaryPane.setVisible(false);
        manageStaffPane.setVisible(false);
        addDeptPane.setVisible(false);
        addStaffPane.setVisible(false);
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
