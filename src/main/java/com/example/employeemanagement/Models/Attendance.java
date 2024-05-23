package com.example.employeemanagement.Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttendanceID")
    private int attendanceId;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Column(name = "AttendanceDate")
    private Date attendanceDate;

    @Column(name = "CheckInTime")
    private Time checkInTime;

    @Column(name = "CheckOutTime")
    private Time checkOutTime;

    @Column(name = "OvertimeHours")
    private double overtimeHours;

    public Attendance(){

    }

    // Getters and Setters
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Time getCheckInTime() {
        return checkInTime;
    }

    public Time getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckInTime(Time checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(Time checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }
}
