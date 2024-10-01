package F28PAAssignment2024;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
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
	private AssessmentRecord[] assessmentResult; 
	
	public Course(String cID, String name, int year, int na, int ns, double[] weights, double[] max) {
		courseID = cID;
		this.name = name;
		this.year = year;
		numOfAssessment = na;
		numOfStudents = ns;
		this.weights = weights;
		this.maxMarks = max;
	}
	
	
}