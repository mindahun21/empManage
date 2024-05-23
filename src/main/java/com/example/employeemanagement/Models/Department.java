package com.example.employeemanagement.Models;

import jakarta.persistence.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private Integer departmentId;

    @Column(name = "DepartmentName")
    private String deptName;

    @Column(name = "ManagerID")
    private Integer managerId;

    @ManyToOne
    @JoinColumn(name = "ManagerID", insertable = false, updatable = false)
    private Employee manager;

    public Department() {
        // Default constructor
    }

    public Department(String deptName, Integer managerId) {
        this.deptName = deptName;
        this.managerId = managerId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }


    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return deptName;
    }

    public void setDepartmentName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
    public SimpleIntegerProperty departmentIdProperty() {
        return new SimpleIntegerProperty(departmentId);
    }

    public SimpleStringProperty departmentNameProperty() {
        return new SimpleStringProperty(deptName);
    }
}