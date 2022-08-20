package entity;

/**
 * @author JEN THOMAS JAMES (2021MT70083)
 *
 */
/**
 * @author JEN THOMAS JAMES (2021MT70083)
 *
 */
public class Book {
	private long bookId;
	private String title;
	private String author;
	private int pages;
	private String bookType;

	public Book() {
		super();
	}

	public Book(long bookId, String title, String author, int pages, String bookType) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.bookType = bookType;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
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

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
}
