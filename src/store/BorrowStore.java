package store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import shared.DbUtils;

/**
 * @author JEN THOMAS JAMES (2021MT70083)
 *
 */
/**
 * @author JEN THOMAS JAMES (2021MT70083)
 *
 */
public class BorrowStore {
	String borrowDbPath = "src/store/borrowdb.txt";
	BookStore bookStore = new BookStore();
	StudentStore studentStore = new StudentStore();
	DbUtils dbUtils = new DbUtils();

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Gives the book to a particular student
	 *         as borrow
	 *
	 */
	public void borrowBook(int studentId, int bookId) {
		// check if the book is available to borrow
		if (bookStore.isBookAvailable(bookId)) {
			// check if the user is already having books with him and he can borrow more
			// books
			if (isUserPresentInBorrowList(studentId)) {
				// if he can borrow more books, add the book to his collection
				if (canStudentBorrowMore(studentId)) {
					addBookToStudentCollection(studentId, bookId);
				} else {
					System.out.println("Your book quota exceeded. Return some books with you to borrow more.");
				}
			} else {
				addNewEntryForStudent(studentId);
				addBookToStudentCollection(studentId, bookId);
			}
			// update the book as unavailable
			bookStore.changeBookStatus(bookId, "false");
		} else {
			System.out.println("The book is not available at the moment. Please check again later!");
		}
	}

	/**
	 * @Author Jen Thomas James (2021mt70083) Adds a new entry for a student in the
	 *         borrow db
	 */
	private void addNewEntryForStudent(int studentId) {
		try {
			File file = new File(borrowDbPath);
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(studentId + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add the student's entry. Try again later.");
		}
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Checks if the student can borrow more
	 *         books
	 *
	 */
	private boolean canStudentBorrowMore(int studentId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(borrowDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(studentId, row);
				if (dbUtils.isRowMatchingColumnId(studentId, Integer.parseInt(columns[0]))) {
					int numberOfBooksBorrowed = columns.length;
					String student = studentStore.findStudentById(Integer.parseInt(columns[0]));
					String[] studentData = dbUtils.getColumnsFromRow(studentStore.numberOfColumns, student);
					// batch is Msc Integrated
					if (studentData[3].charAt(0) == 'M') {
						// Not in final year. So, student can only have 6 books in custody at a time
						if (Integer.parseInt(studentData[4]) != 5) {
							if (numberOfBooksBorrowed < 7) {
								reader.close();
								return true;
							} else {
								reader.close();
								return false;
							}
						}
						// Student is in Final year. thus, he can borrow 12 books out of which only 9
						// can be text books
						else {
							if (numberOfBooksBorrowed < 13) {
								// TODO: check if more textbooks can be borrowed
								reader.close();
								return true;
							} else {
								reader.close();
								return false;
							}
						}
					}
					// Batch is Btech
					else {
						// Not in final year. So, student can only have 6 books in custody at a time
						if (Integer.parseInt(studentData[4]) != 4) {
							if (numberOfBooksBorrowed < 7) {
								reader.close();
								return true;
							} else {
								reader.close();
								return false;
							}
						}
						// Student is in Final year. thus, he can borrow 12 books out of which only 9
						// can be text books
						else {
							if (numberOfBooksBorrowed < 13) {
								// TODO: check if more textbooks can be borrowed
								reader.close();
								return true;
							} else {
								reader.close();
								return false;
							}
						}
					}
				}
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Exception occured while getting student details");
		}
		return false;
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Makes the book available for borrowing
	 *
	 */
	public void returnBook(long userId, long bookId) {
		// find the user's collection
		// remove the book from his collection
		// mark the book as available
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Checks if the user has some books in
	 *         his custody
	 *
	 */
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

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Adds a book to a student's custody
	 *
	 */
	public void addBookToStudentCollection(int studentId, int bookId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(borrowDbPath));
			File file = new File("src/store/borrowTemp.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(studentId, row);
				if (dbUtils.isRowMatchingColumnId(studentId, Integer.parseInt(columns[0]))) {
					writer.append(row + "::" + bookId + "\n");
				} else {
					writer.append(row + "\n");
				}
			}
			writer.close();
			reader.close();
			File previousFile = new File(borrowDbPath);
			previousFile.delete();
			file.renameTo(previousFile);
		} catch (Exception ex) {
			System.out.println("Exception occured while getting borrow details");
		}
	}

}
