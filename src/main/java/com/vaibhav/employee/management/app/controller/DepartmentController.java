package com.vaibhav.employee.management.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.employee.management.app.entity.Department;
import com.vaibhav.employee.management.app.entity.Employee;
import com.vaibhav.employee.management.app.service.DepartmentServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentServiceImpl departmentService;
	
	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments() {
		List<Department> departmentList = departmentService.getAllDepartments();
		return new ResponseEntity<List<Department>>(departmentList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@PostMapping
	ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		Department saveDepartment = departmentService.createDepartment(department);
		return new ResponseEntity<Department>(saveDepartment, HttpStatus.CREATED);
	} 
	
	@PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, 
    		@RequestBody Department department) {
        Department existingDepartment = departmentService.getDepartmentById(id);

        if (existingDepartment != null) {
            // Save the updated employee
        	department.setId(id);
            Department updatedDepartment = departmentService.updateDepartment(department);
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id) {
		 System.out.println("deleting department");
        Department existingDepartment = departmentService.getDepartmentById(id);
        System.out.println("got department");
        if (existingDepartment != null) {
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
