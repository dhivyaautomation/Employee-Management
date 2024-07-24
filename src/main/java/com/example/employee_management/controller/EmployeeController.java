package com.example.employee_management.controller;


import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private static final String DELETE_SUCCESSFUL = "Employee id deleted successfully";
    private static final String ENTER_VALID_EMPLOYEE_ID  ="Please enter valid Employee id";
    private static final String ENTER_VALID_DEPARTMENT_ID  ="Please enter valid Department id";
    private static final String ERROR_OCCURRED = "An error occurred";

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            return employee.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(ENTER_VALID_EMPLOYEE_ID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_OCCURRED + e.getMessage());
        }
    }


    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Object> getEmployeesByDepartment(@PathVariable Long departmentId) {
        try {
            List<Employee> employees = employeeService.getEmployeesByDepartment(departmentId);
            if (employees.isEmpty()) {
                return ResponseEntity.badRequest().body(ENTER_VALID_DEPARTMENT_ID);
            }
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ERROR_OCCURRED + e.getMessage());
        }
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = employeeOptional.get();
        employee.setName(updatedEmployee.getName());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSkills(updatedEmployee.getSkills());
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (!employeeService.getEmployeeById(id).isPresent()) {
            return ResponseEntity.badRequest().body(ENTER_VALID_EMPLOYEE_ID);
        }

        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(DELETE_SUCCESSFUL);
    }


}
