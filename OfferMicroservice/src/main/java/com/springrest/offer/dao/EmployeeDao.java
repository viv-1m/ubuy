package com.springrest.offer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springrest.offer.entities.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

	// STORE ALL EMPLOYEES MATCHING AN ID

	@Query(value = "select * from employee where emp_id = :empId", nativeQuery = true)
	public List<Employee> findById(@Param("empId") long empId);
	
	@Query(value = "select * from employee where emp_id = :empId", nativeQuery = true)
	public Employee findEmployee(@Param("empId") long empId);
}
