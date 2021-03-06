package dataObjects;

public class Grade {
	
	private Course course;
	private float average;
	private float gpa;
	private String letter;
	
	public Grade(Course course, float average, float gpa, String letter) {
		this.setCourse(course);
		this.setAverage(average);
		this.setGpa(gpa);
		this.setLetter(letter);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}
}
