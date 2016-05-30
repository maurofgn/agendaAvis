package it.mesis.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFromTo {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	Date dateFrom;
	Date dateTo;
	
	public DateFromTo(Date dateFrom, Date dateTo) {
		super();
		
		if (dateFrom == null) {
			Calendar gc = TimeUtil.getToday();	//oggi con ore, min, sec e millis = 0
			if (dateTo == null)
				dateFrom = gc.getTime();
			else if (dateTo.after(gc.getTime()))
				dateFrom = gc.getTime();
			else
				dateFrom = dateTo;
		}
		
		if (dateTo == null)
			dateTo = dateFrom;
		
		this.dateFrom = TimeUtil.getMinHour(dateFrom);
		this.dateTo = TimeUtil.getMaxHour(dateTo);
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public String toString() {
		return "dateFrom=" + sdf.format(dateFrom) + ", dateTo=" + sdf.format(dateTo);
	}
	
}
