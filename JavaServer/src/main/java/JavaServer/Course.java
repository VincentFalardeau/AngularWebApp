package JavaServer;

public class Course {
	private int id;
	private String code;
	private int semester;
	private String description;
	private float credits;
	
	public Course(int id, String code, int semester, String description, float credits) {
		this.setId(id);
		this.setCode(code);
		this.setSemester(semester);
		this.setDescription(description);
		this.setCredits(credits);
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
