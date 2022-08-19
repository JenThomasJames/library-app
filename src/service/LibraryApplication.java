package service;

import java.util.Scanner;

import entity.Book;
import entity.Programme;
import entity.Student;

public class LibraryApplication {

	BookService bookService = new BookService();
	StudentService studentService = new StudentService();
	ProgrammeService programmeService = new ProgrammeService();
	Scanner scan = new Scanner(System.in);

	/**
	 * @Author : Jen Thomas James (2021mt70083) Driver method for main class
	 */
	public void run() {
		int choice;
		char menuChoice = 'y';
		do {
			System.out.println("************************");
			System.out
					.println("\n1)Add New Student\n2)Add New Book\n3)Add New Programme\n4)Student Data\n5)Book Data\n");
			System.out.println("************************");
			System.out.println("\nYour Choice? : ");
			choice = scan.nextInt();
			menuDriver(choice);
			System.out.println("Do you want to continue?(Y/N): ");
			menuChoice = scan.next().charAt(0);
		} while (menuChoice == 'y' || menuChoice == 'Y');
		scan.close();
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Diverts user's choice to respective
	 *         methods
	 */
	public void menuDriver(int choice) {
		switch (choice) {
		// add student
		case 1:
			Student student = createStudent();
			studentService.addStudent(student);
			System.out.println("Student saved successfully!");
			break;

		// add book
		case 2:
			Book book = createBook();
			bookService.addBook(book);
			System.out.println("Book saved successfully!");
			break;

		// add new batch
		case 3:
			Programme programme = createProgramme();
			programmeService.addProgramme(programme);
			System.out.println("Programme saved successfully!");
			break;

		case 4:
			// show student data
			break;

		case 5:
			// show book data
			break;

		default:
			System.out.println("You made an invalid choice. Please make a valid selection.");
			break;
		}
	}

	/**
	 * @Author Jen Thomas James Creates a new batch object with the collected
	 *         details
	 */
	private Programme createProgramme() {
		System.out.println("What is the Programme ID?: ");
		int programmeId = scan.nextInt();
		scan.nextLine();
		System.out.println("What is the name of the programme? : ");
		String title = scan.nextLine();
		System.out.println("How long is the programme?: ");
		int numberOfYears = scan.nextInt();
		System.out.println("How many specializations does this programme have?: ");
		int numberOfSpecializations = scan.nextInt();
		scan.nextLine();
		String specializations = getSpecializations(numberOfSpecializations);
		Programme programme = new Programme(programmeId, title, numberOfYears, 60, specializations);
		return programme;
	}

	/**
	 * @Author Jen Thomas James Gets the specializations for the given programme
	 */
	private String getSpecializations(int numberOfSpecializations) {
		String specializations = "[";
		for (int i = 0; i < numberOfSpecializations; i++) {
			System.out.println("Name of specialization " + (i + 1));
			String specialization = scan.nextLine();
			specializations += "" + specialization;
			if (i != numberOfSpecializations - 1) {
				specializations += ", ";
			}
		}
		specializations += "]";
		return specializations;
	}

	/**
	 * @Author Jen Thomas James Creates a Book object with the collected details
	 */
	private Book createBook() {
		System.out.println("Enter Book ID: ");
		int bookId = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter book title: ");
		String title = scan.nextLine();
		System.out.println("Enter book author: ");
		String author = scan.nextLine();
		System.out.println("Enter number of pages: ");
		int numberOfPages = scan.nextInt();
		Book book = new Book(bookId, title, author, numberOfPages);
		return book;
	}

	/**
	 * @Author Jen Thomas James Creates a student object with the collected details
	 */
	private Student createStudent() {
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
