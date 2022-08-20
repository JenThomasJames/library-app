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
	int numberOfColumns = 3;
	DbUtils dbUtils = new DbUtils();

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Adds a student to memory
	 */
	public void addStudent(Student student) {
		try {
			File file = new File(studentDbPath);
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(student.getStudentId() + "::" + student.getFirstName() + "::" + student.getLastName() + "\n");
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
	public void findStudentById(int id) {
		boolean isFound = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(studentDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnsFromRow(id, row);
				if (dbUtils.isRowMatchingColumnId(id, Integer.parseInt(columns[0]))) {
					dbUtils.displayAllColumns(numberOfColumns, columns);
					isFound = true;
					reader.close();
					return;
				}
			}
			if (!isFound) {
				System.out.println("No student found with the given Id!");
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("No student with the fiven ID found.");
		}
	}
}
