package com.example.employeemanagement;

import com.example.employeemanagement.Models.Employee;
import com.example.employeemanagement.Models.Salary;
import com.example.employeemanagement.database.Database;
import com.example.employeemanagement.Models.Attendance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class SalaryCalculation {

    private static final double OVERTIME_RATE = 20.0; // Example overtime rate

    public static void calculateAndStoreSalaries() {
        List<Employee> employees = Database.findAll(Employee.class);

        for (Employee employee : employees) {
            double baseSalary = employee.getBaseSalary();
            double totalOvertime = Database.getTotalOvertimeForEmployee(employee.getEmployeeId());
            double overtimePay = totalOvertime * OVERTIME_RATE;
            double totalSalary = baseSalary + overtimePay;

            Salary salary = new Salary();
            salary.setEmployee(employee);
            salary.setSalaryDate(Date.valueOf(LocalDate.now()));
            salary.setBaseSalary(baseSalary);
            salary.setOvertimePay(overtimePay);
            salary.setTotalSalary(totalSalary);
            salary.setStatus(Salary.Status.Unpaid);

            Database.addObj(salary);
        }
    }


}
