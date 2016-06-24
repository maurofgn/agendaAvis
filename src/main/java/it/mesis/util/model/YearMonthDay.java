package it.mesis.util.model;


import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class YearMonthDay extends YearMonth {

	private static final int FIRST_DAY_OF_WEEK = GregorianCalendar.getInstance(Locale.ITALY).getFirstDayOfWeek();
	
	public static final YearMonthDay VOID = new YearMonthDay(null);

	private YearMonth yearMonth;
	private int day;
	private int weekNr;		//nr settimana del mese 0 based
	private int weekDay;	//nr giorno della settimana ==> lun=0, Mar=1, Mer=2, ... Dom=6
	
	public YearMonthDay(Date date) {
		this(new Timestamp(date.getTime()));
	}

	public YearMonthDay(Timestamp dataorapren) {
		super(dataorapren);
		
		if (dataorapren != null) {
			GregorianCalendar gc = new GregorianCalendar(Locale.ITALY);
			
			gc.setTime(dataorapren);
			day = gc.get(GregorianCalendar.DAY_OF_MONTH);
			weekNr = gc.get(GregorianCalendar.WEEK_OF_MONTH);	//nr settimana del mese 0 based
			weekDay = gc.get(GregorianCalendar.DAY_OF_WEEK);	//nr giorno della settimana ==> Dom=1, lun=2, Mar=3, Mer=4, Gio=5, Ven=6, Sab=7
			weekDay = weekDay - FIRST_DAY_OF_WEEK;				//nr giorno della settimana ==> lun=0, Mar=1, Mer=2, Gio=3, Ven=4, Sab=5, Dom=6
			if (weekDay < 0)
				weekDay = 6;
			
			yearMonth = new YearMonth(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH));
		}
	}
	
	public YearMonthDay(int year, int month, int day) {
		this(new Timestamp(new GregorianCalendar(year, month, day).getTimeInMillis()));
	}

	public int getDay() {
		return day;
	}
	
	public YearMonth getYearMonth() {
		return yearMonth;
	}
	
	public int getWeekNr() {
		return weekNr;
	}

	public int getWeekDay() {
		return weekDay;
	}
	
	public boolean isValid() {
		return day > 0;
	}

	@Override
	public int compareTo(YearMonth o) {
		
		int c = super.compareTo(o);
		if (c != 0)
			return c;
		
		if (o instanceof YearMonthDay) {
			YearMonthDay d = (YearMonthDay) o;
			if (day > d.getDay())
				return 1;
			else if (day < d.getDay())
				return -1;
		}
		
		return 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + day;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		YearMonthDay other = (YearMonthDay) obj;
		if (day != other.day)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return isValid() ? String.format("%02d/%02d/%04d", day, getMonth()+1, getYear()) : "--";
	}
	
}
