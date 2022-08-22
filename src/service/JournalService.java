package service;

import java.util.Scanner;

import entity.ResearchJournal;
import store.JournalStore;

public class JournalService {

	private JournalStore journalstore = new JournalStore();

	/**
	 * @Author Jen Thomas James (2021mt70083) Adds a new Journal to memory
	 */
	public void addJournal(ResearchJournal journal) {
		journalstore.addJournal(journal);
	}

	/**
	 * @Author Jen Thomas James (2021mt7083) creates a new Journal object
	 */
	public ResearchJournal createNewResearchJournal(Scanner scan) {
		System.out.println("Enter Research Journal ID: ");
		int journalId = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter title: ");
		String title = scan.nextLine();
		System.out.println("Enter author: ");
		String author = scan.nextLine();
		System.out.println("Enter number of pages: ");
		int numberOfPages = scan.nextInt();
		ResearchJournal journal = new ResearchJournal(journalId, title, author, numberOfPages, true);
		return journal;
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Gets the journal's Id from the user
	 *
	 */
	public int getJournalId(Scanner scan) {
		System.out.print("Enter the Journal ID: ");
		int journalId = scan.nextInt();
		return journalId;
	}

}
