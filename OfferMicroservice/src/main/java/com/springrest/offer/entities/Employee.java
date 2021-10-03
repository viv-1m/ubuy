package com.springrest.offer.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Employee")
public class Employee {

	// ATTRIBUTES
	@Id
	@JsonIgnore
	@ColumnDefault("1")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_Id")
	private long empId;

	@Size(min = 3, max = 15)
	@Column(name = "empName")
	private String empName;

	@Size(min = 3, max = 15)
	@Column(name = "empPassword")
	private String empPassword;

	@Size(min = 3, max = 15)
	@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Email invalid")
	@Column(name = "email")
	private String email;

	@Pattern(regexp = "^\\\\d{10}$", message = "Phone number invalid")
	@Column(name = "phoneNo")
	private String phoneNo;

	@Size(min = 3, max = 15)
	@Column(name = "role")
	@Value("employee")
	private String role;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	@JsonIgnore
	private List<Offer> offers = new ArrayList<>();

//GETTERS AND SETTERS
	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// PARAMETERIZED CONSTRUCTOR

	public Employee(long empId, String empName, String empPassword, String email, String phoneNo, List<Offer> offers,
			String role) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPassword = empPassword;
		this.email = email;
		this.phoneNo = phoneNo;
		this.offers = offers;
		this.role = role;
	}

	// PARAMETERIZED CONSTRUCTOR

	public Employee(long empId, @Size(min = 3, max = 15) String empName, @Size(min = 3, max = 15) String empPassword,
			@Size(min = 3, max = 15) @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Email invalid") String email,
			@Pattern(regexp = "^\\\\d{10}$", message = "Phone number invalid") String phoneNo,
			@Size(min = 3, max = 15) String role) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPassword = empPassword;
		this.email = email;
		this.phoneNo = phoneNo;
		this.role = role;
	}

	// SUPER CLASS CONSTRUCTOR

	public Employee() {
		super();

	}

}
