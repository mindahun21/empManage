package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Models.Department;
import com.example.employeemanagement.database.Database;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.List;

public class ManageDeptController {
    private HomeController homeController;

    @FXML
    private TableView<Department> departmentTable;
    @FXML
    private TableColumn<Department, Integer> departmentIdColumn;
    @FXML
    private TableColumn<Department, String> departmentNameColumn;
    @FXML
    private TableColumn<Department, Void> actionColumn;

    private final ObservableList<Department> departmentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        departmentList.addAll(Database.findAll(Department.class));

        departmentIdColumn.setCellValueFactory(cellData -> cellData.getValue().departmentIdProperty().asObject());
        departmentNameColumn.setCellValueFactory(cellData -> cellData.getValue().departmentNameProperty());

        Callback<TableColumn<Department, Void>, TableCell<Department, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Department, Void> call(final TableColumn<Department, Void> param) {
                final TableCell<Department, Void> cell = new TableCell<>() {

                    private MFXButton btn = new MFXButton("Delete");

                    {
                        btn.setOnAction((event) -> {
                            Department department = getTableView().getItems().get(getIndex());
                            departmentList.remove(department);
                            // Additionally, remove from database
                            Database.deleteObj(department);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        actionColumn.setCellFactory(cellFactory);

        departmentTable.setItems(departmentList);
    }
    @FXML
    public void handleLogout(){
        homeController.handleLogout();
    }

    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }
}
