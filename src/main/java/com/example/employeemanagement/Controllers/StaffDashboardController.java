package com.example.employeemanagement.Controllers;

import javafx.fxml.FXML;

public class StaffDashboardController {
    private StaffPageController staffPageController;


    @FXML
    private void showSalary(){
        staffPageController.showSalary();
    }
    @FXML
    private void showApplyLeave(){
        staffPageController.showApplyLeave();
    }
    @FXML
    public void handleLogout(){
        staffPageController.handleLogout();
    }
    public void setStaffPageController(StaffPageController staffPageController){
        this.staffPageController = staffPageController;
    }

}
