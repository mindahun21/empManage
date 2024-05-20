package com.example.employeemanagement.Models;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "Employee")
public class Employee {

    public enum Role {
        Admin,
        Staff
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private int employeeId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "DateOfBirth")
    private java.sql.Date dateOfBirth;

    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "DepartmentID")
    private Integer departmentId;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "DateOfJoining")
    private Date dateOfJoining;

    @Column(name = "EmergencyContact")
    private String emergencyContact;

    @Column(name = "Position")
    private String position;

    @Column(name = "BaseSalary")
    private double baseSalary;

    public Employee(){

    }
    public Employee(String firstName, String lastName, String gender, Date dateOfBirth, Role role, Integer departmentId, String email, String phone, String address, java.sql.Date dateOfJoining, String emergencyContact, String position, double baseSalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.departmentId = departmentId;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfJoining = dateOfJoining;
        this.emergencyContact = emergencyContact;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.sql.Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(java.sql.Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}

