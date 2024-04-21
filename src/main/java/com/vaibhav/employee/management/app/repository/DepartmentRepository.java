package com.vaibhav.employee.management.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.employee.management.app.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
