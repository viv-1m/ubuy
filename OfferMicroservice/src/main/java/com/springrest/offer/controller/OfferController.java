package com.springrest.offer.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.PropertyAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springrest.offer.exceptions.OfferNotFoundException;
import com.springrest.offer.entities.Employee;
import com.springrest.offer.entities.Offer;
import com.springrest.offer.services.OfferService;

//CONTROLLER APPLICATION

@Controller
@RequestMapping("/offer")
public class OfferController {

	Logger logger = LoggerFactory.getLogger(OfferController.class);

	// AUTOWIRING OBJECT OF SERVICE CLASS

	@Autowired
	private OfferService offerService;

	@GetMapping("/loginsuccess")
	public String engage(Model model) {
		logger.trace("LOGIN SUCCESSFUL");
		return "loginsuccess";
	}

	// ADD A NEW OFFER

	@GetMapping("/addOffer")
	public String addOffer(Model model) {
		logger.trace("ADD A NEW OFFER");
		model.addAttribute("offer", new Offer());
		return "addOffer";
	}

	// ADDED OFFER

	@PostMapping("/postedOffer")
	public String saveOffer(@Valid @ModelAttribute("offer") Offer offer, BindingResult result,
			@RequestParam(value = "empId") long empId, Model model) {
		logger.trace("OFFER ADDED SUCCESSFULLY");

		if (result.hasErrors()) {
			logger.trace("ERROR OCCURED");
			model.addAttribute("offer", offer);
			return "addOffer";
		}
		Employee e = offerService.getEmployee(empId);
		offer.setEmp(e);
		Offer o = offerService.addOffer(offer);
		model.addAttribute("offer", o);
		return "addOffer";
	}

	// DISPLAY ALL OFFERS

	@GetMapping("/offers")
	public String offers(Model model) {
		logger.trace("SHOWING ALL OFFERS");
		List<Offer> offers = offerService.getOfferDetails();
		model.addAttribute("offers", offers);
		return "loginsuccess";
	}

	// DISPLAY ALL OFFERS

	@PostMapping("/offers")
	public String offersBack(Model model) {
		logger.trace("SHOWING ALL OFFERS");
		List<Offer> offers = offerService.getOfferDetails();
		model.addAttribute("offers", offers);
		return "loginsuccess";
	}

	// SEARCH PAGE

	@GetMapping("/search")
	public String searchOffers(Model model) {
		logger.trace("SEARCH PAGE");
		return "search";

	}

	// SEARCH PAGE

	@PostMapping("/search")
	public String searchOffers2(Model model) {
		logger.trace("SEARCH PAGE");
		return "search";
	}

	// SEARCH BY TOP 3 LIKES

	@GetMapping("/toplikes")
	public String topLikes(Model model) {
		logger.trace("DISPLAYING TOP 3 OFFERS WITH MOST LIKES");
		List<Offer> offers = offerService.getOfferByLikes();
		if (offers.isEmpty()) {
			// runtime exception
			logger.trace("EXCEPTION OCCURED");
			throw new OfferNotFoundException("dATABase is not populated");
		}
		model.addAttribute("offers", offers);
		return "toplikes";
	}

	// SEARCH BY ID

	@PostMapping("/searchById")
	public String searchById(Model model, @RequestParam(value = "offerId") long offerId) {
		logger.trace("DISPLAY OFFERS FOR A SPECIFIC OFFER ID");
		Offer offers = null;
		offers = offerService.getOffer(offerId);
		if (offers == null) {
			// runtime exception
			logger.trace("EXCEPTION OCCURED");
			throw new OfferNotFoundException("dATABase is not populated");
		}
		model.addAttribute("offers", offers);
		return "searchById";
	}

	// SEARCH BY CATEGORY

	@PostMapping("/searchByCategory")
	public String searchByCategory(Model model, @RequestParam(value = "category") String category) {
		logger.trace("DISPLAY OFFERS BY CATEGORY");
		List<Offer> offers = offerService.getOfferByCategory(category);
		if (offers.isEmpty()) {
			// runtime exception
			logger.trace("EXCEPTION OCCURED");
			throw new OfferNotFoundException("dATABase is not populated");
		}
		model.addAttribute("offers", offers);
		return "searchByCategory";
	}

	// SEARCH BY POSTED DATE

	@PostMapping("/searchByDate")
	public String searchByDate(Model model, @RequestParam(value = "offerDate") String offerDate) {
		logger.trace("DISPLAY OFFERS BY POSTED DATE");
		List<Offer> offers = offerService.getOfferByDate(offerDate);
		if (offers.isEmpty()) {
			// runtime exception
			logger.trace("EXCEPTION OCCURED");
			throw new OfferNotFoundException("dATABase is not populated");
		}
		model.addAttribute("offers", offers);
		return "searchByDate";
	}

	// UPDATE AN OFFER

	@PostMapping("/updateOffer")
	public String showUpdateForm(@ModelAttribute("offer") Offer offer, Model model) {
		long id = offer.getOfferId();
		Offer offers = offerService.getOffer(id);
		model.addAttribute("offers", offers);
		return "updateOffer";
	}

	// OFFER UPDATED

	@PostMapping("/updatedOffer")
	public String updateUser(@Valid Offer offer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("offer", offer);
			return "updateOffer";
		}
		model.addAttribute("offer", offer);
		return "updateOffer";
	}

	// LIKE AN OFFER

	@PostMapping("/liked")
	public String likeOffer(@ModelAttribute("offer") Offer offer, Model model) {
		logger.trace("OFFER LIKED");
		long i = offer.getOfferId();
		offerService.engageOffer(i);
		List<Offer> offers = offerService.getOfferDetails();
		model.addAttribute("offers", offers);
		return "loginsuccess";
	}

	@ExceptionHandler(value = NullPointerException.class)
	public String exceptionHandlerNullPointer(Model m) {
		return "nullPage";

	}

	@ExceptionHandler(value = OfferNotFoundException.class)
	public String exceptionHandlerEmployeeNotFound(Model m) {
		logger.trace("EXCEPTION RESOLVING");
		return "error1";

	}

	@ExceptionHandler(value = NumberFormatException.class)
	public String exceptionHandlerNumberFormatException(Model m) {
		logger.trace("EXCEPTION RESOLVING");
		return "error1";

	}

	@ExceptionHandler(value = PropertyAccessException.class)
	public String exceptionHandlerPropertyAccessException(Model m) {
		logger.trace("EXCEPTION RESOLVING");
		return "nullPage";

	}

}
