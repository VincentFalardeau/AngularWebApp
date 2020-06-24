package dataObjects;

public class GlobalGrade {
	
	private int semester;
	private float average;
	private float gpa;
	private String letter;
	
	public GlobalGrade(int semester, float average, float gpa, String letter) {
		this.setSemester(semester);
		this.setAverage(average);
		this.setGpa(gpa);
		this.setLetter(letter);
	}
	
	public GlobalGrade(float average, float gpa, String letter) {
		this.setAverage(average);
		this.setGpa(gpa);
		this.setLetter(letter);
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
}
