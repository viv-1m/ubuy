package com.springrest.offer.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.offer.dao.EmployeeDao;
import com.springrest.offer.dao.OfferDao;
import com.springrest.offer.entities.Employee;
import com.springrest.offer.entities.Offer;

//SERVICE CLASS TO IMPLEMENT ALL THE SERVICE METHODS
@Service
public class OfferServiceImpl implements OfferService {

	// AUTOWIRING DAO OBJECT FOR OFFER
	@Autowired
	private OfferDao offerDao;

	// AUTOWIRING DAO OBJECT FOR EMPLOYEE
	@Autowired
	EmployeeDao employeeDao;

	// FIND DETAILS OF ALL THE OFFERS
	@Override
	public List<Offer> getOfferDetails() {
		return offerDao.findAll();
	}

	// GET OFFER BY ID
	@Override
	public Optional<Offer> getOffersById(long offerId) {
		return offerDao.findById(offerId);
	}

	// GET OFFER BY CATEGORY
	@Override
	public List<Offer> getOfferByCategory(String category) {
		return offerDao.findByCategory(category);
	}

	// GET OFFER BY POSTED DATE
	@Override
	public List<Offer> getOfferByDate(String offerDate) {
		return offerDao.findByOfferDate(offerDate);
	}

	// GET TOP 3 LIKED OFFERS
	@Override
	public List<Offer> getOfferByLikes() {
		return offerDao.findByLikes();
	}

	// ADD A NEW OFFER
	@Override
	public Offer addOffer(Offer offer) {

		return offerDao.save(offer);
	}

	// EDIT AN EXISTING OFFER
	@Override
	public Offer editOffer(Offer offer) {
		offerDao.save(offer);
		return offer;
	}

	// LIKE AN OFFER
	@Override
	public void engageOffer(long offerId) {
		Optional<Offer> offer = this.getOffersById(offerId);
		if (offer.isPresent()) {
			Offer o = offer.get();
			long temp = o.getNumberOfLikes();
			temp++;
			o.setNumberOfLikes(temp);
			offerDao.save(o);
		}

	}

	// CALCULATE POINTS ON EACH POST
	@Override
	public void calculatePoints() {

		List<Offer> offer = offerDao.findAll();
		for (Offer o : offer) {
			String d = o.getOfferDate();
			long likes = o.getNumberOfLikes();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {

				Date d1 = sdf.parse(d);
				Date d2 = new Date();

				long d1InMs = d1.getTime();
				long d2InMs = d2.getTime();

				long timeDiff = 0;

				if (d1InMs > d2InMs) {
					timeDiff = d1InMs - d2InMs;
				} else {
					timeDiff = d2InMs - d1InMs;
				}

				int daysDiff = (int) (timeDiff / (1000 * 60 * 60 * 24));

				if (likes >= 50 && likes < 100 && daysDiff > 2) {
					o.setPoints(10);
				} else if (likes >= 100 && daysDiff > 2) {
					o.setPoints(50);
				}
				offerDao.save(o);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Employee getEmployee(long empId) {

		return employeeDao.findEmployee(empId);
	}

	// GET OFFER BY ID

	@Override
	public Offer getOffer(long offerId) {
		return offerDao.findOfferById(offerId);
	}

}
