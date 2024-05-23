package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.Models.Salary;
import com.example.employeemanagement.database.Database;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class StaffSalaryController {
    @FXML
    private TableView<Salary> salaryTable;
    @FXML
    private TableColumn<Salary, Integer> salaryIdCol;
    @FXML
    private TableColumn<Salary, String> employeeNameCol;
    @FXML
    private TableColumn<Salary, Double> baseSalaryCol;
    @FXML
    private TableColumn<Salary, Double> overtimePayCol;
    @FXML
    private TableColumn<Salary, Double> totalSalaryCol;
    @FXML
    private TableColumn<Salary, String> statusCol;


    private StaffPageController staffPageController;

    @FXML
    public void initialize() {
        salaryIdCol.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        employeeNameCol.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue().getEmployee();
            return new SimpleStringProperty(employee.getFirstName() + " " + employee.getLastName());
        });
        baseSalaryCol.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));
        overtimePayCol.setCellValueFactory(new PropertyValueFactory<>("overtimePay"));
        totalSalaryCol.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    @FXML
    public void handleLogout(){
        staffPageController.handleLogout();
    }
    public void setStaffPageController(StaffPageController staffPageController){
        this.staffPageController = staffPageController;
        loadSalaries();
    }
    private void loadSalaries(){
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("employee", staffPageController.getEmployee());
        List<Salary> salaries = Database.findObjectsByCriteria(Salary.class, criteria);
        salaryTable.getItems().setAll(salaries);
    }
}
