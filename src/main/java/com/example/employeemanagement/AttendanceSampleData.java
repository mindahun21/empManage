package com.example.employeemanagement;

import com.example.employeemanagement.Models.Attendance;
import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.database.Database;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class AttendanceSampleData {

    public static void populateSampleData() {
        List<Employee> employees = Database.findAll(Employee.class);

        for (Employee employee : employees) {
            for (int i = 1; i <= 30; i++) { // Assuming a month has 30 days for simplicity
                Attendance attendance = new Attendance();
                attendance.setEmployee(employee);
                attendance.setAttendanceDate(Date.valueOf(LocalDate.now().minusDays(30 - i)));
                attendance.setCheckInTime(Time.valueOf("09:00:00"));
                attendance.setCheckOutTime(Time.valueOf("18:30:00")); // 0.5 hours overtime
//                attendance.setOvertimeHours(0.5);
                Database.addObj(attendance);
            }
        }
    }
}
