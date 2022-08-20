package entity;

/**
 * @author JEN THOMAS JAMES (2021MT70083)
 *
 */
public class Student {
	private long studentId;
	private String firstName;
	private String lastName;
	private String programme;
	private int currentYear;

	public Student() {
	}

	public Student(long studentId, String firstName, String lastName, String programme, int currentYear) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.programme = programme;
		this.currentYear = currentYear;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public int getCurrentYear() {
		return this.currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

}
