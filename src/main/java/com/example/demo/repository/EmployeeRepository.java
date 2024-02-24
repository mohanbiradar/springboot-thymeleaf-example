package com.example.demo.repository;

import com.example.demo.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class EmployeeRepository {
    private static final String CSV_FILE_PATH = "src/main/resources/data/employees.csv";

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Employee employee = new Employee();
                employee.setId(parts[0]);
                employee.setName(parts[1]);
                employee.setSalary(Double.parseDouble(parts[2]));
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee findById(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    Employee employee = new Employee();
                    employee.setId(parts[0]);
                    employee.setName(parts[1]);
                    employee.setSalary(Double.parseDouble(parts[2]));
                    return employee;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Employee employee) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            bw.write(employee.getId() + "," + employee.getName() + "," + employee.getSalary());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Employee employee) {
        List<Employee> employees = findAll();
        for (Employee emp : employees) {
            if (emp.getId().equals(employee.getId())) {
                emp.setName(employee.getName());
                emp.setSalary(employee.getSalary());
                break;
            }
        }
        writeAllEmployees(employees);
    }

    public void delete(String id) {
        List<Employee> employees = findAll();
        employees.removeIf(emp -> emp.getId().equals(id));
        writeAllEmployees(employees);
    }

    private void writeAllEmployees(List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (Employee employee : employees) {
                bw.write(employee.getId() + "," + employee.getName() + "," + employee.getSalary());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

