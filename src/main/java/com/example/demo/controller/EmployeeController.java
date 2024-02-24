package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSalaryResponse;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/{id}/salary")
    public ResponseEntity<EmployeeSalaryResponse> getEmployeeSalary(@PathVariable String id) {
        double salary = employeeService.getEmployeeSalaryById(id);
        EmployeeSalaryResponse response = new EmployeeSalaryResponse(salary);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}

