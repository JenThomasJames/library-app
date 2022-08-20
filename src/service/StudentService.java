package service;

import java.util.Scanner;

import entity.Student;
import store.StudentStore;

public class StudentService {

	StudentStore studentStore = new StudentStore();

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Adds a new student to the memory
	 */
	public void addStudent(Student student) {
		studentStore.addStudent(student);
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Fetches all details of the student
	 *         from the memory and displays it to the user.
	 */
	private void displayAllStudentsData() {
		studentStore.getAllStudents();
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Shows the student data menu
	 */
	public void studentMenu(Scanner scan) {
		int choice;
		char menuChoice = 'n';
		do {
			System.out.println("************************");
			System.out.println("\n1)All students list" + "\n2)Find student by ID");
			System.out.println("************************");
			System.out.println("\nYour Choice? : ");
			choice = scan.nextInt();
			studentMenuSwitch(choice, scan);
			System.out.println("Go back to main menu?(Y/N): ");
			menuChoice = scan.next().charAt(0);
		} while (menuChoice == 'n' || menuChoice == 'N');
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Diverts user's choice to respective
	 *         methods for student menu
	 */
	public void studentMenuSwitch(int choice, Scanner scan) {
		switch (choice) {

		// display all the student's data
		case 1:
			displayAllStudentsData();
			break;

		// dsiplays the data of the given user ID
		case 2:
			int studentId = getStudentId(scan);
			studentStore.findStudentById(studentId);
			break;

		default:
			System.out.println("You made an invalid selection. Please try again!");
		}
	}

	private int getStudentId(Scanner scan) {
		System.out.println("Enter the student ID: ");
		int studentId = scan.nextInt();
		return studentId;
	}

	/**
	 * @Author Jen Thomas James Creates a student object with the collected details
	 */
	public Student createStudent(Scanner scan) {
		System.out.println("Enter Student ID: ");
		int studentId = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter First Name: ");
		String firstName = scan.nextLine();
		System.out.println("Enter Last Name: ");
		String lastName = scan.nextLine();
		System.out.println("Enter Batch ID: ");
		long batchId = scan.nextLong();
		Student student = new Student(studentId, firstName, lastName, batchId);
		return student;
	}
}
