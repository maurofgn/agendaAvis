package it.mesis.util.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hour {
	
	private Date date;
	private int available;
	private int total;
	private YearMonthDay yeaMonthDay;
	
	private static final SimpleDateFormat HH_MM = new SimpleDateFormat("HH:mm");
	private static final SimpleDateFormat DATA_FORMAT_LONG = new SimpleDateFormat("E dd/MM/yyyy");

	public Hour(Date date, int available, int total) {
		super();
		this.date = date;
		this.available = available;
		this.total = total;
		yeaMonthDay = new YearMonthDay(date);
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	public boolean hasFree() {
		return available > 0;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getHourMinutes() {
		return HH_MM.format(date);
	}
	
	public String getDayLong() {
		return DATA_FORMAT_LONG.format(date);
	}
	
	public String getLinkPreno() {
		return hasFree() ? "<a href=\"/agendaAvis/preno-" + getDayNr() + "-dd-" + getHourMinutes() + "-hh\">" + getHourMinutes() + "</a>" : getHourMinutes();
	}
	
	public String getState() {
		return available + "/" + total;
	}

	
	public int getDayNr() {
		return yeaMonthDay.getDay();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Hour other = (Hour) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hour [date=" + date + ", available=" + available + ", total=" + total + ", yeaMonthDay=" + yeaMonthDay + " hh=" +getHourMinutes() + "]";
	}

}
