package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.AttendanceSampleData;
import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.Models.Salary;
import com.example.employeemanagement.SalaryCalculation;
import com.example.employeemanagement.database.Database;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.List;

public class ManageSalaryController {
    @FXML
    private Pane manageSalaryPane;
    @FXML
    private MFXButton btnCalculateSalary;
    @FXML
    private TableView<Salary> salaryTable;
    @FXML
    private TableColumn<Salary, Integer> employeeIdCol;
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
    @FXML
    private TableColumn<Salary, MFXButton> actionCol;

    private HomeController homeController;

    @FXML
    public void handleLogout() {
        homeController.handleLogout();
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    private void handleCalculateSalary() {
        AttendanceSampleData.populateSampleData();
        SalaryCalculation.calculateAndStoreSalaries();
        loadSalaries();
    }

    @FXML
    public void initialize() {
        employeeIdCol.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue().getEmployee();
            return new SimpleIntegerProperty(employee.getEmployeeId()).asObject();
        });        employeeNameCol.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue().getEmployee();
            return new SimpleStringProperty(employee.getFirstName() + " " + employee.getLastName());
        });
        baseSalaryCol.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));
        overtimePayCol.setCellValueFactory(new PropertyValueFactory<>("overtimePay"));
        totalSalaryCol.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        actionCol.setCellFactory(col -> new TableCell<Salary, MFXButton>() {
            private final MFXButton payButton = new MFXButton("Pay");

            @Override
            protected void updateItem(MFXButton item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                Salary salary = getTableView().getItems().get(getIndex());
                if (salary.getStatus() == Salary.Status.Unpaid) {
                    payButton.setOnAction(event -> {
                        salary.setStatus(Salary.Status.Paid);
                        Database.updateObj(salary);
                        getTableView().refresh();
                    });
                    setGraphic(payButton);
                } else {
                    setGraphic(null);
                }
            }
        });

        loadSalaries();
    }

    private void loadSalaries() {
        List<Salary> salaries = Database.findAll(Salary.class);
        salaryTable.getItems().setAll(salaries);
    }
}
