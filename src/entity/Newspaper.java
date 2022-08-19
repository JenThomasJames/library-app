package entity;

public class Newspaper {
	private long newspaperId;
	private String tilte;
	private String pages;

	public Newspaper() {
		super();
	}

	public Newspaper(long newspaperId, String tilte, String pages) {
		super();
		this.newspaperId = newspaperId;
		this.tilte = tilte;
		this.pages = pages;
	}

	public long getNewspaperId() {
		return newspaperId;
	}

	public void setNewspaperId(long newspaperId) {
		this.newspaperId = newspaperId;
	}

	public String getTilte() {
		return tilte;
	}

	public void setTilte(String tilte) {
		this.tilte = tilte;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

}
