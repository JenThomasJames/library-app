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

	public void getAllStudents() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(studentDbPath));
			String row = "";
			while ((row = reader.readLine()) != null) {
				String[] columns = dbUtils.getColumnFromRow(numberOfColumns, row);
				dbUtils.displayAllColumns(numberOfColumns, columns);
			}
			reader.close();
		} catch (Exception ex) {
			System.out.println("Can't fetch the student details at the moment. Please try again after some time.");
		}
	}
}
