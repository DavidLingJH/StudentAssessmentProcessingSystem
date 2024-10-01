package F28PAAssignment2024;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
 * @author DAVID LING JIA HAO
 */

public class Student {
	private String studentID;
	private String name;
	
	public Student(String id, String name) {
		studentID = id;
		this.name = name;
	}
	
	public String getId() {
		return studentID;
	}
	
	public String getName() {
		return name;
	}

}