package com.springrest.employee.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ColumnDefault;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "Offer")
public class Offer {

	// ATTRIBUTES
	@Id
	@ColumnDefault("1")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "offer_Id")
	private long offerId;

	@Column(name = "category")
	private String category;

	@NotBlank(message = "This is a mandatory field")
	@Column(name = "details")
	private String details;

	@NotBlank(message = "This is a mandatory field")
	@Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", message = "Date should be in format(dd-MM-yyyy)")
	@Column(name = "offerDate")
	private String offerDate;

	@NotNull(message = "This is a mandatory field")
	@Column(name = "numberOfLikes")
	private long numberOfLikes;

	@Column(name = "points")
	@Value("0")
	private long points;

	@ManyToOne
	private Employee emp;

	// GETTERS AND SETTERS
	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}

	public long getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(long numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	// PARAMETERIZED CONSTRUCTOR

	public Offer(long offerId, String category, String details, String offerDate, long numberOfLikes, long points,
			Employee emp) {
		super();
		this.offerId = offerId;
		this.category = category;
		this.details = details;
		this.offerDate = offerDate;
		this.numberOfLikes = numberOfLikes;
		this.points = points;
		this.emp = emp;
	}

	public Offer(long offerId, String category, @NotBlank(message = "This is a mandatory field") String details,
			@NotBlank(message = "This is a mandatory field") @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$", message = "Date should be in format(dd-MM-yyyy)") String offerDate,
			@NotNull(message = "This is a mandatory field") long numberOfLikes, long points) {
		super();
		this.offerId = offerId;
		this.category = category;
		this.details = details;
		this.offerDate = offerDate;
		this.numberOfLikes = numberOfLikes;
		this.points = points;
	}

	// SUPERCLASS CONSTRUCTOR
	public Offer() {
		super();

	}

}
