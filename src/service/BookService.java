package service;

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
}
