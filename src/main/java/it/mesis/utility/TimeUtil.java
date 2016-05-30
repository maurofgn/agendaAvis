/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package it.mesis.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 *	Time Utilities
 *
 */
public class TimeUtil
{
	
	/** 2090-10-13 00:00:00.0 */
	public static final long MAX_DATE = 3811528800000l;
	/** 2090-10-13 00:00:00.0 */
	public static final Timestamp MAX_DATE_TS = new Timestamp(MAX_DATE);
	
	/** 1960-10-13 00:00:00.0 */
	public static final long MIN_DATE = -290912400000l;
	/** 1960-10-13 00:00:00.0 */
	public static final Timestamp MIN_DATE_TS = new Timestamp(MIN_DATE);
	
	public static final long MILLISECONDS_PER_SECOND = 1000;
	public static final long MILLISECONDS_PER_MINUTE = MILLISECONDS_PER_SECOND * 60;
	public static final long MILLISECONDS_PER_HOUR = MILLISECONDS_PER_MINUTE * 60;
	public static final long MILLISECONDS_PER_DAY = MILLISECONDS_PER_HOUR * 24;
	public static final long MILLISECONDS_PER_WEEK = MILLISECONDS_PER_DAY * 7;
	
	public static final SimpleDateFormat DATE_JDBC = new SimpleDateFormat("{'d' ''yyyy-MM-dd''}");
	public static final SimpleDateFormat TIME_JDBC = new SimpleDateFormat("{'ts' ''yyyy-MM-dd HH:mm:ss''}");
	
	public static final String[] MONTHS_NAME = {
		"Gennaio",
		"Febbraio",
		"Marzo",
		"Aprile",
		"Maggio",
		"Giugno",
		"Luglio",
		"Agosto",
		"Settembre",
		"Ottobre",
		"Novembre",
		"Dicembre"
	};
	

	/**
	 * 
	 * @param date
	 * @return la stessa data con le ore, minuti, secondi e millesecondi minimi
	 *         (0)
	 */
	public static Date getMinHour(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		setMinHour(gc);
//		gc.set(Calendar.HOUR_OF_DAY, 0);
//		gc.set(Calendar.MINUTE, 0);
//		gc.set(Calendar.SECOND, 0);
//		gc.set(Calendar.MILLISECOND, 0);
		return gc.getTime();
	}
	
	public static void setMinHour(Calendar gc) {
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
	}
	
	public static void setMaxHour(Calendar gc) {
		setMinHour(gc);
		gc.add(GregorianCalendar.DAY_OF_YEAR, 1);
		gc.add(GregorianCalendar.MILLISECOND, -1);
	}

	/**
	 * 
	 * @param date
	 * @return la stessa data con le ore, minuti, secondi e millesecondi massimi 23:59:59 999
	 */
	public static Date getMaxHour(Date date) {
		GregorianCalendar gcTo = new GregorianCalendar();
		gcTo.setTime(date);
		setMaxHour(gcTo);
		return gcTo.getTime();
	}	 


	/**
	 * 	Get earliest time of a day (truncate)
	 *  @param time day and time
	 *  @return day with 00:00
	 */
	static public Timestamp getDay (long time)
	{
		if (time == 0)
			time = System.currentTimeMillis();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp (cal.getTimeInMillis());
	}	//	getDay

	/**
	 * 	Get earliest time of a day (truncate)
	 *  @param dayTime day and time
	 *  @return day with 00:00
	 */
	static public Timestamp getDay (Timestamp dayTime)
	{
		if (dayTime == null)
			return getDay(System.currentTimeMillis());
		return getDay(dayTime.getTime());
	}	//	getDay

	/**
	 * 	Get earliest time of a day (truncate)
	 *	@param day day 1..31
	 *	@param month month 1..12
	 *	@param year year (if two diguts: < 50 is 2000; > 50 is 1900)
	 *	@return timestamp ** not too reliable
	 */
	static public Timestamp getDay (int year, int month, int day)
	{
		if (year < 50)
			year += 2000;
		else if (year < 100)
			year += 1900;
		if (month < 1 || month > 12)
			throw new IllegalArgumentException("Invalid Month: " + month);
		if (day < 1 || day > 31)
			throw new IllegalArgumentException("Invalid Day: " + month);
		GregorianCalendar cal = new GregorianCalendar (year, month-1, day);
		return new Timestamp (cal.getTimeInMillis());
	}	//	getDay

	/**
	 * 	Get today (truncate)
	 *  @return day with 00:00
	 */
	static public Calendar getToday ()
	{
		GregorianCalendar cal = new GregorianCalendar();
	//	cal.setTimeInMillis(System.currentTimeMillis());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}	//	getToday

	/**
	 * 	Get earliest time of next day
	 *  @param day day
	 *  @return next day with 00:00
	 */
	static public Timestamp getNextDay (Timestamp day)
	{
		if (day == null)
			day = new Timestamp(System.currentTimeMillis());
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(day.getTime());
		cal.add(Calendar.DAY_OF_YEAR, +1);	//	next
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp (cal.getTimeInMillis());
	}	//	getNextDay

	/**
	 * 	Get last date in month
	 *  @param day day
	 *  @return last day with 00:00
	 */
	static public Timestamp getMonthLastDay (Timestamp day)
	{
		if (day == null)
			day = new Timestamp(System.currentTimeMillis());
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(day.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//
		cal.add(Calendar.MONTH, 1);			//	next
		cal.set(Calendar.DAY_OF_MONTH, 1);	//	first
		cal.add(Calendar.DAY_OF_YEAR, -1);	//	previous
		return new Timestamp (cal.getTimeInMillis());
	}	//	getNextDay

	/**
	 * 	Return the day and time
	 * 	@param day day part
	 * 	@param time time part
	 * 	@return day + time
	 */
	static public Timestamp getDayTime (Timestamp day, Timestamp time)
	{
		GregorianCalendar cal_1 = new GregorianCalendar();
		cal_1.setTimeInMillis(day.getTime());
		GregorianCalendar cal_2 = new GregorianCalendar();
		cal_2.setTimeInMillis(time.getTime());
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(cal_1.get(Calendar.YEAR),
			cal_1.get(Calendar.MONTH),
			cal_1.get(Calendar.DAY_OF_MONTH),
			cal_2.get(Calendar.HOUR_OF_DAY),
			cal_2.get(Calendar.MINUTE),
			cal_2.get(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, 0);
		Timestamp retValue = new Timestamp(cal.getTimeInMillis());
	//	log.fine( "TimeUtil.getDayTime", "Day=" + day + ", Time=" + time + " => " + retValue);
		return retValue;
	}	//	getDayTime

	/**
	 * 	Is the _1 in the Range of _2
	 *  <pre>
	 * 		Time_1         +--x--+
	 * 		Time_2   +a+      +---b---+   +c+
	 * 	</pre>
	 *  The function returns true for b and false for a/b.
	 *  @param start_1 start (1)
	 *  @param end_1 not included end (1)
	 *  @param start_2 start (2)
	 *  @param end_2 not included (2)
	 *  @return true if in range
	 */
	static public boolean inRange (Timestamp start_1, Timestamp end_1, Timestamp start_2, Timestamp end_2)
	{
		//	validity check
		if (end_1.before(start_1))
			throw new UnsupportedOperationException ("TimeUtil.inRange End_1=" + end_1 + " before Start_1=" + start_1);
		if (end_2.before(start_2))
			throw new UnsupportedOperationException ("TimeUtil.inRange End_2=" + end_2 + " before Start_2=" + start_2);
		//	case a
		if (!end_2.after(start_1))		//	end not including
		{
	//		log.fine( "TimeUtil.InRange - No", start_1 + "->" + end_1 + " <??> " + start_2 + "->" + end_2);
			return false;
		}
		//	case c
		if (!start_2.before(end_1))		//	 end not including
		{
	//		log.fine( "TimeUtil.InRange - No", start_1 + "->" + end_1 + " <??> " + start_2 + "->" + end_2);
			return false;
		}
	//	log.fine( "TimeUtil.InRange - Yes", start_1 + "->" + end_1 + " <??> " + start_2 + "->" + end_2);
		return true;
	}	//	inRange

	/**
	 * 	Is start..end on one of the days ?
	 *  @param start start day
	 *  @param end end day (not including)
	 *  @param OnMonday true if OK
	 *  @param OnTuesday true if OK
	 *  @param OnWednesday true if OK
	 *  @param OnThursday true if OK
	 *  @param OnFriday true if OK
	 *  @param OnSaturday true if OK
	 *  @param OnSunday true if OK
	 *  @return true if on one of the days
	 */
	static public boolean inRange (Timestamp start, Timestamp end,
		boolean OnMonday, boolean OnTuesday, boolean OnWednesday,
		boolean OnThursday, boolean OnFriday, boolean OnSaturday, boolean OnSunday)
	{
		//	are there restrictions?
		if (OnSaturday && OnSunday && OnMonday && OnTuesday && OnWednesday && OnThursday && OnFriday)
			return false;

		GregorianCalendar calStart = new GregorianCalendar();
		calStart.setTimeInMillis(start.getTime());
		int dayStart = calStart.get(Calendar.DAY_OF_WEEK);
		//
		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTimeInMillis(end.getTime());
		calEnd.add(Calendar.DAY_OF_YEAR, -1);	//	not including
		int dayEnd = calEnd.get(Calendar.DAY_OF_WEEK);

		//	On same day
		if (calStart.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR)
			&& calStart.get(Calendar.MONTH) == calEnd.get(Calendar.MONTH)
			&& calStart.get(Calendar.DAY_OF_MONTH) == calEnd.get(Calendar.DAY_OF_YEAR))
		{
			if ((!OnSaturday && dayStart == Calendar.SATURDAY)
				|| (!OnSunday && dayStart == Calendar.SUNDAY)
				|| (!OnMonday && dayStart == Calendar.MONDAY)
				|| (!OnTuesday && dayStart == Calendar.TUESDAY)
				|| (!OnWednesday && dayStart == Calendar.WEDNESDAY)
				|| (!OnThursday && dayStart == Calendar.THURSDAY)
				|| (!OnFriday && dayStart == Calendar.FRIDAY))
			{
		//		log.fine( "TimeUtil.InRange - SameDay - Yes", start + "->" + end + " - "
		//			+ OnMonday+"-"+OnTuesday+"-"+OnWednesday+"-"+OnThursday+"-"+OnFriday+"="+OnSaturday+"-"+OnSunday);
				return true;
			}
		//	log.fine( "TimeUtil.InRange - SameDay - No", start + "->" + end + " - "
		//		+ OnMonday+"-"+OnTuesday+"-"+OnWednesday+"-"+OnThursday+"-"+OnFriday+"="+OnSaturday+"-"+OnSunday);
			return false;
		}
		//
	//	log.fine( "TimeUtil.inRange - WeekDay Start=" + dayStart + ", Incl.End=" + dayEnd);

		//	Calendar.SUNDAY=1 ... SATURDAY=7
		BitSet days = new BitSet (8);
		//	Set covered days in BitArray
		if (dayEnd <= dayStart)
			dayEnd += 7;
		for (int i = dayStart; i < dayEnd; i++)
		{
			int index = i;
			if (index > 7)
				index -= 7;
			days.set(index);
	//		System.out.println("Set index=" + index + " i=" + i);
		}

	//	for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++)
	//		System.out.println("Result i=" + i + " - " + days.get(i));

		//	Compare days to availability
		if ((!OnSaturday && days.get(Calendar.SATURDAY))
			|| (!OnSunday && days.get(Calendar.SUNDAY))
			|| (!OnMonday && days.get(Calendar.MONDAY))
			|| (!OnTuesday && days.get(Calendar.TUESDAY))
			|| (!OnWednesday && days.get(Calendar.WEDNESDAY))
			|| (!OnThursday && days.get(Calendar.THURSDAY))
			|| (!OnFriday && days.get(Calendar.FRIDAY)))
		{
	//		log.fine( "MAssignment.InRange - Yes",	start + "->" + end + " - "
	//			+ OnMonday+"-"+OnTuesday+"-"+OnWednesday+"-"+OnThursday+"-"+OnFriday+"="+OnSaturday+"-"+OnSunday);
			return true;
		}

	//	log.fine( "MAssignment.InRange - No", start + "->" + end + " - "
	//		+ OnMonday+"-"+OnTuesday+"-"+OnWednesday+"-"+OnThursday+"-"+OnFriday+"="+OnSaturday+"-"+OnSunday);
		return false;
	}	//	isRange


	/**
	 * 	Is it the same day
	 * 	@param one day
	 * 	@param two compared day
	 * 	@return true if the same day
	 */
	static public boolean isSameDay (Timestamp one, Timestamp two)
	{
		GregorianCalendar calOne = new GregorianCalendar();
		calOne.setTimeInMillis(one.getTime());
		GregorianCalendar calTwo = new GregorianCalendar();
		calTwo.setTimeInMillis(two.getTime());
		if (calOne.get(Calendar.YEAR) == calTwo.get(Calendar.YEAR)
			&& calOne.get(Calendar.MONTH) == calTwo.get(Calendar.MONTH)
			&& calOne.get(Calendar.DAY_OF_MONTH) == calTwo.get(Calendar.DAY_OF_YEAR))
			return true;
		return false;
	}	//	isSameDay
	
	/**
	 * 	Is it the same hour
	 * 	@param one day/time
	 * 	@param two compared day/time
	 * 	@return true if the same day
	 */
	static public boolean isSameHour (Timestamp one, Timestamp two)
	{
		GregorianCalendar calOne = new GregorianCalendar();
		if (one != null)
			calOne.setTimeInMillis(one.getTime());
		GregorianCalendar calTwo = new GregorianCalendar();
		if (two != null)
			calTwo.setTimeInMillis(two.getTime());
		if (calOne.get(Calendar.YEAR) == calTwo.get(Calendar.YEAR)
			&& calOne.get(Calendar.MONTH) == calTwo.get(Calendar.MONTH)
			&& calOne.get(Calendar.DAY_OF_MONTH) == calTwo.get(Calendar.DAY_OF_YEAR)
			&& calOne.get(Calendar.HOUR_OF_DAY) == calTwo.get(Calendar.HOUR_OF_DAY))
			return true;
		return false;
	}	//	isSameHour

	/**
	 * 	Is all day
	 * 	@param start start date
	 * 	@param end end date
	 * 	@return true if all day (00:00-00:00 next day)
	 */
	static public boolean isAllDay (Timestamp start, Timestamp end)
	{
		GregorianCalendar calStart = new GregorianCalendar();
		calStart.setTimeInMillis(start.getTime());
		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTimeInMillis(end.getTime());
		if (calStart.get(Calendar.HOUR_OF_DAY) == calEnd.get(Calendar.HOUR_OF_DAY)
			&& calStart.get(Calendar.MINUTE) == calEnd.get(Calendar.MINUTE)
			&& calStart.get(Calendar.SECOND) == calEnd.get(Calendar.SECOND)
			&& calStart.get(Calendar.MILLISECOND) == calEnd.get(Calendar.MILLISECOND)
			&& calStart.get(Calendar.HOUR_OF_DAY) == 0
			&& calStart.get(Calendar.MINUTE) == 0
			&& calStart.get(Calendar.SECOND) == 0
			&& calStart.get(Calendar.MILLISECOND) == 0
			&& start.before(end))
			return true;
		//
		return false;
	}	//	isAllDay

	/**
	 * 	Calculate the number of days between start and end.
	 * 	@param start start date
	 * 	@param end end date
	 * 	@return number of days (0 = same)
	 */
	static public int getDaysBetween (Timestamp start, Timestamp end)
	{
		boolean negative = false;
		if (end.before(start))
		{
			negative = true;
			Timestamp temp = start;
			start = end;
			end = temp;
		}
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTime(end);
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);

	//	System.out.println("Start=" + start + ", End=" + end + ", dayStart=" + cal.get(Calendar.DAY_OF_YEAR) + ", dayEnd=" + calEnd.get(Calendar.DAY_OF_YEAR));

		//	in same year
		if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR))
		{
			if (negative)
				return (calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR)) * -1;
			return calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
		}

		//	not very efficient, but correct
		int counter = 0;
		while (calEnd.after(cal))
		{
			cal.add (Calendar.DAY_OF_YEAR, 1);
			counter++;
		}
		if (negative)
			return counter * -1;
		return counter;
	}	//	getDatesBetrween

	/**
	 * 	Return Day + offset (truncates)
	 * 	@param day Day
	 * 	@param offset day offset
	 * 	@return Day + offset at 00:00
	 */
	static public Timestamp addDays (Timestamp day, int offset)
	{
		if (day == null)
			day = new Timestamp(System.currentTimeMillis());
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if (offset == 0)
			return new Timestamp (cal.getTimeInMillis());
		cal.add(Calendar.DAY_OF_YEAR, offset);			//	may have a problem with negative (before 1/1)
		return new Timestamp (cal.getTimeInMillis());
	}	//	addDays

	/**
	 * 	Return DateTime + offset in minutes
	 * 	@param dateTime Date and Time
	 * 	@param offset minute offset
	 * 	@return dateTime + offset in minutes
	 */
	static public Timestamp addMinutess (Timestamp dateTime, int offset)
	{
		if (dateTime == null)
			dateTime = new Timestamp(System.currentTimeMillis());
		if (offset == 0)
			return dateTime;
		//
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dateTime);
		cal.add(Calendar.MINUTE, offset);			//	may have a problem with negative
		return new Timestamp (cal.getTimeInMillis());
	}	//	addMinutes


	/**************************************************************************
	 * 	Format Elapsed Time
	 * 	@param start start time or null for now
	 * 	@param end end time or null for now
	 * 	@return formatted time string 1'23:59:59.999
	 */
	public static String formatElapsed (Timestamp start, Timestamp end)
	{
		long startTime = 0;
		if (start == null)
			startTime = System.currentTimeMillis();
		else
			startTime = start.getTime();
		//
		long endTime = 0;
		if (end == null)
			endTime = System.currentTimeMillis();
		else
			endTime = end.getTime();
		return formatElapsed(endTime-startTime);
	}	//	formatElapsed

	/**
	 * 	Format Elapsed Time until now
	 * 	@param start start time
	 *	@return formatted time string 1'23:59:59.999
	 */
	public static String formatElapsed (Timestamp start)
	{
		if (start == null)
			return "NoStartTime";
		long startTime = start.getTime();
		long endTime = System.currentTimeMillis();
		return formatElapsed(endTime-startTime);
	}	//	formatElapsed

	/**
	 * 	Format Elapsed Time
	 *	@param elapsed time in ms
	 *	@return formatted time string 1'23:59:59.999
	 */
	public static String formatElapsed (long elapsed)
	{
		long miliSeconds = elapsed%1000;
		elapsed = elapsed / 1000;
		long seconds = elapsed%60;
		elapsed = elapsed / 60;
		long minutes = elapsed%60;
		elapsed = elapsed / 60;
		long hours = elapsed%24;
		long days = elapsed / 24;
		//
		StringBuffer sb = new StringBuffer();
		if (days != 0)
			sb.append(days).append("'");
		if (hours != 0)
			sb.append(get2digits(hours)).append(":");
		if (minutes != 0)
			sb.append(get2digits(minutes)).append(":");
		sb.append(get2digits(seconds)).append(".").append(miliSeconds);
		return sb.toString();
	}	//	format Elapsed

	/**
	 * 	Get Minimum of 2 digits
	 *	@param no number
	 *	@return String
	 */
	private static String get2digits (long no)
	{
		String s = String.valueOf(no);
		if (s.length() > 1)
			return s;
		return "0" + s;
	}	//	get2digits


	/**
	 * 	Is it valid today?
	 *	@param validFrom valid from
	 *	@param validTo valid to
	 *	@return true if walid
	 */
	public static boolean isValid (Timestamp validFrom, Timestamp validTo)
	{
		return isValid (validFrom, validTo, new Timestamp (System.currentTimeMillis()));
	}	//	isValid

	/**
	 * 	Is it valid on test date
	 *	@param validFrom valid from
	 *	@param validTo valid to
	 *	@param testDate Date
	@return true if walid
	 */
	public static boolean isValid (Timestamp validFrom, Timestamp validTo, Timestamp testDate)
	{
		if (testDate == null)
			return true;
		if (validFrom == null && validTo == null)
			return true;
		//	(validFrom)	ok
		if (validFrom != null && validFrom.after(testDate))
			return false;
		//	ok	(validTo)
		if (validTo != null && validTo.before(testDate))
			return false;
		return true;
	}	//	isValid

	/**
	 * 	Max date
	 *	@param ts1 p1
	 *	@param ts2 p2
	 *	@return max time
	 */
	public static Timestamp max (Timestamp ts1, Timestamp ts2)
	{
		if (ts1 == null)
			return ts2;
		if (ts2 == null)
			return ts1;
		
		if (ts2.after(ts1))
			return ts2;
		return ts1;
	}	//	max
	/** Truncate Day - D			*/
	public static final String	TRUNC_DAY = "D";
	/** Truncate Week - W			*/
	public static final String	TRUNC_WEEK = "W";
	/** Truncate Month - MM			*/
	public static final String	TRUNC_MONTH = "MM";
	/** Truncate Quarter - Q		*/
	public static final String	TRUNC_QUARTER = "Q";
	/** Truncate Year - Y			*/
	public static final String	TRUNC_YEAR = "Y";
	
	/**
	 * 	Get truncated day/time
	 *  @param dayTime day
	 *  @param trunc how to truncate TRUNC_*
	 *  @return next day with 00:00
	 */
	static public Timestamp trunc (Timestamp dayTime, String trunc)
	{
		if (dayTime == null)
			dayTime = new Timestamp(System.currentTimeMillis());
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(dayTime.getTime());
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		//	D
		cal.set(Calendar.HOUR_OF_DAY, 0);
		if (trunc == null || trunc.equals(TRUNC_DAY))
			return new Timestamp (cal.getTimeInMillis());
		//	W
		if (trunc.equals(TRUNC_WEEK))
		{
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			return new Timestamp (cal.getTimeInMillis());
		}
		// MM
		cal.set(Calendar.DAY_OF_MONTH, 1);
		if (trunc.equals(TRUNC_MONTH))
			return new Timestamp (cal.getTimeInMillis());
		//	Q
		if (trunc.equals(TRUNC_QUARTER))
		{
			int mm = cal.get(Calendar.MONTH);
			if (mm < 4)
				mm = 1;
			else if (mm < 7)
				mm = 4;
			else if (mm < 10)
				mm = 7;
			else
				mm = 10;
			cal.set(Calendar.MONTH, mm);
			return new Timestamp (cal.getTimeInMillis());
		}
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return new Timestamp (cal.getTimeInMillis());
	}	//	trunc
	
	static public Timestamp maxTime (Timestamp dayTime) {
		if (dayTime == null)
			dayTime = new Timestamp(System.currentTimeMillis());
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(dayTime.getTime());
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.add(GregorianCalendar.DAY_OF_YEAR, 1);
		cal.add(GregorianCalendar.MILLISECOND, -1);

		return new Timestamp (cal.getTimeInMillis());
	}

	/**
	 * 
	 * @return current time
	 */
	public static Timestamp getNow() {
		return new Timestamp(System.currentTimeMillis());
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
	
	/**
	 * 
	 * @param year
	 * @return list holiday
	 */
	public static List<Date> getHoliday(int year) {
		
		List<Date> holiday = new ArrayList<Date>();
		GregorianCalendar easterSunday = getEasterSunday(year);
		GregorianCalendar mondayEasterSunday = new GregorianCalendar();
		mondayEasterSunday.setTime(easterSunday.getTime());
		mondayEasterSunday.add(GregorianCalendar.DAY_OF_YEAR, 1);
		holiday.add(easterSunday.getTime());
		holiday.add(mondayEasterSunday.getTime());
		holiday.add(new GregorianCalendar(year, 0, 1).getTime());
		holiday.add(new GregorianCalendar(year,0, 6).getTime());
		holiday.add(new GregorianCalendar(year,3, 25).getTime());
		holiday.add(new GregorianCalendar(year,4, 1).getTime()); // 1 maggio
		holiday.add(new GregorianCalendar(year,5, 2).getTime()); // 2 giugno
		holiday.add(new GregorianCalendar(year,7, 15).getTime());
		holiday.add(new GregorianCalendar(year,10, 1).getTime());
		holiday.add(new GregorianCalendar(year,11, 8).getTime());
		holiday.add(new GregorianCalendar(year,11, 25).getTime()); // Natale
		holiday.add(new GregorianCalendar(year,11, 26).getTime()); // S.Stefano
		
		return holiday;
	}
	
	/**
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return delta week
	 */
	public static int getDeltaWeek(Date dateFrom, Date dateTo) {

		long diff = dateTo.getTime() - firstDateOfWeek(dateFrom).getTime();
		int weeks = new BigDecimal(diff).divide(new BigDecimal(MILLISECONDS_PER_WEEK), 0, RoundingMode.HALF_UP).intValue();	// / MILLISECONDS_PER_WEEK);
		if (diff < 0)
			weeks--;
		return weeks;
	}
	
	/**
	 * 
	 * @param dateFrom
	 * @return first date of week
	 */
	public static Date firstDateOfWeek(Date dateFrom) {

		GregorianCalendar start = new GregorianCalendar();
		start.setTime(dateFrom);
		start.set(GregorianCalendar.DAY_OF_WEEK, start.getFirstDayOfWeek());
		start.set(GregorianCalendar.HOUR_OF_DAY, 0);
		start.set(GregorianCalendar.MINUTE, 0);
		start.set(GregorianCalendar.SECOND, 0);
		start.set(GregorianCalendar.MILLISECOND, 0);

		return start.getTime();
	}
	
	/**
	 * 
	 * @param date
	 * @return ISO week (yyyyWww)
	 */
	public String getISOWeek(Date date) {

		int yw = getISOWeekNo(date);
		DecimalFormat df = new DecimalFormat("00");
		return "" + yw / 100 + "W" + df.format(yw % 100);
	}
	
	/**
	 * 
	 * @param date
	 * @return ISO week (yyyyww)
	 */
	public int getISOWeekNo(Date date) {
		GregorianCalendar dat1 = new GregorianCalendar(Locale.ITALY);
		dat1.setTime(date);
		int w = dat1.get(GregorianCalendar.WEEK_OF_YEAR);
		int y = dat1.get(GregorianCalendar.YEAR);
		int m = dat1.get(GregorianCalendar.MONTH);

		y = ((m == 0 && w > 10) 
			? y - 1 
			: ((m == 11 && w < 3) 
				? y + 1 
				: y));

		return y * 100 + w;
	}
	
	/**
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return delta month
	 */
	public static int getDeltaMonth(Date dateFrom, Date dateTo) {
		
		GregorianCalendar min = new GregorianCalendar();
		GregorianCalendar max = new GregorianCalendar();
 
		min.setTime(dateFrom.before(dateTo) ? dateFrom : dateTo);
		max.setTime(dateTo.after(dateFrom) ? dateTo : dateFrom);
	   
		min.set(GregorianCalendar.DAY_OF_MONTH, 1);
		int ymMax = max.get(GregorianCalendar.YEAR) * 100 +
					max.get(GregorianCalendar.MONTH);
		
		int ymMin = min.get(GregorianCalendar.YEAR) * 100 +
					min.get(GregorianCalendar.MONTH);
		int month = 0;
		while (ymMin < ymMax) {
			month++;
			min.add(GregorianCalendar.MONTH, 1);
			ymMin = min.get(GregorianCalendar.YEAR) * 100 +
					min.get(GregorianCalendar.MONTH);
		}
		return (dateFrom.before(dateTo) ? month : -month);
	}

	public static int getDeltaDay(Date dateFrom, Date dateTo) {
		
		GregorianCalendar min = new GregorianCalendar();
		GregorianCalendar max = new GregorianCalendar();
		min.setTime(dateFrom.before(dateTo) ? dateFrom : dateTo);
		max.setTime(dateTo.after(dateFrom) ? dateTo : dateFrom);
		
		min.set(GregorianCalendar.HOUR_OF_DAY, 0);
		min.set(GregorianCalendar.MINUTE, 0);
		min.set(GregorianCalendar.SECOND, 0);
		min.set(GregorianCalendar.MILLISECOND, 0);
		
		max.set(GregorianCalendar.HOUR_OF_DAY, 23);
		max.set(GregorianCalendar.MINUTE, 59);
		max.set(GregorianCalendar.SECOND, 59);
		max.set(GregorianCalendar.MILLISECOND, 999);
	   
		long days = (max.getTimeInMillis() - min.getTimeInMillis()) / MILLISECONDS_PER_DAY;
		
		return (int)(dateFrom.before(dateTo) ? days : -days);
	}
	
	/**
	 * @param dateFrom
	 * @param dateTo
	 * @param type D=Day M=Month W=Week (W is default)
	 * @return type delta time
	 */
	public static int getDeltaTime(Date dateFrom, Date dateTo, String type) {
		
		int retValue = 0;
		if ("D".equals(type) ) 
			retValue = getDeltaDay(dateFrom, dateTo);
		else if ("M".equals(type) )
			retValue = getDeltaMonth(dateFrom, dateTo);
		else 
			retValue = getDeltaWeek(dateFrom, dateTo);	//default
		
		return retValue;
	}

	/**
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @return il range di date con l'orario minimo per dateFrom e orario max per dateTo
	 */
	public static DateFromTo getDateRange(Date dateFrom, Date dateTo) {
		return new DateFromTo(dateFrom, dateTo);
	}
	
}
