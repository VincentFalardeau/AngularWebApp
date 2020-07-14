package dataObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarkWrapper {
	
	//Wrapper class for when receiving a Mark object as JSON in a request body.
	//Assigns every member to a JSON property.
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("mark")
	private float mark;
	
	@JsonProperty("weight")
	private float weight;
	
	@JsonProperty("course")
	private CourseWrapper course;
	
	@JsonProperty("category")
	private CategoryWrapper category;
	
	public MarkWrapper() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public CategoryWrapper getCategory() {
		return category;
	}

	public void setCategory(CategoryWrapper category) {
		this.category = category;
	}

	public CourseWrapper getCourse() {
		return course;
	}

	public void setCourse(CourseWrapper course) {
		this.course = course;
	}
}
