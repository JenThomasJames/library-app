package service;

import java.util.Scanner;

import entity.Book;
import store.BookStore;

public class BookService {

	BookStore bookStore = new BookStore();

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Adds a new book to memory
	 */
	public void addBook(Book book) {
		bookStore.addNewBook(book);
	}

	/**
	 * @Author Jen Thomas James Creates a Book object with the collected details
	 */
	public Book createBook(Scanner scan) {
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
}
