package com.sample.restapi.postcode.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.restapi.postcode.converter.StringListConverter;

/***
 * @Entity
 * @author Ayush khandelwal
 *
 */

@Entity
@Table(name = "POSTAL_CODE")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class PostalCodeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column(name = "post_code")

	private Long postCode;

	@Column(name = "suburbs")
	@Convert(converter = StringListConverter.class)
	private List<String> suburbs;

	public Long getPostCode() {
		return postCode;
	}

	public void setPostCode(Long postCode) {
		this.postCode = postCode;
	}

	public List<String> getSuburbs() {
		return suburbs;
	}

	public void setSuburbs(List<String> suburbs) {
		this.suburbs = suburbs;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
