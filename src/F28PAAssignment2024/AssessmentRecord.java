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
		student = std;
		this.course = course;
		this.marks = marks;
	}
	
	public void setFinalMark() {
		for (int i = 0; i < marks.length; i++) {
			
		}
	}
	
	public void setGrade() {
		
	}
}