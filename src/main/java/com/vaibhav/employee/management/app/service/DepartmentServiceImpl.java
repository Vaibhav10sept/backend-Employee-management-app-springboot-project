package com.vaibhav.employee.management.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.employee.management.app.entity.Department;
import com.vaibhav.employee.management.app.entity.Employee;
import com.vaibhav.employee.management.app.exception.ResourceNotFoundException;
import com.vaibhav.employee.management.app.repository.DepartmentRepository;
import com.vaibhav.employee.management.app.repository.EmployeeRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department getDepartmentById(Long id) {
		Optional<Department> optionalDepartment = departmentRepository.findById(id);
		System.out.println("getting departemnt for a id");
		if(optionalDepartment.isPresent()) {
			return optionalDepartment.get();
		} 
		else {
			throw new ResourceNotFoundException("department with this id " + id + " doesn't exist");		
		}
	}

	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}
	
	public Department updateDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public void deleteDepartment(Long id) {
		System.out.println("inseide delte department service");
//		Optional<Department> departmentOptional = departmentRepository.findById(id);
//        departmentOptional.ifPresent(department -> {
//            // Get associated employees and set their department to null
//        	System.out.println("inside emplyee for a departement");
//            List<Employee> employees = department.getEmployees();
//            for (Employee employee : employees) {
//            	System.out.println("department for employee " + employee);
//                employee.setDepartment(null);
//            }
//            // Save the modified employees
//            employeeRepository.saveAll(employees);
//            
//        });
        departmentRepository.deleteById(id);
	}
}
