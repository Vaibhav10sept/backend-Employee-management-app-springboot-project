package com.vaibhav.employee.management.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.vaibhav.employee.management.app.entity.Department;
import com.vaibhav.employee.management.app.entity.Employee;
import com.vaibhav.employee.management.app.entity.EmployeeDto;
import com.vaibhav.employee.management.app.exception.ResourceNotFoundException;
import com.vaibhav.employee.management.app.mapper.EmployeeMapper;
import com.vaibhav.employee.management.app.repository.DepartmentRepository;
import com.vaibhav.employee.management.app.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentServiceImpl departmentService;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Department department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
		System.out.println("create employe departmetn " + department.getId());
		employee.setDepartment(department);
		
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
	}

	public Employee getEmployeeById(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		
		if(optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		}
		else {
			throw new ResourceNotFoundException("employee with this id " + id + " doesn't exist");		
		}
	//      .orElseThrow(() -> 
	//      new ResourceNotFoundException("employee with this id " + id + " doesn't exist"));

	}


	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		Employee employee = getEmployeeById(employeeId);
		
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());

		Department department = departmentService.getDepartmentById(updatedEmployee.getDepartmentId());
		employee.setDepartment(department);
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

}
