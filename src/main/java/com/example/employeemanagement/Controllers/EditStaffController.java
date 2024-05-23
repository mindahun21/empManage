package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Helper;
import com.example.employeemanagement.Models.Department;
import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.database.Database;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.scene.control.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.fxml.FXML;

import java.sql.Date;
import java.util.List;

public class EditStaffController {
    private HomeController homeController;
    private Employee employee;


    @FXML
    private TextField editFirstNameText;
    @FXML
    private TextField editLastNameText;
    @FXML
    private ComboBox<Department> editDepartmentComboBox;
    @FXML
    private TextField editEmailText;
    @FXML
    private TextField editPhoneText;
    @FXML
    private TextField editAddressText;
    @FXML
    private TextField editEmergencyContactText;
    @FXML
    private TextField editPositionText;
    @FXML
    private TextField editBaseSalaryText;
    @FXML
    private MFXButton btnSaveChanges;

    private Double editBaseSalary;
    private String editPhoneNumber;
    private int editSelectedDepartmentId;
    private String editFirstName;
    private String editLastName;
    private String editEmail;

    @FXML
    public void initialize() {
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
        populateFields();

    }

    private void populateFields() {

        editFirstNameText.setText(employee.getFirstName());
        editLastNameText.setText(employee.getLastName());

        List<Department> departments = Database.findAll(Department.class);

        editDepartmentComboBox.getItems().addAll(departments);

        editDepartmentComboBox.setCellFactory(comboBox -> new ListCell<>() {
            @Override
            protected void updateItem(Department department, boolean empty) {
                super.updateItem(department, empty);
                if (empty || department == null) {
                    setText(null);
                } else {
                    setText(department.getDepartmentName());
                }
            }
        });

        editDepartmentComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Department department, boolean empty) {
                super.updateItem(department, empty);
                if (empty || department == null) {
                    setText(null);
                } else {
                    setText(department.getDepartmentName());
                }
            }
        });
        Department prevDept = departments.stream()
                        .filter(d-> d.getDepartmentId().equals(employee.getDepartmentId()))
                                .findFirst()
                                        .orElse(null);
        editDepartmentComboBox.setValue(prevDept);

        editEmailText.setText(employee.getEmail());
        editPhoneText.setText(employee.getPhone());
        editAddressText.setText(employee.getAddress());
        editEmergencyContactText.setText(employee.getEmergencyContact());
        editPositionText.setText(employee.getPosition());
        editBaseSalaryText.setText(String.valueOf(employee.getBaseSalary()));

        editBaseSalaryText.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                editBaseSalary = Double.parseDouble(newValue);
            } catch (NumberFormatException e) {
                editBaseSalary = null;
            }
        });

        editPhoneText.textProperty().addListener((observable, oldValue, newValue) -> validatePhoneNumber(newValue));
    }

    @FXML
    private void handleSaveChanges() {
        String editPosition;
        String editEmergencyContact;
        String editAddress;
        StringBuilder errorMessage = new StringBuilder();

        if (editFirstNameText.getText() == null || editFirstNameText.getText().isEmpty()) {
            errorMessage.append("First name is required.\n");
        } else {
            editFirstName = editFirstNameText.getText();
        }

        if (editLastNameText.getText() == null || editLastNameText.getText().isEmpty()) {
            errorMessage.append("Last name is required.\n");
        } else {
            editLastName = editLastNameText.getText();
        }

        if (editEmailText.getText() == null || editEmailText.getText().isEmpty()) {
            errorMessage.append("Email is required.\n");
        } else {
            editEmail = editEmailText.getText();
        }

        if (editBaseSalary == null) {
            errorMessage.append("Base salary is required and must be a valid number.\n");
        }

        Department selectedDepartment = editDepartmentComboBox.getSelectionModel().getSelectedItem();
        if (selectedDepartment == null) {
            errorMessage.append("Department must be selected.\n");
        } else {
            editSelectedDepartmentId = selectedDepartment.getDepartmentId();
        }
        if (!errorMessage.isEmpty()) {
            Helper.showAlert(Alert.AlertType.ERROR, "Input Error", "Please correct the following errors:", errorMessage.toString());
            return;
        }

        editAddress = editAddressText.getText() != null ? editAddressText.getText() : "";
        editPosition = editPositionText.getText() != null ? editPositionText.getText() : "";
        editEmergencyContact = editEmergencyContactText.getText() != null ? editEmergencyContactText.getText() : "";

        employee.setFirstName(editFirstName);
        employee.setLastName(editLastName);
        employee.setDepartmentId(editSelectedDepartmentId);
        employee.setEmail(editEmail);
        employee.setPhone(editPhoneNumber);
        employee.setAddress(editAddress);
        employee.setEmergencyContact(editEmergencyContact);
        employee.setPosition(editPosition);
        employee.setBaseSalary(editBaseSalary);

        Database.updateObj(employee);

        editPositionText.setText("");
        editAddressText.setText("");
        editFirstNameText.setText("");
        editLastNameText.setText("");
        editBaseSalaryText.setText("");
        editDepartmentComboBox.getSelectionModel().clearSelection();
        editEmergencyContactText.setText("");
        editEmailText.setText("");
        editPhoneText.setText("");
        Helper.showAlert(Alert.AlertType.INFORMATION, "EDITING SUCCESSFUL", "Staff "+employee.getFirstName()+"  "+employee.getLastName()+" Data Edited Successfully ","");

        this.homeController.showManageStaff();
    }

    @FXML
    private void handleCancel(){
        this.homeController.showManageStaff();
    }
    private void validatePhoneNumber(String phone) {
        String sanitizedPhone = phone.replaceAll("\\D", "");
        editPhoneNumber = sanitizedPhone.length() == 10 ? sanitizedPhone : null;
    }

    @FXML
    public void handleLogout(){
        homeController.handleLogout();
    }

    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }
}
