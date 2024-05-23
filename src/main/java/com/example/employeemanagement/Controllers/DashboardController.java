package com.example.employeemanagement.Controllers;

import javafx.fxml.FXML;

public class DashboardController {
    private HomeController homeController;

    @FXML
    private void initialize(){
        this.homeController= new HomeController();
    }

    @FXML
    public void showManageDept(){
        homeController.showManageDept();
    }
    @FXML
    public void showManageStaff(){
        homeController.showManageStaff();
    }
    @FXML
    public void showManageSalary(){
        homeController.showManageSalary();
    }
    @FXML
    public void handleLogout(){
        homeController.handleLogout();
    }
    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }

}
