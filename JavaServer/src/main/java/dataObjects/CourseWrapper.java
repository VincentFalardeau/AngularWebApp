package dataObjects;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseWrapper {
	
	//Wrapper class for when receiving a Course object as JSON in a request body.
	//Assigns every member to a JSON property.

	@JsonProperty("id")
	private int id;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("semester")
	private int semester;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("credits")
	private float credits;
	
	public CourseWrapper() {}
		
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCredits() {
		return credits;
	}

	public void setCredits(float credits) {
		this.credits = credits;
	}
}
