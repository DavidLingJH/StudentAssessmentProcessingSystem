package F28PAAssignment2024;

import java.util.ArrayList;

/**
 * @author DAVID LING JIA HAO
 */

public class Course {
	private String courseID;
	private String name;
	private int year;
	private int numOfAssessment;
	private int numOfStudents;
	private double[] weights;
	private double[] maxMarks;
	private ArrayList<AssessmentRecord> assessmentResult;

	public Course(String cID, String name, int year, int na, int ns, double[] weights, double[] max) {
		this.courseID = cID;
		this.name = name;
		this.year = year;
		this.numOfAssessment = na;
		this.numOfStudents = ns;

		// Deepcopy
		this.weights = new double[na];
		for (int i = 0; i < weights.length; i++) {
			this.weights[i] = weights[i];
		}

		this.maxMarks = new double[na];
		for (int i = 0; i < max.length; i++) {
			this.maxMarks[i] = max[i];
		}

		this.assessmentResult = new ArrayList<>();
	}

	public double getMean() {
		double sum = 0;

		for (int i = 0; i < assessmentResult.size(); i++) {
			sum += assessmentResult.get(i).getFinalMark();
		}

		return (sum / numOfStudents);
	}

	public double getMin() {
		double lowestMarks = assessmentResult.get(0).getFinalMark();

		for (int i = 1; i < assessmentResult.size(); i++) {
			if (lowestMarks > assessmentResult.get(i).getFinalMark()) {
				lowestMarks = assessmentResult.get(i).getFinalMark();
			}
		}

		return lowestMarks;
	}

	public double getMax() {
		double highestMarks = assessmentResult.get(0).getFinalMark();

		for (int i = 1; i < assessmentResult.size(); i++) {
			if (highestMarks < assessmentResult.get(i).getFinalMark()) {
				highestMarks = assessmentResult.get(i).getFinalMark();
			}
		}

		return highestMarks;
	}

	public double getGradePercentage(char grade) {
		double gradeAppearance = 0;

		for (int i = 0; i < assessmentResult.size(); i++) {
			if (assessmentResult.get(i).getGrade() == grade) {
				gradeAppearance++;
			}
		}

		double gradePercentage = (gradeAppearance / numOfStudents) * 100;

		return gradePercentage;
	}

	public double getPassRate() {
		double numOfFail = 0;

		for (int i = 0; i < assessmentResult.size(); i++) {
			if (assessmentResult.get(i).getGrade() == 'E' || assessmentResult.get(i).getGrade() == 'F') {
				numOfFail++;
			}
		}

		double passRate = ((numOfStudents - numOfFail) / numOfStudents) * 100;

		return passRate;
	}

	// getters and setters
	public double[] getWeights() {
		return this.weights;
	}

	public double[] getMaxMarks() {
		return this.maxMarks;
	}

	public void setAssessmentResult(AssessmentRecord record) {
		assessmentResult.add(record);
	}

	public String getCourseID() {
		return courseID;
	}

	public String getCourseName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public int getNumOfStudents() {
		return numOfStudents;
	}

}