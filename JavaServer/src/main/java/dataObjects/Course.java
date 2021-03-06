package dataObjects;

import java.math.BigDecimal;

public class Course {
	
	private int id;
	private String code;
	private int semester;
	private String description;
	private float credits;
	
	public static Course globalCourse() {
		return new Course (-1, "GLOBAL", -1, "Global", 0.0f);
	}
	
	public static Course globalCourse(int semester) {
		return new Course (-1, "GLOBAL", semester, "Global", 0.0f);
	}
	
	public Course(int id, String code, int semester, String description, float credits) {
		this.setId(id);
		this.setCode(code);
		this.setSemester(semester);
		this.setDescription(description);
		this.setCredits(credits);
	}
	
	public Course(Integer id, String code, Integer semester, String description, BigDecimal credits) {
		this.setId(id);
		this.setCode(code);
		this.setSemester(semester);
		this.setDescription(description);
		this.setCredits(credits.floatValue());
	}
	
	public Course(
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
		
		this.setDescription(courseDescription);
		this.setId(idCourse);
		this.setCode(courseCode);
		this.setSemester(semester);
		this.setCredits(courseCredits.floatValue());
	}
	
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
