package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Models.EmployeeLeave;
import com.example.employeemanagement.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;

public class ApproveLeaveRequestController {
    private HomeController homeController;
    private EmployeeLeave employeeLeave;


    @FXML
    private TextField leaveReasonText;

    @FXML
    private TextField leaveDescriptionText;

    @FXML
    public void handleApproveCancel(){
        this.homeController.showLeaveRequests();
    }
    @FXML
    public void handleApprove(){
        employeeLeave.setStatus(EmployeeLeave.LeaveStatus.Taken);
        Database.updateObj(employeeLeave);
        this.homeController.showLeaveRequests();
    }
    @FXML
    public void handleDeclineApprove(){
        employeeLeave.setStatus(EmployeeLeave.LeaveStatus.Declined);
        Database.updateObj(employeeLeave);
        this.homeController.showLeaveRequests();
    }

    @FXML
    public void initialize(){

    }

    @FXML
    public void handleLogout() {
        homeController.handleLogout();
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
    public void setEmployeeLeave(EmployeeLeave employeeLeave){
        this.employeeLeave = employeeLeave;
        leaveReasonText.setText(employeeLeave.getReason());
        leaveDescriptionText.setText(employeeLeave.getDescription());
    }
}
