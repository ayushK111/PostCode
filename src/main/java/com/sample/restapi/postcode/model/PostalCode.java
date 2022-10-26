package com.sample.restapi.postcode.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 
 * @author akhande6
 *  Java Pojo 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostalCode implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<PostalCodeEntity> postalCodeEntity;

	public ArrayList<PostalCodeEntity> getPostalCodeEntity() {
		return postalCodeEntity;
	}

	public void setPostalCodeEntity(ArrayList<PostalCodeEntity> postalCodeEntity) {
		this.postalCodeEntity = postalCodeEntity;
	}

}
