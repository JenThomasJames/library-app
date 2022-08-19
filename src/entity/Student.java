package entity;

/**
 * @author JEN THOMAS JAMES (2021MT70083)
 *
 */
public class Student {
	private long studentId;
	private String firstName;
	private String lastName;
	private long batchId;

	public Student() {
	}

	public Student(long studentId, String firstName, String lastName, long batchId) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.batchId = batchId;
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

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

}
