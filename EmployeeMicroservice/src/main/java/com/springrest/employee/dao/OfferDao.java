package com.springrest.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springrest.employee.entities.Offer;

//DAO CLASS TO FETCH FROM OFFER DATABASE
@Repository
public interface OfferDao extends JpaRepository<Offer, Long> {

	// FIND OFFERS BY EMPLOYEES USING EMPLOYEE ID
	@Query(value = "select * from offer where emp_emp_id = :empId", nativeQuery = true)
	public List<Offer> findAllById(@Param("empId") long empId);

	// FIND MOST LIKED OFFERS OF AN EMPLOYEE USING EMPLOYEE ID
	@Query(value = "select * from offer where emp_emp_id=:empId order by number_of_likes desc limit 0,3 ", nativeQuery = true)
	public List<Offer> findByName(@Param("empId") long empId);

}
