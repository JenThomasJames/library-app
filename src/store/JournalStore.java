package store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entity.ResearchJournal;
import shared.DbUtils;

public class JournalStore {
	String journalDbPath = "src/store/journaldb.txt";
	String journalBorrowDbPath = "src/store/journalborrowdb.txt";
	DbUtils dbUtils = new DbUtils();

	/**
	 * @Author Jen Thomas James (2021mt70083) Adds a journal to memory
	 */
	public void addJournal(ResearchJournal journal) {
		try {
			File file = new File(journalDbPath);
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(journal.getBookId() + "::" + journal.getTitle() + "::" + journal.getAuthor() + "::"
					+ journal.getPages() + "::" + journal.isAvailable() + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add journal. Try again later.");
		}
	}

	/**
	 * @Author Jen Thomas James (2021mt70083) Method to borrow a journal
	 */
	public void borrowJournal(int studentId, int journalId) {
		// check if the journal is available to borrow
		if (isJournalAvailable(journalId)) {
			// check if the user is already having journals with him and he can borrow more
			// journals
			if (isUserPresentInBorrowList(studentId)) {
				// if he can borrow more journals, add the journal to his collection
				if (canStudentBorrowMore(studentId)) {
					addJournalToStudentCollection(studentId, journalId);
				} else {
					System.out.println("Your book quota exceeded. Return some books with you to borrow more.");
					return;
				}
			} else {
				addNewEntryForStudent(studentId);
				addJournalToStudentCollection(studentId, journalId);
			}
			// update the journal as unavailable
			changeJournalStatus(journalId, "false");
		} else {
			System.out.println("The book is not available at the moment. Please check again later!");
		}
	}

	/**
	 * @Author Jen Thomas James (2021mt70083) Changes the availability of the
	 *         journal
	 */
	private void changeJournalStatus(int journalId, String status) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(journalDbPath));
			File file = new File("src/store/bookTemp.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(journalId, row);
				if (dbUtils.isRowMatchingColumnId(journalId, Integer.parseInt(columns[0]))) {
					String updatedRow = columns[0] + "::" + columns[1] + "::" + columns[2] + "::" + columns[3] + "::"
							+ status + "::" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MM yyyy"))
							+ "\n";
					writer.append(updatedRow);
				} else {
					writer.append(row + "\n");
				}
			}
			writer.close();
			reader.close();
			File previousFile = new File(journalDbPath);
			previousFile.delete();
			file.renameTo(previousFile);
		} catch (Exception ex) {
			System.out.println("Exception occured while getting borrow details");
		}
	}

	/**
	 * @Author Jen Thomas James (2021mt70083) Lends the journal to the student
	 */
	private void addJournalToStudentCollection(int studentId, int journalId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(journalBorrowDbPath));
			File file = new File("src/store/journalBorrowTemp.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(studentId, row);
				if (dbUtils.isRowMatchingColumnId(studentId, Integer.parseInt(columns[0]))) {
					writer.append(row + "::" + journalId + "\n");
				} else {
					writer.append(row + "\n");
				}
			}
			writer.close();
			reader.close();
			File previousFile = new File(journalBorrowDbPath);
			previousFile.delete();
			file.renameTo(previousFile);
		} catch (Exception ex) {
			System.out.println("Exception occured while getting borrow details");
		}
	}

	/**
	 * @Author Jen Thomas James (2021mt70083) Checks if the user can borrow more
	 */
	private boolean canStudentBorrowMore(int studentId) {
		if (isUserPresentInBorrowList(studentId)) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(journalBorrowDbPath));
				String row = "";
				while ((row = reader.readLine()) != null) {
					String[] columns = dbUtils.getColumnsFromRow(studentId, row);
					if (dbUtils.isRowMatchingColumnId(studentId, Integer.parseInt(columns[0]))) {
						int numberOfBooksBorrowed = columns.length - 1;
						if (numberOfBooksBorrowed == 1) {
							reader.close();
							return false;
						} else {
							reader.close();
							return true;
						}
					}
				}
				reader.close();
			} catch (Exception ex) {
				System.out.println("Exception occured while getting student details");
			}
		}
		return false;
	}

	/**
	 * @Author Jen Thomas James (2021mt70083) Checks if the journal is available to
	 *         borrow
	 */
	private boolean isJournalAvailable(int journalId) {
		String journal = findJournalById(journalId);
		String[] bookDetails = dbUtils.getColumnsFromRow(journalId, journal);
		if (bookDetails[4].equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Author Jen Thomas James (2021mt70083) Fetches the journal by id from memory
	 */
	private String findJournalById(int journalId) {
		boolean isFound = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(journalDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(journalId, row);
				if (dbUtils.isRowMatchingColumnId(journalId, Integer.parseInt(columns[0]))) {
					isFound = true;
					reader.close();
					return row;
				}
			}
			if (!isFound) {
				System.out.println("No journal found with the given Id!");
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Exception occured while getting Journal details");
		}
		return null;
	}

	/**
	 * @Autor Jen Thomas James(2021mt70083) checks if the user is present in borrow
	 *        list
	 */
	public boolean isUserPresentInBorrowList(int userId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(journalBorrowDbPath));
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
	 * @Author Jen Thomas James (2021mt70083) Adds a new entry for a student in the
	 *         borrow db
	 */
	private void addNewEntryForStudent(int studentId) {
		try {
			File file = new File(journalBorrowDbPath);
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(studentId + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add the student's entry. Try again later.");
		}
	}
}
