package com.example.employeemanagement;


import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {                   //(1)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root =loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Employee Management System");
        primaryStage.show();
    }
    public static void main(String[] args) throws SQLException {

//        Employee employee = new Employee("abebe", "kebede", LocalDate.of(1990, 5, 15), "M",
//                "johndoe@example.com", "1234567890", "123 Main St", Employee.Role.normal);
//
//        Database.addObj(employee);
//        Employee employee1 = (Employee) Database.getObjectById(Employee.class, 2);
//
//        // Check if the employee1 object is not null
//        if (employee1 != null) {
//            // Print or use the retrieved employee1 object
//            System.out.println("Employee Found: " + employee1.getFirstName() + " " + employee1.getLastName());
//        } else {
//            System.out.println("Employee not found with ID: " + 2);
//        }
        launch();
    }
}