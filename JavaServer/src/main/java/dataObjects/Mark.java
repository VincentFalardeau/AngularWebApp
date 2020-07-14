package dataObjects;

import java.math.BigDecimal;

public class Mark {
	
	private int id;
	private String description;
	private float mark;
	private float weight;
	private Course course;
	private Category category;
	
	public Mark(int id, String description, float mark, float weight, Course course, Category category) {
		this.setDescription(description);
		this.setId(id);
		this.setMark(mark);
		this.setWeight(weight);
		this.setCategory(category);
		this.setCourse(course);
	}
	
	//Constructor that takes the database's types to build a Mark object.
	public Mark(
			Integer idMark, 
			Integer idCourse, 
			Integer idCategory, 
			String markDescription, 
			BigDecimal mark, 
			BigDecimal weight,  
			String categoryDescription, 
			String courseCode, 
			Integer semester, 
			String courseDescription, 
			BigDecimal courseCredits) {
		
		this.setDescription(markDescription);
		this.setId(idMark);
		this.setMark(mark.floatValue());
		this.setWeight(weight.floatValue());
		this.setCategory(new Category(idCategory, categoryDescription));
		this.setCourse(new Course(idCourse, courseCode, semester, courseDescription, courseCredits));
	}
	
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
