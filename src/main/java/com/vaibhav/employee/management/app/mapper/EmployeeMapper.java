package com.vaibhav.employee.management.app.mapper;

import com.vaibhav.employee.management.app.entity.Employee;
import com.vaibhav.employee.management.app.entity.EmployeeDto;

public class EmployeeMapper {
	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		return new Employee(
				employeeDto.getId(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail()
				);
	}
	
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(
				employee.getId(), 
				employee.getFirstName(), 
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartment().getId()
				);
	}
}
