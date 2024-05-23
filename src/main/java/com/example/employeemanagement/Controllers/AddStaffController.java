package com.example.employeemanagement.Controllers;

import com.example.employeemanagement.Helper;
import com.example.employeemanagement.Models.Department;
import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.database.Database;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AddStaffController {
    private HomeController homeController;

    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField emergencyContactText;
    @FXML
    private TextField baseSalaryText;
    @FXML
    private TextField positionText;
    @FXML
    private TextField phoneText;
    @FXML
    private MFXPasswordField passwordText;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private ToggleGroup btnGender;
    @FXML
    private MFXDatePicker birthDatePicker;
    @FXML
    private MFXDatePicker joinDatePicker;
    @FXML
    private ComboBox<Department> departmentComboBox2;

    private Date birthDate;
    private Date dateOfJoin;
    private String selectedGender;
    private int selectedDepartmentId;
    private String firstName;
    private String lastName;
    private String email;
    private Double baseSalary;
    private String phoneNumber;
    private String password;

    @FXML
    private void initialize() {
        btnGender.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            selectedGender = newValue == male ? "Male" : (newValue == female ? "Female" : null);
        });

        birthDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            birthDate = newValue != null ? Date.valueOf(newValue) : null;
        });

        joinDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            dateOfJoin = newValue != null ? Date.valueOf(newValue) : Date.valueOf(LocalDate.now());
        });

        List<Department> departments = Database.findAll(Department.class);

        departmentComboBox2.getItems().addAll(departments);

        departmentComboBox2.setCellFactory(comboBox -> new ListCell<>() {
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

        departmentComboBox2.setButtonCell(new ListCell<>() {
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

        baseSalaryText.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                baseSalary = Double.parseDouble(newValue);
            } catch (NumberFormatException e) {
                baseSalary = null;
            }
        });

        phoneText.textProperty().addListener((observable, oldValue, newValue) -> validatePhoneNumber(newValue));
        passwordText.textProperty().addListener((observable, oldValue, newValue) -> validatePassword(newValue));
    }

    @FXML
    public void handleSubmit() {
        String address;
        String emergencyContact;
        String position;
        StringBuilder errorMessage = new StringBuilder();

        if (firstNameText.getText() == null || firstNameText.getText().isEmpty()) {
            errorMessage.append("First name is required.\n");
        } else {
            firstName = firstNameText.getText();
        }

        if (lastNameText.getText() == null || lastNameText.getText().isEmpty()) {
            errorMessage.append("Last name is required.\n");
        } else {
            lastName = lastNameText.getText();
        }

        if (emailText.getText() == null || emailText.getText().isEmpty()) {
            errorMessage.append("Email is required.\n");
        } else {
            email = emailText.getText();
        }

        if (baseSalary == null) {
            errorMessage.append("Base salary is required and must be a valid number.\n");
        }

        if (password == null) {
            errorMessage.append("Password is required and must contain UpperCase LowerCase and numbers.\n");
        }

        Department selectedDepartment = departmentComboBox2.getSelectionModel().getSelectedItem();
        if (selectedDepartment == null) {
            errorMessage.append("Department must be selected.\n");
        } else {
            selectedDepartmentId = selectedDepartment.getDepartmentId();
        }
        if (!errorMessage.isEmpty()) {
            Helper.showAlert(Alert.AlertType.ERROR, "Input Error", "Please correct the following errors:", errorMessage.toString());
            return;
        }

        address = addressText.getText() != null ? addressText.getText() : "";
        position = positionText.getText() != null ? positionText.getText() : "";
        emergencyContact = emergencyContactText.getText() != null ? emergencyContactText.getText() : "";

        Employee employee = new Employee(firstName, lastName, selectedGender, birthDate, Employee.Role.Staff, selectedDepartmentId, email, password, phoneNumber, address, dateOfJoin, emergencyContact, position, baseSalary);
        Database.addObj(employee);

        firstNameText.setText("");
        lastNameText.setText("");
        emailText.clear();
        addressText.clear();
        emergencyContactText.clear();
        baseSalaryText.clear();
        positionText.clear();
        phoneText.clear();
        passwordText.clear();
        birthDatePicker.clear();
        joinDatePicker.clear();
        departmentComboBox2.getSelectionModel().clearSelection();
        btnGender.selectToggle(null);
        Helper.showAlert(Alert.AlertType.INFORMATION, "ADDITION SUCCESSFUL", "Staff "+employee.getFirstName()+"  "+employee.getLastName()+" Added Successfully ","");

    }

    private void validatePhoneNumber(String phone) {
        String sanitizedPhone = phone.replaceAll("\\D", "");
        phoneNumber = sanitizedPhone.length() == 10 ? sanitizedPhone : null;
    }

    private void validatePassword(String pass) {
        if (isPasswordValid(pass)) {
            password = pass;
        } else {
            password = null;
        }
    }

    private boolean isPasswordValid(String pass) {
        return pass.length() >= 8 && hasUppercase(pass) && hasLowercase(pass) && hasDigit(pass);
    }

    private boolean hasUppercase(String str) {
        return str.matches(".*[A-Z].*");
    }

    private boolean hasLowercase(String str) {
        return str.matches(".*[a-z].*");
    }

    private boolean hasDigit(String str) {
        return str.matches(".*\\d.*");
    }
    @FXML
    public void handleLogout(){
        homeController.handleLogout();
    }
    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }
}


