package service;

import entity.Student;
import store.StudentStore;

public class StudentService {

	StudentStore studentStore = new StudentStore();

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Adds a new student to the memory
	 */
	public void addStudent(Student student) {
		studentStore.addStudent(student);
	}
}
