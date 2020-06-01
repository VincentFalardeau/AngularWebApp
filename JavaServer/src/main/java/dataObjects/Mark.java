package dataObjects;

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
	
	public Mark(float mark, float weight) {
		this.setMark(mark);
		this.setWeight(weight);
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
