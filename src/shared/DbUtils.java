package shared;

public class DbUtils {

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Returns all the columns from a given
	 *         row
	 *
	 */
	public String[] getColumnsFromRow(int numberOfColumns, String row) {
		String[] columns = new String[numberOfColumns];
		columns = row.split("::");
		return columns;
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Displays all the data from columns,
	 *         seperated by a blank space
	 *
	 */
	public void displayAllColumns(int numberOfColumns, String[] columns) {
		for (int i = 0; i < numberOfColumns; i++) {
			System.out.print(columns[i] + " ");
		}
		System.out.println();
		System.out.println();
	}

	/**
	 * @author JEN THOMAS JAMES (2021MT70083) Dertermines whether a row matches the
	 *         given column ID
	 *
	 */
	public boolean isRowMatchingColumnId(long prefferdId, long currentId) {
		if (prefferdId == currentId) {
			return true;
		}
		return false;
	}
}
