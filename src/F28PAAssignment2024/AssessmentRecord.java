package F28PAAssignment2024;

/**
 * @author DAVID LING JIA HAO
 */

public class AssessmentRecord {
	private Student student;
	private Course course;
	private double[] marks;
	private double finalMark;
	private char grade;

	public AssessmentRecord(Student std, Course course, int na, double[] marks) {
		this.student = std;
		this.course = course;

		// Deepcopy of marks
		this.marks = new double[na];
		for (int i = 0; i < marks.length; i++) {
			this.marks[i] = marks[i];
		}
	}

	public void setFinalMark() throws InvalidMarkException {
		finalMark = 0;
		double[] weights = course.getWeights();
		double[] maxMarks = course.getMaxMarks();

		for (int i = 0; i < marks.length; i++) {
			finalMark += (marks[i] / maxMarks[i]) * weights[i];
		}

		if (finalMark < 0 || finalMark > 100) {
			throw new InvalidMarkException("Input error: Final marks exceed boundary");
		}
	}

	public void setGrade() {
		if (finalMark >= 70) {
			grade = 'A';
		} else if (finalMark >= 60) {
			grade = 'B';
		} else if (finalMark >= 50) {
			grade = 'C';
		} else if (finalMark >= 40) {
			grade = 'D';
		} else if (finalMark >= 30) {
			grade = 'E';
		} else {
			grade = 'F';
		}
	}

	// getters
	public double getFinalMark() {
		return this.finalMark;
	}

	public double getGrade() {
		return this.grade;
	}
}