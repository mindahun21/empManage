package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Helper;
import com.example.employeemanagement.Models.EmployeeLeave;
import com.example.employeemanagement.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ApplyLeaveController {
    private StaffPageController staffPageController;

    @FXML
    private TextField leaveReason;
    @FXML
    private TextArea leaveDescription;

    @FXML
    public void handleSubmitLeave(){
        StringBuilder errorMessage = new StringBuilder();
        int employeeId = staffPageController.getEmployee().getEmployeeId(); // Assuming you have a method to get the employee ID
        String reason = leaveReason.getText();
        String description = leaveDescription.getText();
        java.sql.Date startDate = new java.sql.Date(System.currentTimeMillis()); // Get the current date
        boolean approved = false; // Set the initial approval status to false
        EmployeeLeave.LeaveStatus status = EmployeeLeave.LeaveStatus.Pending; // Set the initial status to Pending
        if(reason.isEmpty()){
            errorMessage.append("Reason field is required");
            Helper.showAlert(Alert.AlertType.ERROR,"Input Error","Reason field is required","please enter your reason of leaving our company.");
            return;
        }
        if(description.isEmpty()){
            errorMessage.append("Description field is required");

        }
        if (!errorMessage.isEmpty()) {
            Helper.showAlert(Alert.AlertType.ERROR, "Input Error", "Please correct the following errors:", errorMessage.toString());
            return;
        }
        EmployeeLeave newLeave = new EmployeeLeave(employeeId, reason, description, startDate, approved, status);
        Database.addObj(newLeave);
        Helper.showAlert(Alert.AlertType.INFORMATION,"SUCCESS MESSAGE","Leave request Successfully applied","");
        leaveReason.setText("");
        leaveDescription.setText("");
    }

    @FXML
    public void handleLogout(){
        staffPageController.handleLogout();
    }
    public void setStaffPageController(StaffPageController staffPageController){
        this.staffPageController = staffPageController;
    }
}
