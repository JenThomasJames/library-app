package entity;

public class ResearchJournal {
	private long journalId;
	private String title;
	private String author;

	public ResearchJournal() {
		super();
	}

	public ResearchJournal(long journalId, String title, String author) {
		super();
		this.journalId = journalId;
		this.title = title;
		this.author = author;
	}

	public long getJournalId() {
		return journalId;
	}

	public void setJournalId(long journalId) {
		this.journalId = journalId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
