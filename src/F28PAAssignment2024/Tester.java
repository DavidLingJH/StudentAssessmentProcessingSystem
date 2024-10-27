package F28PAAssignment2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
 * @author DAVID LING JIA HAO
 */

public class Tester {
	public static void readInput(String inputFile, ArrayList<Course> courses, ArrayList<Student> students) throws InvalidMarkException, FileNotFoundException, NoSuchElementException{		
		File f1 = new File(inputFile);
		try (Scanner input = new Scanner(f1)){
			int numOfCourses = input.nextInt();
			
			for (int i = 0; i < numOfCourses; i++) {
				//Read course details
				String cID = input.next();
				String name = input.next();
				int year = input.nextInt();
				int na = input.nextInt();
				int ns = input.nextInt();
				input.nextLine();
				
				//Split the String line into specific double of an array(weights and max)
				double[] weights = parseDoubleFromStringArray(input.nextLine(), true);
				double[] max = parseDoubleFromStringArray(input.nextLine(), false);
				
				Course c1 = new Course(cID, name, year, na, ns, weights, max);
				courses.add(c1);
				
				//Read student details
				for (int j = 0; j < ns; j++) {
					String studentID = input.next();
					String studentName = input.next();
					input.nextLine();
					
					double[] marks = parseMarks(input.nextLine(), studentID, max, name);
								
					Student s1 = findOrCreateStudent(studentID, studentName, students);
					
					AssessmentRecord ar1 = new AssessmentRecord(s1, c1, na, marks);
					ar1.setFinalMark();
					ar1.setGrade();
					c1.setAssessmentResult(ar1);
				}
			}
			
			input.close();
			
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found: " + inputFile);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No such element: The file is incomplete and lack certain data");
		} 
	}
	
	public static double[] parseDoubleFromStringArray(String line, boolean isWeights) throws InvalidMarkException{
		String[] parts = line.trim().split("\\s+");
		double[] result = new double[parts.length];
		double sum = 0.0;
		
		for (int i = 0; i < parts.length; i++) {
			result[i] = Double.parseDouble(parts[i]);
			
			if(result[i] < 0 || result[i] > 100) {
				throw new InvalidMarkException("The weights/maximum marks are not between 0 and 100");
			}
			
			if(isWeights) {
				sum += result[i];
			}
        }
		
		if(isWeights && sum != 100) {
			throw new InvalidMarkException("The total weights do not add up to 100. Current total: " + sum);
		}
		
		return result;
	}
	
	public static double[] parseMarks(String line, String studentID, double[] maxMarks, String courseName) throws InvalidMarkException{
		String[] markStringArray = line.trim().split("\\s+");
		
	    if (markStringArray.length != maxMarks.length) {
	        throw new InvalidMarkException("Invalid number of marks for student " + studentID + " in course " + courseName + 
	                                       ":\nExpected: " + maxMarks.length + "\nReceived: " + markStringArray.length);
	    }
		
		double[] marks = new double[markStringArray.length];
		
		for (int i = 0; i < markStringArray.length; i++) {
            try {
            	marks[i] = Double.parseDouble(markStringArray[i]);
            	
            	if (marks[i] < 0) {
            		throw new InvalidMarkException("Invalid mark for student " + studentID + ": " + marks[i]);
            	}
            	
            	//Compare marks to prevent marks > maxMarks
            	if(marks[i] > maxMarks[i]) {
            		throw new InvalidMarkException("Invalid mark for student " + studentID + ": " + marks[i] + "\nThe mark exceeds the maximum mark of " + maxMarks[i] + " for assessment " + (i+1) + " for course " + courseName);
            	}
            	
            } catch(NumberFormatException e) {
            	throw new InvalidMarkException("Invalid mark for student " + studentID + ": " + markStringArray[i]);
            }
        }
		return marks;
	}
	
	public static Student findOrCreateStudent(String studentID, String studentName, ArrayList<Student> students) {
		for (Student student : students) {
			if (studentID.equals(student.getId())) {
				return student;
			}
		}
		
		Student newStudent = new Student(studentID, studentName);
		students.add(newStudent);
		return newStudent;
	}
	
	public static void printReport(ArrayList<Course> courses, ArrayList<Student> students) {
		for (Course oneCourse : courses) {
			System.out.println("CourseID: " + oneCourse.getCourseID());
			System.out.println("Name: " + oneCourse.getCourseName());
			System.out.println("Year: " + oneCourse.getYear());
			System.out.printf("Minimum Final Mark: %.2f%n", oneCourse.getMin());
			System.out.printf("Maximum Final Mark: %.2f%n", oneCourse.getMax());
			System.out.printf("Average Final Mark: %.2f%n", oneCourse.getMean());
			System.out.println("Number of students: " + oneCourse.getNumOfStudents());
			System.out.printf("Pass rate: %.2f%%%n", oneCourse.getPassRate());
			System.out.printf("A: %.2f%%  ", oneCourse.getGradePercentage('A'));
			System.out.printf("B: %.2f%%  ", oneCourse.getGradePercentage('B'));
			System.out.printf("C: %.2f%%  ", oneCourse.getGradePercentage('C'));
			System.out.printf("D: %.2f%%  ", oneCourse.getGradePercentage('D'));
			System.out.printf("E: %.2f%%  ", oneCourse.getGradePercentage('E'));
			System.out.printf("F: %.2f%%%n  ", oneCourse.getGradePercentage('F'));
			
			System.out.println();
		}
	}
	
    public static void main(String[] args){   
    	ArrayList<Course> courses = new ArrayList<Course>();
    	ArrayList<Student> students = new ArrayList<Student>();
    	
    	try {
    		if (args.length != 1) {
    			System.out.println("Error: Invalid Argument");
    		}
    	
    		String inputFile = args[0];
    		readInput(inputFile, courses, students);
			printReport(courses, students);
		
		} catch (InvalidMarkException | FileNotFoundException | NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
    }
}