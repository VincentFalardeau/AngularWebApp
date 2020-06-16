package dataObjects;

import java.math.BigDecimal;

public class Category {
	
	private Integer id;
	private String description;
	
	public Category(Integer id, String description) {
		this.setId(id);
		this.setDescription(description);
	}
	
	public Category(
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
		
		this.setDescription(categoryDescription);
		this.setId(idCategory);
	}

	public Integer getId() {
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
