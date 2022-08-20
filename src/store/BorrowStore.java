package store;

import java.io.BufferedReader;
import java.io.FileReader;

import shared.DbUtils;

public class BorrowStore {
	String borrowDbPath = "src/store/borrowdb.txt";
	BookStore bookStore = new BookStore();
	StudentStore studentStore = new StudentStore();
	DbUtils dbUtils = new DbUtils();

	public void borrowBook(int studentId, int bookId) {
		bookStore.isBookAvailable(bookId);
		// check if the user is already having books with him and he can borrow more
		// books
		if (isUserPresentInBorrowList(studentId)) {
			if (canStudentBorrowMore(studentId)) {

			}
		}
		// if he can borrow more books, add the book to his collection
		// update the book as unavailable
	}

	// TODO: Add logic to check if a user can borrow more books
	private boolean canStudentBorrowMore(int studentId) {
		return false;
	}

	public void returnBook(long userId, long bookId) {
		// find the user's collection
		// remove the book from his collection
		// mark the book as available
	}

	public boolean isUserPresentInBorrowList(int userId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(borrowDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(userId, row);
				if (dbUtils.isRowMatchingColumnId(userId, Integer.parseInt(columns[0]))) {
					reader.close();
					return true;
				}
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Exception occured while getting student details");
		}
		return false;
	}
}
