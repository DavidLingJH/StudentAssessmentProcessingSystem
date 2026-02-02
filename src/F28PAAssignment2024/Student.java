package F28PAAssignment2024;

/**
 * @author DAVID LING JIA HAO
 */

public class Student {
	private String studentID;
	private String name;

	public Student(String id, String name) {
		this.studentID = id;
		this.name = name;
	}

	public String getId() {
		return studentID;
	}

	public String getName() {
		return name;
	}

}