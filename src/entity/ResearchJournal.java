package entity;

/**
 * @author JEN THOMAS JAMES (2021MT70083)
 *
 */
public class ResearchJournal extends Book {

	public ResearchJournal() {
	}

	public ResearchJournal(int journalId, String title, String author, int numberOfPages, boolean isAvailable) {
		this.bookId = journalId;
		this.title = title;
		this.author = author;
		this.pages = numberOfPages;
		this.isAvailable = isAvailable;
	}
}
