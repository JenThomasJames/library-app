package store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
					+ "::" + book.getBookType() + "::" + book.isAvailable() + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add book. Try again later.");
		}
	}

	/**
	 * @Author Jen Thomas James(2021mt70083) Fetches a book from memory based on ID
	 */
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

	/**
	 * @Author Jen Thomas James(2021mt70083) Checks if a book is available with a
	 *         given ID
	 */
	public boolean isBookAvailable(int bookId) {
		String book = findBookById(bookId);
		String[] bookDetails = dbUtils.getColumnsFromRow(bookId, book);
		if (bookDetails[5].equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Author Jen Thomas James(2021mt70083) Marks a book as unavailable when it is
	 *         borrowed by a student
	 */
	public void changeBookStatus(int bookId, String status) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(bookDbPath));
			File file = new File("src/store/bookTemp.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(bookId, row);
				if (dbUtils.isRowMatchingColumnId(bookId, Integer.parseInt(columns[0]))) {
					String updatedRow = columns[0] + "::" + columns[1] + "::" + columns[2] + "::" + columns[3] + "::"
							+ columns[4] + "::" + status + "::"
							+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MM yyyy")) + "\n";
					writer.append(updatedRow);
				} else {
					writer.append(row + "\n");
				}
			}
			writer.close();
			reader.close();
			File previousFile = new File(bookDbPath);
			previousFile.delete();
			file.renameTo(previousFile);
		} catch (Exception ex) {
			System.out.println("Exception occured while getting borrow details");
		}
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Method to renew a book
	 *
	 */
	public void renewBook(int bookId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(bookDbPath));
			File file = new File("src/store/bookTemp.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(bookId, row);
				if (dbUtils.isRowMatchingColumnId(bookId, Integer.parseInt(columns[0]))) {
					String updatedRow = columns[0] + "::" + columns[1] + "::" + columns[2] + "::" + columns[3] + "::"
							+ columns[4] + "::false::"
							+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MM yyyy")) + "\n";
					writer.append(updatedRow);
				} else {
					writer.append(row + "\n");
				}
			}
			writer.close();
			reader.close();
			File previousFile = new File(bookDbPath);
			previousFile.delete();
			file.renameTo(previousFile);
		} catch (Exception ex) {
			System.out.println("Exception occured while getting borrow details");
		}
	}

}
