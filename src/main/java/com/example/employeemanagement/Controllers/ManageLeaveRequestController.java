package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Models.EmployeeLeave;
import com.example.employeemanagement.database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class ManageLeaveRequestController {

    @FXML
    private ListView<EmployeeLeave> leaveListView;

    private HomeController homeController;
    private EmployeeLeave selectedEmployeeLeave;

    @FXML
    public void initialize() {
        ObservableList<EmployeeLeave> employeeLeaves = FXCollections.observableArrayList(Database.findAll(EmployeeLeave.class));
        leaveListView.setItems(employeeLeaves);

        // Set cell factory to display the employee leave details in an HBox with color coding
        leaveListView.setCellFactory(param -> new ListCell<EmployeeLeave>() {
            @Override
            protected void updateItem(EmployeeLeave item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(10); // 10 is the spacing between elements in HBox

                    Text idText = new Text("ID: " + item.getId());
                    Text reasonText = new Text("Reason: " + item.getReason());
                    Text statusText = new Text("Status: " + item.getStatus());

                    // Apply color based on the status
                    switch (item.getStatus()) {
                        case Taken:
                            statusText.setFill(Color.GREEN);
                            break;
                        case Pending:
                            statusText.setFill(Color.ORANGE);
                            break;
                        case Declined:
                            statusText.setFill(Color.RED);
                            break;
                    }

                    hBox.getChildren().addAll(idText, reasonText, statusText);
                    setGraphic(hBox);
                }
            }
        });

        leaveListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedEmployeeLeave = newValue;
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/employeemanagement/approveLeaveRequest.fxml"));
                Node pane = loader.load();

                ApproveLeaveRequestController approveLeaveRequestController = loader.getController();
                approveLeaveRequestController.setEmployeeLeave(selectedEmployeeLeave);
                approveLeaveRequestController.setHomeController(this.homeController);
                homeController.setPane(pane);

            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }

    public EmployeeLeave getSelectedEmployeeLeave() {
        return selectedEmployeeLeave;
    }

    @FXML
    public void handleLogout() {
        homeController.handleLogout();
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
