package com.example.employeemanagement.Controllers;

import javafx.fxml.FXML;

public class ManageSallaryController {
    private HomeController homeController;

    @FXML
    public void handleLogout(){
        homeController.handleLogout();
    }

    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }
}
