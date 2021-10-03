package com.springrest.offer.services;

import java.util.List;
import java.util.Optional;

import com.springrest.offer.entities.Employee;
import com.springrest.offer.entities.Offer;

//SERVICE INTERFACE WITH ALL THE NECESSARY ABSTRACT METHODS
public interface OfferService {

	// FIND OFFERS BY OFFER ID
	public Optional<Offer> getOffersById(long offerId);

	// FIND OFFERS BY CATEGORY
	public List<Offer> getOfferByCategory(String category);

	// FIND OFFERS BY
	public List<Offer> getOfferDetails();

	// FIND OFFERS BY POSTED DATE
	public List<Offer> getOfferByDate(String offerDate);

	// FIND OFFERS WITH TOP 3 LIKES
	public List<Offer> getOfferByLikes();

	// ADD A NEW OFFER
	public Offer addOffer(Offer offer);

	// UPDATE AN OFFER
	public Offer editOffer(Offer offer);

	// LIKE AN OFFER
	public void engageOffer(long offerId);

	// CALCULATE POINTS ON EACH POST
	public void calculatePoints();
	
	//GET EMPLOYEE
	public Employee getEmployee(long empId);
	
	//GET OFFER BY ID
	public Offer getOffer(long offerId);
	
}
