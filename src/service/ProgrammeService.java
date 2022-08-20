package service;

import java.util.Scanner;

import entity.Programme;
import store.ProgrammeStore;

public class ProgrammeService {

	ProgrammeStore programmeStore = new ProgrammeStore();

	/**
	 * @Author Jen Thomas James(2021mt70083) Adds a new programme to the memory
	 */
	public void addProgramme(Programme programme) {
		programmeStore.addProgramme(programme);
	}

	/**
	 * @Author Jen Thomas James Creates a new batch object with the collected
	 *         details
	 */
	public Programme createProgramme(Scanner scan) {
		System.out.println("What is the Programme ID?: ");
		int programmeId = scan.nextInt();
		scan.nextLine();
		System.out.println("What is the name of the programme? : ");
		String title = scan.nextLine();
		System.out.println("How long is the programme?: ");
		int numberOfYears = scan.nextInt();
		System.out.println("How many specializations does this programme have?: ");
		int numberOfSpecializations = scan.nextInt();
		scan.nextLine();
		String specializations = getSpecializations(numberOfSpecializations, scan);
		Programme programme = new Programme(programmeId, title, numberOfYears, 60, specializations);
		return programme;
	}

	/**
	 * @Author Jen Thomas James Gets the specializations for the given programme
	 */
	private String getSpecializations(int numberOfSpecializations, Scanner scan) {
		String specializations = "[";
		for (int i = 0; i < numberOfSpecializations; i++) {
			System.out.println("Name of specialization " + (i + 1));
			String specialization = scan.nextLine();
			specializations += "" + specialization;
			if (i != numberOfSpecializations - 1) {
				specializations += ", ";
			}
		}
		specializations += "]";
		return specializations;
	}

}
