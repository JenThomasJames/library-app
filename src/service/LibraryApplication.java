package service;

import java.util.Scanner;

public class LibraryApplication {

	BookService bookService = new BookService();
	StudentService studentService = new StudentService();
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
			System.out.println("\n1) Manage Students" + "\n2) Manage Books" + "\n3) Exit\n");
			System.out.println("************************");
			System.out.print("\nYour Choice? : ");
			choice = scan.nextInt();
			mainMenuSwitch(choice);
			System.out.print("Do you want to continue?(Y/N): ");
			menuChoice = scan.next().charAt(0);
		} while (menuChoice == 'y' || menuChoice == 'Y');
	}

	/**
	 * @Author : Jen Thomas James (2021mt70083) Diverts user's choice to respective
	 *         methods
	 */
	public void mainMenuSwitch(int choice) {
		switch (choice) {
		// manage students
		case 1:
			studentService.studentMenu(scan);
			break;

		// manage books
		case 2:
			bookService.bookMenu(scan);
			break;

		// Exit from app
		case 3:
			System.exit(0);
			break;

		default:
			System.out.println("You made an invalid choice. Please make a valid selection.");
			break;
		}
	}
}
