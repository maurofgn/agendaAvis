package it.mesis.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public class Calendar {

	private static Hashtable<String, ArrayList<String>> years;

	/**
	 * @param date
	 * @return true when is holiday
	 */
	public static boolean isHoliday(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		return isHoliday(gc);
	}
	
	/**
	 * 
	 * @param gc
	 * @return true when is working
	 */
	public static boolean isWorking(GregorianCalendar gc) {
		return !isHoliday(gc);
	}
	
	/**
	 * 
	 * @param gc
	 * @return true when is holiday
	 */
	public static boolean isHoliday(GregorianCalendar gc) {
		if (gc.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY)
			return true;
		int month = gc.get(GregorianCalendar.MONTH);
		int day = gc.get(GregorianCalendar.DAY_OF_MONTH);
		ArrayList<String> holiday = getHoliday(gc.get(GregorianCalendar.YEAR));
		return holiday.contains(monthDay(month, day));
	}
	
	/**
	 * 
	 * @param month
	 * @param day
	 * @return true if is holiday
	 */
	public static ArrayList<String> getHoliday(int year) {
		String key = "" + year;
		if (years == null)
			years = new Hashtable<String, ArrayList<String>>();
		if (years.contains(key))
			return (ArrayList<String>)years.get(key);
		
		ArrayList<String> holiday = new ArrayList<String>();
		GregorianCalendar easterSunday = getEasterSunday(year);
		GregorianCalendar mondayEasterSunday = new GregorianCalendar();
		mondayEasterSunday.setTime(easterSunday.getTime());
		mondayEasterSunday.add(GregorianCalendar.DAY_OF_YEAR, 1);
		holiday.add(monthDay(easterSunday));
		holiday.add(monthDay(mondayEasterSunday));
		holiday.add(monthDay(0, 1));
		holiday.add(monthDay(0, 6));
		holiday.add(monthDay(3, 25));
		holiday.add(monthDay(4, 1)); // 1 maggio
		holiday.add(monthDay(5, 2)); // 2 giugno
		holiday.add(monthDay(7, 15));
		holiday.add(monthDay(10, 1));
		holiday.add(monthDay(11, 8));
		holiday.add(monthDay(11, 25)); // Natale
		holiday.add(monthDay(11, 26)); // S.Stefano
		
		years.put(key, holiday);
		return holiday;
	}

	private static synchronized String monthDay(int month, int day) {
		return "M" + month + "D" + day;
	}

	private static synchronized String monthDay(GregorianCalendar day) {
		return monthDay(day.get(GregorianCalendar.MONTH), day.get(GregorianCalendar.DAY_OF_MONTH));
	}
	
	/**
	 * EasterSunday day calc
	 * 
	 * @param year
	 * @return EasterSunday day
	 */
	public static synchronized GregorianCalendar getEasterSunday(int year) {
		int correction = 0;
		if (year < 1700) correction = 4;
		else if (year < 1800) correction = 5;
		else if (year < 1900) correction = 6;
		else if (year < 2100) correction = 0;
		else if (year < 2200) correction = 1;
		else if (year < 2300) correction = 2;
		else if (year < 2500) correction = 3;

		int day = (19 * (year % 19) + 24) % 30;
		day = 22 + day + ((2 * (year % 4) + 4 * (year % 7) + 6 * day + 5 + correction) % 7);

		int month = 3;
		if (day > 31) {
			month = 4;
			day = day - 31;
		} else {
			month = 3;
		}

		return new GregorianCalendar(year, month - 1, day);
	}
}
