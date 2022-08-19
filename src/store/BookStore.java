package store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import entity.Book;

public class BookStore {

	/**
	 * @Author Jen Thomas James(2021mt70083) Adds a new book to the memory
	 */
	public void addNewBook(Book book) {
		try {
			File file = new File("src/store/bookdb.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(book.getBookId() + "::" + book.getTitle() + "::" + book.getAuthor() + "::" + book.getPages()
					+ "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add book. Try again later.");
		}
	}

}
