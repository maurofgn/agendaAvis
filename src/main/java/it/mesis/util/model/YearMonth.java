package it.mesis.util.model;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class YearMonth implements Comparable<YearMonth> {
	
	public static final YearMonth VOID = new YearMonth(null);
	
	private static final String[] MONTH_NAMES = {"Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "Ott", "Nov", "Dic"};
	
	private int year;
	private int month;
	
	public YearMonth(Timestamp dataorapren) {
		if (dataorapren != null) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(dataorapren.getTime());
			year = gc.get(GregorianCalendar.YEAR);
			month = gc.get(GregorianCalendar.MONTH);	//zero based
		}
	}

	public YearMonth(int year, int month) {
		super();
		this.year = year;
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		YearMonth other = (YearMonth) obj;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	public String getValToString() {
		return year + "," + month;
	}
	
	

	@Override
	public String toString() {
		return MONTH_NAMES[month] + "/" + year;
	}

	@Override
	public int compareTo(YearMonth o) {
		if (year > o.getYear())
			return 1;
		else if (year < o.getYear())
			return -1;
		
		if (month > o.getMonth())
			return 1;
		else if (month < o.getMonth())
			return -1;
		
		return 0;
	}

	public YearMonth prev() {
		return month > 0 ? new YearMonth(year, month-1) : new YearMonth(year-1,11);
	}
	
	public YearMonth next() {
		return month < 11 ? new YearMonth(year, month+1) : new YearMonth(year+1,0);
	}


}
