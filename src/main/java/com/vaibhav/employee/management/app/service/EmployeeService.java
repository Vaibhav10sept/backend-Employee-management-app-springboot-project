package com.vaibhav.employee.management.app.service;

import org.springframework.stereotype.Service;

import com.vaibhav.employee.management.app.entity.Employee;
import com.vaibhav.employee.management.app.entity.EmployeeDto;
import com.vaibhav.employee.management.app.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

public interface EmployeeService {
	
	EmployeeDto createEmployee(EmployeeDto employeeDto);
}
