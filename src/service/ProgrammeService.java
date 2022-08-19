package service;

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

}
