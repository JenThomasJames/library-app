package store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import entity.Book;
import shared.DbUtils;

public class BookStore {

	String bookDbPath = "src/store/bookdb.txt";
	DbUtils dbUtils = new DbUtils();

	/**
	 * @Author Jen Thomas James(2021mt70083) Adds a new book to the memory
	 */
	public void addBook(Book book) {
		try {
			File file = new File(bookDbPath);
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(book.getBookId() + "::" + book.getTitle() + "::" + book.getAuthor() + "::" + book.getPages()
					+ "::" + book.getBookType() + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add book. Try again later.");
		}
	}

	public String findBookById(int bookId) {
		boolean isFound = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(bookDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(bookId, row);
				if (dbUtils.isRowMatchingColumnId(bookId, Integer.parseInt(columns[0]))) {
					isFound = true;
					reader.close();
					return row;
				}
			}
			if (!isFound) {
				System.out.println("No book found with the given Id!");
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Exception occured while getting book details");
		}
		return null;
	}

	public boolean isBookAvailable(int bookId) {
		String book = findBookById(bookId);
		String[] bookDetails = dbUtils.getColumnsFromRow(bookId, book);
		if (bookDetails[5].equals("true")) {
			return true;
		} else {
			return false;
		}
	}

}
