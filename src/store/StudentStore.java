package store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import entity.Student;

public class StudentStore {
	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Adds a student to memory
	 */
	public void addStudent(Student student) {
		try {
			File file = new File("src/store/studentdb.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(student.getStudentId() + "::" + student.getFirstName() + "::" + student.getLastName() + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add student. Try again later.");
		}
	}
}
