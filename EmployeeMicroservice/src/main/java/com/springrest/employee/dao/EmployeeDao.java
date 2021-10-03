package com.springrest.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springrest.employee.entities.Employee;

//DAO CLASS TO FETCH DATA FROM EMPLOYEE DATABASE
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

	// GET LIST OF EMPLOYEES BY EMPLOYEE ID
	@Query(value = "select* from employee where emp_id = :empId", nativeQuery = true)
	public List<Employee> findById(@Param("empId") long empId);

}
