package util;

import java.util.Calendar;
import java.util.Date;

public class IsToday {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();

		// set the calendar to start of today
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		// and get that as a Date
		Date today = c.getTime();

		// or as a timestamp in milliseconds
		long todayInMillis = c.getTimeInMillis();

		// user-specified date which you are testing
		// let's say the components come from a form or something
		int year = Calendar.getInstance().YEAR;
		int month = Calendar.getInstance().MONTH;
		int dayOfMonth = Calendar.getInstance().DAY_OF_MONTH;
System.out.println(year + " " + month + " " + dayOfMonth);
		// reuse the calendar to set user specified date
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

		// and get that as a Date
		Date dateSpecified = c.getTime();

		// test your condition
	
		if (dateSpecified.before(today)) {
		  System.out.println("Date specified [" + dateSpecified + "] is before today [" + today + "]");
		} else {
		  System.out.println("Date specified [" + dateSpecified + "] is NOT before today [" + today + "]");
		}
	}
}
