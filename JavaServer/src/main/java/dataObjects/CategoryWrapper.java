package dataObjects;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryWrapper {
	
	//Wrapper class for when receiving a Category object as JSON in a request body.
	//Assigns every member to a JSON property.
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("description")
	private String description;
	
	public CategoryWrapper() {}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
