package com.springrest.employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.springrest.employee.dao.EmployeeDao;
import com.springrest.employee.dao.OfferDao;
import com.springrest.employee.entities.Employee;
import com.springrest.employee.entities.Offer;

// IMPLEMENTING SERVICE CLASS
@Service
public class EmployeeServicesImpl implements EmployeeServices {

	// AUTOWIRING OBJECT FOR EMPLOYEE DAO
	@Autowired
	private EmployeeDao employeeDao;

	// AUTOWIRING OBJECT FOR EMPLOYEE DAO
	@Autowired
	private OfferDao offerDao;

	// FIND EMPLOYEES BY EMPLOYEE ID
	@Override
	public List<Employee> getEmployeeById(long empId) {
		return employeeDao.findById(empId);
	}

	// FIND OFFERS POSTED BY EMPLOYEES BY OFFER ID
	@Override
	public List<Offer> viewEmployeeOffers(long empId) {
		return offerDao.findAllById(empId);
	}

	// FIND MOST LIKED OFFERS OF AN EMPLOYEE
	@Override
	public List<Offer> viewMostLikedOffer(long empId) {
		return offerDao.findByName(empId);
	}

	// FIND THE LIST OF ALL EMPLOYEES
	@Override
	public List<Employee> viewEmployees() {
		return employeeDao.findAll();
	}


}
