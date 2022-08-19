package store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import entity.Programme;

public class ProgrammeStore {

	public void addProgramme(Programme programme) {
		try {
			File file = new File("src/store/programmedb.txt");
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(programme.getProgrammeId() + "::" + programme.getTitle() + "::" + programme.getNumberOfYears()
					+ programme.getTotalStrength() + "::" + programme.getSpecialization() + "\n");
			writer.close();

		} catch (Exception ex) {
			System.out.println("Couldn't add student. Try again later.");
		}
	}

}
