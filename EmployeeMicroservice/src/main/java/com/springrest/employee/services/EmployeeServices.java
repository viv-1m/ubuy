package com.springrest.employee.services;

import java.util.List;

import com.springrest.employee.entities.Employee;
import com.springrest.employee.entities.Offer;

//SERVICE CLASS
public interface EmployeeServices {

	// FIND EMPLOYEES BY EMPLOYEE ID
	public List<Employee> getEmployeeById(long empId);

	// FIND OFFERS POSTED BY EMPLOYEES BY OFFER ID
	public List<Offer> viewEmployeeOffers(long empId);

	// FIND MOST LIKED OFFERS OF AN EMPLOYEE
	public List<Offer> viewMostLikedOffer(long empId);

	// FIND THE LIST OF ALL EMPLOYEES
	public List<Employee> viewEmployees();
	

}
