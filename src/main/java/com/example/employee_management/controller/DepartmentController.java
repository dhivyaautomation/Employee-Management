package com.example.employee_management.controller;


import com.example.employee_management.model.Department;
import com.example.employee_management.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private static final String INVALID_DEPT_ID = "Department ID is Invalid";
    private static final String ERROR_OCCURRED = "An error occurred";

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getDepartmentById(@PathVariable Long id) {
        try {
            Optional<Department> department = departmentService.getDepartmentById(id);
            return department.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(INVALID_DEPT_ID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_OCCURRED + e.getMessage());
        }
    }


    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        Optional<Department> departmentOptional = departmentService.getDepartmentById(id);
        if (!departmentOptional.isPresent()) {
            return ResponseEntity.badRequest().body(INVALID_DEPT_ID);
        }
        logger.info("Fetching the Department id entry for {}", id);
        Department department = departmentOptional.get();
        department.setName(updatedDepartment.getName());
        Department savedDepartment = departmentService.saveDepartment(department);
        return ResponseEntity.ok(savedDepartment);
    }
}
