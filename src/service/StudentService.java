package service;

import java.util.Scanner;

import entity.Student;
import shared.DbUtils;
import store.BorrowStore;
import store.StudentStore;

public class StudentService {

	StudentStore studentStore = new StudentStore();
	BookService bookService = new BookService();
	BorrowStore borrowStore = new BorrowStore();
	DbUtils dbUtils = new DbUtils();

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
			System.out.println(
					"\n1) Add new student\n2) Display all students\n3) Display Student details by ID\n4) Borrow Book\n5) Return Book");
			System.out.println("************************");
			System.out.print("\nYour Choice? : ");
			choice = scan.nextInt();
			studentMenuSwitch(choice, scan);
			System.out.print("Go back to main menu?(Y/N): ");
			menuChoice = scan.next().charAt(0);
		} while (menuChoice == 'n' || menuChoice == 'N');
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Diverts user's choice to respective
	 *         methods for student menu
	 */
	public void studentMenuSwitch(int choice, Scanner scan) {
		switch (choice) {

		// Adds a new Student
		case 1:
			Student student = createStudent(scan);
			studentStore.addStudent(student);
			break;

		// display all the student's data
		case 2:
			displayAllStudentsData();
			break;

		// displays the data of the given user ID
		case 3:
			int studentId = getStudentId(scan);
			String studentDetails = studentStore.findStudentById(studentId);
			showStudentDetails(studentDetails);
			break;

		// Borrow book
		case 4:
			int userId = getStudentId(scan);
			int bookId = bookService.getBookId(scan);
			borrowStore.borrowBook(userId, bookId);
			break;

		// Return book
		case 5:
			int sid = getStudentId(scan);
			int bid = bookService.getBookId(scan);
			borrowStore.returnBook(sid, bid);
			break;

		default:
			System.out.println("You made an invalid selection. Please try again!");
		}
	}

	/**
	 * @Author Jen Thomas James(2021mt70083) Displays the student details.
	 */
	private void showStudentDetails(String student) {
		String[] columns = dbUtils.getColumnsFromRow(5, student);
		System.out.println("\n*******Basic Details*******\n");
		System.out.println("Student ID: " + columns[0] + "\nName: " + columns[1] + " " + columns[2]);
		System.out.println("\n*******Programme Details*******\n");
		System.out.println("Programme Name: " + columns[3] + "\nCurrent Year: " + columns[4]);
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Gets the student's Id from the user
	 *
	 */
	private int getStudentId(Scanner scan) {
		System.out.print("Enter the student ID: ");
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
		System.out.println("Enter Programme Name and Specialization: ");
		String programme = scan.nextLine();
		System.out.println("Enter current year: ");
		int currentYear = scan.nextInt();
		scan.nextLine();
		Student student = new Student(studentId, firstName, lastName, programme, currentYear);
		return student;
	}
}
