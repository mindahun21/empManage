package com.example.employeemanagement.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private Integer departmentId;

    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "ManagerID")
    private Integer managerId;

    @ManyToOne
    @JoinColumn(name = "ManagerID", insertable = false, updatable = false)
    private Employee manager;

    public Department() {
        // Default constructor
    }

    public Department(String departmentName, Integer managerId) {
        this.departmentName = departmentName;
        this.managerId = managerId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
}