package com.springrest.offer.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.springrest.offer.dao.OfferDao;
import com.springrest.offer.entities.Offer;
import com.springrest.offer.services.OfferServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringrestApplicationTests {

	@Autowired
	private OfferServiceImpl offerServices;

	@MockBean
	private OfferDao offerDao;

	@Test
	public void getOfferDetailsByIdTest() {
		Offer offer = new Offer();
		offer.setCategory("Electornics");
		offer.setDetails("Samsung phone");
		offer.setNumberOfLikes(90);
		offer.setOfferDate("08-07-2021");
		offer.setOfferId(1);
		offer.setPoints(7);
		when(offerDao.findById(1)).thenReturn(Optional.of(offer));
	}

	@Test
	public void getOfferByCategoryTest() {
		String category = "Electronics";
		when(offerDao.findByCategory(category))
				.thenReturn(Stream.of(new Offer(1, "lkj", "rohan", "@.com", 9, 2),
						new Offer(2, "lkj", "rohan", "@.com", 19, 1),
						new Offer(3, "lkj", "rohan", "@.com", 90, 2)).collect(Collectors.toList()));

		assertEquals(3, offerServices.getOfferByCategory(category).size());
	}

	@Test
	public void getOfferByDateTest() {
		String offerDate = "20-06-2021";
		when(offerDao.findByOfferDate(offerDate))
				.thenReturn(Stream.of(new Offer(1, "lkj", "rohan", "@.com", 9, 2)).collect(Collectors.toList()));

		assertEquals(0, offerServices.getOfferByCategory(offerDate).size());
	}

	@Test
	public void getOfferByLikesTest() {

		when(offerDao.findByLikes()).thenReturn(Stream.of(new Offer(1, "lkj", "rohan", "@.com", 9,2),
				new Offer(2, "lkj", "rohan", "@.com", 19,  1),
				new Offer(3, "lkj", "rohan", "@.com", 90, 2)).collect(Collectors.toList()));

		assertEquals(3, offerServices.getOfferByLikes().size());
	}

	@Test
	public void addOfferTest() {

		Offer offer = new Offer();
		offer.setCategory("Electornics");
		offer.setDetails("Samsung phone");
		offer.setNumberOfLikes(90);
		offer.setOfferDate("08-07-2021");
		offer.setOfferId(1);
		offer.setPoints(7);

		when(offerDao.save(offer)).thenReturn(offer);
		assertEquals(offer, offerServices.addOffer(offer));

	}

	@Test
	public void  editOfferTest() {

		Offer offer = new Offer();
		offer.setCategory("Electornics");
		offer.setDetails("Samsung phone");
		offer.setNumberOfLikes(90);
		offer.setOfferDate("08-07-2021");
		offer.setOfferId(1);
		offer.setPoints(7);

		when(offerDao.save(offer)).thenReturn(offer);
		assertEquals(offer, offerServices.addOffer(offer));

	}
	
	
	
	
	
}