package JavaServer;

public class Mark {
	private int id;
	private String description;
	private float mark;
	private float ponderation;
	private Course course;
	private Category category;
	
	public Mark(int id, String description, float mark, float ponderation, Course course, Category category) {
		this.setDescription(description);
		this.setId(id);
		this.setMark(mark);
		this.setPonderation(ponderation);
		this.setCategory(category);
		this.setCourse(course);
	}
	
	public Mark(float mark, float ponderation) {
		this.setMark(mark);
		this.setPonderation(ponderation);
	}
	
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

	public float getPonderation() {
		return ponderation;
	}

	public void setPonderation(float ponderation) {
		this.ponderation = ponderation;
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
