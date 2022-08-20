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
		mainMenu();
		scan.close();
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Shows the main menu
	 */
	private void mainMenu() {
		int choice;
		char menuChoice = 'y';
		do {
			System.out.println("************************");
			System.out.println("\n1)Add New Student" + "\n2)Add New Book" + "\n3)Add New Programme"
					+ "\n4)Student Data\n" + "5)Book Data\n");
			System.out.println("************************");
			System.out.println("\nYour Choice? : ");
			choice = scan.nextInt();
			mainMenuSwitch(choice);
			System.out.println("Do you want to continue?(Y/N): ");
			menuChoice = scan.next().charAt(0);
		} while (menuChoice == 'y' || menuChoice == 'Y');
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Diverts user's choice to respective
	 *         methods
	 */
	public void mainMenuSwitch(int choice) {
		switch (choice) {
		// add student
		case 1:
			Student student = studentService.createStudent(scan);
			studentService.addStudent(student);
			System.out.println("Student saved successfully!");
			break;

		// add book
		case 2:
			Book book = bookService.createBook(scan);
			bookService.addBook(book);
			System.out.println("Book saved successfully!");
			break;

		// add new batch
		case 3:
			Programme programme = programmeService.createProgramme(scan);
			programmeService.addProgramme(programme);
			System.out.println("Programme saved successfully!");
			break;

		// show student data
		case 4:
			studentService.studentMenu(scan);
			break;

		case 5:
			// show book data
			break;

		default:
			System.out.println("You made an invalid choice. Please make a valid selection.");
			break;
		}
	}
}
