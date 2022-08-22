package store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import entity.Student;
import shared.DbUtils;

public class StudentStore {

	String studentDbPath = "src/store/studentdb.txt";
	int numberOfColumns = 6;
	DbUtils dbUtils = new DbUtils();

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Adds a student to memory
	 */
	public void addStudent(Student student) {
		try {
			File file = new File(studentDbPath);
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(student.getStudentId() + "::" + student.getFirstName() + "::" + student.getLastName() + "::"
					+ student.getProgramme() + "::" + student.getCurrentYear() + "::"
					+ student.getReservedNewsPapers());
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add student. Try again later.");
		}
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Fetches all the students from memory
	 */
	public void getAllStudents() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(studentDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(numberOfColumns, row);
				dbUtils.displayAllColumns(numberOfColumns, columns);
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Can't fetch the student details at the moment. Please try again after some time.");
		}
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Fetches a student by ID from memory
	 */
	public String findStudentById(int id) {
		boolean isFound = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(studentDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(id, row);
				if (dbUtils.isRowMatchingColumnId(id, Integer.parseInt(columns[0]))) {
					isFound = true;
					reader.close();
					return row;
				}
			}
			if (!isFound) {
				System.out.println("No student found with the given Id!");
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Exception occured while getting student details");
		}
		return null;
	}

	/**
	 * @Author Jen Thomas James (2021mt70083) checks if a student can reserve
	 *         newspaper
	 */
	public boolean canReserveNewspaper(int studentId) {
		boolean canReserve = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(studentDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(studentId, row);
				if (dbUtils.isRowMatchingColumnId(studentId, Integer.parseInt(columns[0]))) {
					if (Integer.parseInt(columns[5]) < 2) {
						canReserve = true;
						break;
					} else {
						canReserve = false;
						break;
					}
				}
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Exception occured while getting borrow details");
		}
		return canReserve;
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Method to reserve a newspaper
	 *
	 */
	public void reserveNewspaper(int studentId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(studentDbPath));
			File file = new File("src/store/studentTemp.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(studentId, row);
				if (dbUtils.isRowMatchingColumnId(studentId, Integer.parseInt(columns[0]))) {
					String updatedRow = columns[0] + "::" + columns[1] + "::" + columns[2] + "::" + columns[3] + "::"
							+ columns[4] + "::" + (Integer.parseInt(columns[5]) + 1 + "\n");
					writer.append(updatedRow);
				} else {
					writer.append(row + "\n");
				}
			}
			writer.close();
			reader.close();
			File previousFile = new File(studentDbPath);
			previousFile.delete();
			file.renameTo(previousFile);
		} catch (Exception ex) {
			System.out.println("Exception occured while getting borrow details");
		}
	}
}
