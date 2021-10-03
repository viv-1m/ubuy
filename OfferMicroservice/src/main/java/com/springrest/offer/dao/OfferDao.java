package com.springrest.offer.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springrest.offer.entities.Offer;

//USING JPA REPOSITORY TO FETCH DATA FROM DATABASE

@Repository
public interface OfferDao extends JpaRepository<Offer, Serializable> {

	// STORE OFFERS BY CATEGORY

	public List<Offer> findByCategory(String category);

	// STORE 3 OFFERS HAVING TOP LIKES

	@Query(value = "select * from offer order by number_Of_Likes desc limit 3", nativeQuery = true)
	public List<Offer> findByLikes();

	// STORE OFFERS BY POSTED DATE

	public List<Offer> findByOfferDate(String offerDate);

	// FIND OFFERS BY THEIR OFFER ID

	@Query(value = "select * from offer where offer_id= :offerId", nativeQuery = true)
	public Offer findOfferById(@Param("offerId") long offerId);

}
