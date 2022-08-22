package shared;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CommonUtils {

	/**
	 * @Author Jen Thomas James Converts Date to LocalDate
	 */
	public LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * @Author Jen Thomas James Converts LocalDate to Date
	 */
	public Date convertLocalDateToDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}
}
