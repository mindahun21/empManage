package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Helper;
import com.example.employeemanagement.Models.Department;
import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.database.Database;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class AddDeptController {
    private HomeController homeController;

    @FXML
    private MFXButton btnAddDeptSubmit;
    @FXML
    private TextField addDeptName;
    @FXML
    private TextField addDeptMID;

    @FXML
    public void handleLogout(){
        homeController.handleLogout();
    }
    @FXML
    public void handleAddDept(){
        String deptName = addDeptName.getText();
        String managerID = addDeptMID.getText();
        try {
            int managerIDInteger = Integer.parseInt(managerID);
            Employee manager = Database.getObjectById(Employee.class,managerIDInteger);
            if(manager != null){
                Department dept =new Department(deptName,managerIDInteger);
                Database.addObj(dept);
                addDeptMID.setText("");
                addDeptName.setText("");
                Helper.showAlert(Alert.AlertType.INFORMATION,"SUCCESSFUL","Department "+deptName+" Added Successfully"," ");
            }else{
                Helper.showAlert(Alert.AlertType.ERROR,"manager not found","manager with "+managerIDInteger,"please Enter valid manager id");
            }
        } catch (NumberFormatException e) {
            Helper.showAlert(Alert.AlertType.ERROR,"Input Error","manager id must be number", "please enter valid Id for manager id.");
        }


    }
    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }
}
