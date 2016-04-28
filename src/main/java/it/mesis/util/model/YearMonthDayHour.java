package it.mesis.util.model;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class YearMonthDayHour extends YearMonth {

	private int day, hour, minute;
	private YearMonth yearMonth;
	
	public static YearMonthDayHour from(Timestamp dataorapren) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataorapren);
		return new YearMonthDayHour(gc.get(GregorianCalendar.YEAR),
				gc.get(GregorianCalendar.MONTH),
				gc.get(GregorianCalendar.DAY_OF_MONTH),
				gc.get(GregorianCalendar.HOUR_OF_DAY),
				gc.get(GregorianCalendar.MINUTE));
	}

	public YearMonthDayHour(int year, int month, int day, int hour, int minute) {
		super(year, month);
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		yearMonth = new YearMonth(year, month);
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
	
	public YearMonth getYearMonth() {
		return yearMonth;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + day;
		result = prime * result + hour;
		result = prime * result + minute;
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
		YearMonthDayHour other = (YearMonthDayHour) obj;
		if (day != other.day)
			return false;
		if (hour != other.hour)
			return false;
		if (minute != other.minute)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "/" + day + " " + hour + ":" + minute;
	}
	
	@Override
	public int compareTo(YearMonth o) {
		
		int c = super.compareTo(o);
		if (c != 0)
			return c;
		
		if (o instanceof YearMonthDayHour) {
			YearMonthDayHour d = (YearMonthDayHour) o;
			if (day > d.getDay())
				return 1;
			else if (day < d.getDay())
				return -1;
			
			if (hour > d.getHour())
				return 1;
			else if (hour < d.getHour())
				return -1;
	
			if (minute > d.getMinute())
				return 1;
			else if (minute < d.getMinute())
				return -1;
		}
		
		return 0;
	}

}
