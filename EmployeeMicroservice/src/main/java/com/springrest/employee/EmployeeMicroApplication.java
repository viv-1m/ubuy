package com.springrest.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.employeemicros", "com.springrest.employee.services",
		"com.springrest.employee.controller", "com.springrest.employee.dao", "com.springrest.employee.entities" })
public class EmployeeMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMicroApplication.class, args);
	}

}
