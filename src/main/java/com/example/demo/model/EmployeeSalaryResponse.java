package com.example.demo.model;

import lombok.Data;

@Data
public class EmployeeSalaryResponse {
    private double salary;

    public EmployeeSalaryResponse(double salary) {
        this.salary = salary;
    }
}
