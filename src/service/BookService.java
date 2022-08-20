package service;

import java.util.Scanner;

import entity.Book;
import shared.DbUtils;
import store.BookStore;

public class BookService {

	BookStore bookStore = new BookStore();
	DbUtils dbUtils = new DbUtils();

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Displays the menu for managing books
	 *
	 */
	public void bookMenu(Scanner scan) {
		int choice;
		char menuChoice = 'n';
		do {
			System.out.println("************************");
			System.out.println("\n1)Add new book\n2)Find book by id");
			System.out.println("************************");
			System.out.print("\nYour Choice? : ");
			choice = scan.nextInt();
			bookMenuSwitch(choice, scan);
			System.out.print("Go back to main menu?(Y/N): ");
			menuChoice = scan.next().charAt(0);
		} while (menuChoice == 'n' || menuChoice == 'N');
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Diverts the users choice to respective
	 *         methods
	 *
	 */
	public void bookMenuSwitch(int choice, Scanner scan) {
		switch (choice) {

		// Adds a new Book
		case 1:
			Book book = createBook(scan);
			bookStore.addBook(book);
			break;

		// displays the data of the given book id
		case 2:
			int bookId = getBookId(scan);
			String bookDetails = bookStore.findBookById(bookId);
			showBookDetails(bookDetails);
			break;

		default:
			System.out.println("You made an invalid selection. Please try again!");
		}
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Shows the given book's details
	 *
	 */
	private void showBookDetails(String bookDetails) {
		String[] columns = dbUtils.getColumnsFromRow(5, bookDetails);
		System.out.println("\n*******Book Details*******\n");
		System.out.println("Book ID: " + columns[0] + "\nTitle: " + columns[1] + " " + "\nAuthor: " + columns[2]
				+ "\nNumber of Pages: " + columns[3] + "\nBook Type: " + columns[4]);
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Get's the user's choice of book id
	 *
	 */
	private int getBookId(Scanner scan) {
		System.out.print("Enter the book ID: ");
		int bookId = scan.nextInt();
		return bookId;
	}

	/**
	 * @Author Jen Thomas James Creates a Book object with the collected details
	 */
	public Book createBook(Scanner scan) {
		System.out.println("Pick the book type: \n1) Text Book\n 2) Reference Book");
		int bookChoice = scan.nextInt();
		System.out.println("Enter Book ID: ");
		int bookId = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter book title: ");
		String title = scan.nextLine();
		System.out.println("Enter book author: ");
		String author = scan.nextLine();
		System.out.println("Enter number of pages: ");
		int numberOfPages = scan.nextInt();
		String bookType;
		if (bookChoice == 1) {
			bookType = "Text Book";
		} else {
			bookType = "Reference Book";
		}
		Book book = new Book(bookId, title, author, numberOfPages, bookType);
		return book;
	}
}
