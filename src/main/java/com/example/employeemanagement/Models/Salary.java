package com.example.employeemanagement.Models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Salary")
public class Salary {

    public enum Status {
        Unpaid,
        Paid
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalaryID")
    private int salaryId;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Column(name = "SalaryDate")
    private Date salaryDate;

    @Column(name = "BaseSalary")
    private double baseSalary;

    @Column(name = "OvertimePay")
    private double overtimePay;

    @Column(name = "TotalSalary")
    private double totalSalary;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}