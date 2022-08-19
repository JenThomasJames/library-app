package entity;

/**
 * @author JEN THOMAS JAMES (2021MT70083)
 *
 */
public class Programme {
	private long programmeId;
	private String title;
	private int numberOfYears;
	private int totalStrength;

	public Programme() {
		super();
	}

	public Programme(long batchId, String title, int numberOfYears, int totalStrength) {
		super();
		this.programmeId = batchId;
		this.title = title;
		this.numberOfYears = numberOfYears;
		this.totalStrength = totalStrength;
	}

	public long getBatchId() {
		return programmeId;
	}

	public void setBatchId(long batchId) {
		this.programmeId = batchId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumberOfYears() {
		return numberOfYears;
	}

	public void setNumberOfYears(int numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	public int getTotalStrength() {
		return totalStrength;
	}

	public void setTotalStrength(int totalStrength) {
		this.totalStrength = totalStrength;
	}

}
