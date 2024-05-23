package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.database.Database;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;

public class ManageStaffController {
    private HomeController homeController;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> genderColumn;
    @FXML
    private TableColumn<Employee, String> roleColumn;
    @FXML
    private TableColumn<Employee, String> departmentColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, String> phoneColumn;
    @FXML
    private TableColumn<Employee, String> addressColumn;
    @FXML
    private TableColumn<Employee, String> doJoinColumn;
    @FXML
    private TableColumn<Employee, String> eContactColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private TableColumn<Employee, Void> actionsColumn;

    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        loadEmployees();
    }

    private void loadEmployees() {
        employeeList.addAll(Database.findAll(Employee.class));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        doJoinColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfJoining"));
        eContactColumn.setCellValueFactory(new PropertyValueFactory<>("emergencyContact"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));

        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<>() {

                    private final MFXButton editButton = new MFXButton("Edit");
                    private final MFXButton deleteButton = new MFXButton("Delete");

                    {
                        editButton.setOnAction((ActionEvent event) -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            handleEditAction(event, employee);
                        });
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            employeeList.remove(employee);
                            Database.deleteObj(employee);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new HBox(10, editButton, deleteButton));
                        }
                    }
                };
                return cell;
            }
        };

        actionsColumn.setCellFactory(cellFactory);

        employeeTable.setItems(employeeList);
    }

    private void handleEditAction(ActionEvent event, Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/employeemanagement/editStaff.fxml"));
            Node pane = loader.load();

            EditStaffController editController = loader.getController();
            editController.setEmployee(employee);
            editController.setHomeController(this.homeController);
            homeController.setPane(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleLogout(){
        homeController.handleLogout();
    }

    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }
}
