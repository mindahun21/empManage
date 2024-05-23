package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Models.Department;
import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.Models.EmployeeLeave;
import com.example.employeemanagement.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class DashboardController {
    private HomeController homeController;

    @FXML
    private Label departmentsCountLabel;
    @FXML
    private Label staffCountLabel;
    @FXML
    private Label LeaveRequestCountLabel;
    @FXML
    private Label payedSalaryLabel;

    @FXML
    private void initialize(){
        Map<String, Object> emptyMap = new HashMap<>();
        long deptCount = Database.countObjectsByCriteria(Department.class, emptyMap);
        departmentsCountLabel.setText(String.valueOf(deptCount));
        long staffCount = Database.countObjectsByCriteria(Employee.class,emptyMap);
        staffCountLabel.setText(String.valueOf(staffCount));
        Map<String, Object> leaveMap = new HashMap<>();
        leaveMap.put("status", EmployeeLeave.LeaveStatus.Pending);
        long leaveCount = Database.countObjectsByCriteria(EmployeeLeave.class,leaveMap);
        LeaveRequestCountLabel.setText(String.valueOf(leaveCount));

        double totalSalaryOfPaidEmployees = Database.getTotalSalaryOfPaidEmployees();
        payedSalaryLabel.setText(String.valueOf(totalSalaryOfPaidEmployees));

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
    public void showLeaveRequests(){
        homeController.showLeaveRequests();
    }
    @FXML
    public void handleLogout(){
        homeController.handleLogout();
    }
    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }

}
