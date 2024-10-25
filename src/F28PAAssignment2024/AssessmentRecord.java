package F28PAAssignment2024;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
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
		
		this.marks = new double[na];
		for (int i = 0; i < marks.length; i++) {
			this.marks[i] = marks[i];
		}
		
//		this.marks = marks.clone();
	}
	
	public void setFinalMark() {
		finalMark = 0;
		double[] weights = course.getWeights();
		double[] maxMarks = course.getMaxMarks();
		
		for (int i = 0; i < marks.length; i++) {
			finalMark += (marks[i] / maxMarks[i]) * weights[i];
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
	
	//getters
	public double getFinalMark() {
		return this.finalMark;
	}
	
	public double getGrade() {
		return this.grade;
	}
}