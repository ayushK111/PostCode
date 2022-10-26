package com.sample.restapi.postcode.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.restapi.postcode.model.PostalCode;
import com.sample.restapi.postcode.model.PostalCodeEntity;
import com.sample.restapi.postcode.repository.PostalCodesRepository;

@RestController
public class PostalCodesController {

	@Autowired
	private PostalCodesRepository postalCodesRepository;

	/*** --------------- Logger--------------------------------- */

	private final Logger log = LoggerFactory.getLogger(PostalCodesController.class);

	/***
	 * Passing multiple zipCodes and fetching list of suburbs in sorted form
	 * belonging to that postcode
	 */

	@GetMapping(value = "/postcodes")
	public ResponseEntity<Object> getZipCode(@RequestHeader List<Long> postcodes) {

		log.info("Searching zipcode " + postcodes);

		ArrayList<PostalCodeEntity> postalCodes = new ArrayList<>();
		

		ResponseEntity<Object> response = null;
		postcodes.forEach(postCode->{
		postalCodes.addAll(postalCodesRepository.findBypostCode(postCode));
		});
		

		/*** sort list of suburbs alphabetically */

		postalCodes.stream().map(l -> {
			Collections.sort(l.getSuburbs(), Comparator.naturalOrder());
			return l;
		}).collect(Collectors.toList());

		log.info(postalCodes.toString());

		/*** collecting list of suburbs with their postcodes */

		response = ResponseEntity.status(HttpStatus.OK).body(postalCodes);

		return response;
	}

	@PostMapping(path = "/postcodes/save", consumes = "application/json", produces = "application/json")
	public void addAll(@RequestBody String postalCodeEntity) {
		ObjectMapper mapper = new ObjectMapper();

		PostalCode participantJsonList = null;
		ArrayList<PostalCodeEntity> postalCodes = new ArrayList<>();
		try {
			/*** converting Json String to PostalCode Java pojo through Object Mapper ***/

			participantJsonList = (PostalCode) mapper.readValue(postalCodeEntity, PostalCode.class);

			/**** getting list of postalCodeEntity  ****/

			postalCodes = participantJsonList.getPostalCodeEntity();
		} catch (JsonProcessingException e) {
			log.error("Error while parsing String to PostalCode Java Pojo");
		}

		postalCodes.forEach(postalCode -> {
			/** save postal code with the suburbs to database **/

			PostalCodeEntity ps = postalCodesRepository.save(postalCode);
			/**
			 * Logger for Info
			 **/
			log.info(ps.getSuburbs().toString());

		});
	}

}
