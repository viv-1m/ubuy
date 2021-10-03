package com.springrest.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springrest.employee.exceptions.EmployeeNotFoundException;
import com.springrest.employee.entities.Employee;
import com.springrest.employee.entities.Offer;
import com.springrest.employee.services.EmployeeServices;

//CONTROLLER CLASS

@Controller
@RequestMapping("/emp")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	// AUTOWIRING OBJEC OF EMPLOYEE SERVICE CLASS

	@Autowired
	private EmployeeServices employeeService;

	// SHOW ALL EMPLOYEES

	@GetMapping("/employees")
	public String offers(Model model) {
		List<Employee> employees = employeeService.viewEmployees();
		model.addAttribute("employees", employees);
		return "showEmployees";
	}

	// SHOW ALL EMPLOYEES

	@PostMapping("/employees")
	public String showOffers(Model model) {
		logger.trace("SHOWING ALL EMPLOYEES");
		List<Employee> employees = employeeService.viewEmployees();
		if (employees.isEmpty()) {
			// runtime exception
			throw new EmployeeNotFoundException("dATABase is not populated");
		}
		model.addAttribute("employees", employees);
		return "showEmployees";
	}

	// SEARCH PAGE

	@GetMapping("/search")
	public String searchOffers(Model model) {
		logger.trace("SEARCH FOR OFFERS");
		return "search";

	}

	// SEARCH PAGE

	@PostMapping("/search")
	public String searchOffers2(Model model) {
		logger.trace("SEARCH OFFERS");
		return "search";
	}

	// SEARCH EMPLOYEES BY ID

	@PostMapping("/searchById")
	public String searchById(Model model, @RequestParam(value = "empId") long empId) {
		logger.trace("SEARCH BY ID");
		List<Employee> employees = employeeService.getEmployeeById(empId);
		if (employees.isEmpty()) {
			// runtime exception
			logger.trace("EXCEPTION OCCURED");
			throw new EmployeeNotFoundException("Employee id: " + empId);
		}
		model.addAttribute("employees", employees);
		return "employeesById";
	}

	// SEARCH MOST LIKED OFFERS OF AN EMPLOYEE BY ID

	@PostMapping("/searchLiked")
	public String searchMostLiked(Model model, @RequestParam(value = "empId") long empId) {
		logger.trace("SEARCH MOST LIKED OFFERS OF AN EMPLOYEE");
		List<Offer> offers = employeeService.viewMostLikedOffer(empId);
		if (offers.isEmpty()) {
			// runtime exception
			logger.trace("EXCEPTION OCCURED");
			throw new EmployeeNotFoundException("Employee id: " + empId);
		}
		model.addAttribute("offers", offers);
		return "mostLikedOffers";
	}

	// SEARCH OFFERS OF AN EMPLOYEE BY ID

	@PostMapping("/offerById")
	public String searchOffer(Model model, @RequestParam(value = "empId") long empId) {
		logger.trace("SEARCH OFFERS OF AN EMPLOYEE BY ID");
		List<Offer> offers = employeeService.viewEmployeeOffers(empId);
		if (offers.isEmpty()) {
			// runtime exception
			logger.trace("EXCEPTION OCCURED");
			throw new EmployeeNotFoundException("Employee id: " + empId);
		}
		model.addAttribute("offers", offers);
		return "offerById";
	}

	// RESOLVING NULLPOINTER EXCEPTION

	@ExceptionHandler(value = NullPointerException.class)
	public String exceptionHandlerNullPointer(Model m) {
		logger.trace("EXCEPTION RESOLVING");
		return "nullPage";

	}

	// IF EMPLOYEE IS NOT FOUND

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public String exceptionHandlerEmployeeNotFound(Model m) {
		logger.trace("EXCEPTION RESOLVING");
		return "error1";

	}

	// RESOLVING NUMBERFORMAT EXCEPTION

	@ExceptionHandler(value = NumberFormatException.class)
	public String exceptionHandlerNumberFormatException(Model m) {
		logger.trace("EXCEPTION RESOLVING");
		return "error1";

	}
}
