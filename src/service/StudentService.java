package service;

import java.io.BufferedReader;
import java.io.FileReader;
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
			studentMenuSwitch(choice);
			System.out.println("Go back to main menu?(Y/N): ");
			menuChoice = scan.next().charAt(0);
		} while (menuChoice == 'n' || menuChoice == 'N');
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Diverts user's choice to respective
	 *         methods for student menu
	 */
	public void studentMenuSwitch(int choice) {
		switch (choice) {

		// display all the student's data
		case 1:
			System.out.println("Student ID Firstname Lastname");
			displayAllStudentsData();
			break;

		default:
			System.out.println("You made an invalid selection. Please try again!");
		}
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Fetches all details from the memory
	 *         and displays it to the user.
	 */
	private void displayAllStudentsData() {
		String filePath = "src/store/studentdb.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String currentLine = "";
			while ((currentLine = reader.readLine()) != null) {
				String[] column = new String[3];
				column = currentLine.split("::");
				for (int i = 0; i < 3; i++) {
					System.out.print(column[i] + " ");
				}
				System.out.println();
				System.out.println();
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Can't fetch the student details at the moment. Please try again after some time.");
		}
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
