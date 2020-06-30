package Tools;

import java.util.ArrayList;

import dataObjects.Grade;
import dataObjects.Mark;

public class GradeTools {
	
	public GradeTools() {}

	// Gives the weighted average of an array of grades.
	public float getGlobalAverage(ArrayList<Grade> grades) {

		float total = 0.0f;
		float totalWeight = 0.0f;

		for (Grade grade : grades) {

			float weight = grade.getCourse().getCredits();

			total += grade.getAverage() * weight;
			totalWeight += weight;
		}

		return total / totalWeight;
	}

	// Gives the weighted GPA of an array of grades.
	public float getGlobalGpa(ArrayList<Grade> grades) {

		float total = 0.0f;
		float totalWeight = 0.0f;

		for (Grade grade : grades) {

			float weight = grade.getCourse().getCredits();

			total += grade.getGpa() * weight;
			totalWeight += weight;
		}

		return total / totalWeight;
	}

	// Gives the weighted average of an array of marks.
	public float getAverage(ArrayList<Mark> marks) {

		float total = 0.0f;
		float totalWeight = 0.0f;

		for (Mark mark : marks) {

			float weight = mark.getWeight();

			total += mark.getMark() * weight;
			totalWeight += weight;
		}

		if (totalWeight == 0.0f) {
			totalWeight = 1.0f;
		}

		return total / totalWeight;
	}

	// Gives the GPA of a mark.
	public float getGpa(float mark) {

		if (mark >= 90) {
			return 4.3f;
		} else if (mark >= 85) {
			return 4.0f;
		} else if (mark >= 80) {
			return 3.7f;
		} else if (mark >= 77) {
			return 3.3f;
		} else if (mark >= 73) {
			return 3.0f;
		} else if (mark >= 70) {
			return 2.7f;
		} else if (mark >= 65) {
			return 2.3f;
		} else if (mark >= 60) {
			return 2.0f;
		} else if (mark >= 57) {
			return 1.7f;
		} else if (mark >= 54) {
			return 1.3f;
		} else if (mark >= 50) {
			return 1.0f;
		} else if (mark >= 35) {
			return 0.5f;
		}
		return 0.0f;
	}

	// Gives the letter of a mark.
	public String getLetter(float mark) {

		if (mark >= 90) {
			return "A+";
		} else if (mark >= 85) {
			return "A";
		} else if (mark >= 80) {
			return "A-";
		} else if (mark >= 77) {
			return "B+";
		} else if (mark >= 73) {
			return "B";
		} else if (mark >= 70) {
			return "B-";
		} else if (mark >= 65) {
			return "C+";
		} else if (mark >= 60) {
			return "C";
		} else if (mark >= 57) {
			return "C-";
		} else if (mark >= 54) {
			return "D+";
		} else if (mark >= 50) {
			return "D";
		} else if (mark >= 35) {
			return "E";
		}
		return "F";
	}
}
