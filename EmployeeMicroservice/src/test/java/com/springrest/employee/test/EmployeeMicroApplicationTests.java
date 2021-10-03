package com.springrest.employee.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springrest.employee.dao.EmployeeDao;
import com.springrest.employee.dao.OfferDao;
import com.springrest.employee.entities.Employee;
import com.springrest.employee.entities.Offer;
import com.springrest.employee.services.EmployeeServicesImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeMicroApplicationTests {

	@Autowired
	private EmployeeServicesImpl employeeServices;

	@MockBean
	private OfferDao offerDao;

	@MockBean
	private EmployeeDao employeeDao;
	
	@Test
	public void getEmployeeByEmployeeIdTest() {

		long empId = 1;
		when(employeeDao.findById(empId)).thenReturn(
				Stream.of(new Employee(1, "lkj", "rohan", "@.com", "df", "dff")).collect(Collectors.toList()));

		assertEquals(1, employeeServices. getEmployeeById(empId).size());

	}
	

	@Test
	public void viewEmployeeOffersTest() {

		long empId = 1;
		when(offerDao.findAllById(empId)).thenReturn(
				Stream.of(new Offer(1, "lkj", "rohan", "@.com", 9, 2)).collect(Collectors.toList()));

		assertEquals(1, employeeServices.viewEmployeeOffers(empId).size());

	}

	@Test
	public void viewMostLikedOfferTest() {
		long empId = 1;
		when(offerDao.findByName(empId)).thenReturn(Stream.of(new Offer(1, "lkj", "rohan", "@.com", 9, 2),
				new Offer(2, "lkj", "rohan", "@.com", 19, 1),
				new Offer(3, "lkj", "rohan", "@.com", 90, 2)).collect(Collectors.toList()));

		assertEquals(3, employeeServices.viewMostLikedOffer(empId).size());
	}

}