package com.sample.restapi.postcode.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.restapi.postcode.model.PostalCodeEntity;


@Repository
public interface PostalCodesRepository extends JpaRepository<PostalCodeEntity, Long> {
	
	/*
	 * 
	 *  This method will fetch postalCode with list of suburbs 
	 *  
	 */
       
	ArrayList<PostalCodeEntity> findBypostCode(Long postCode);
	
	

	
	
	}
