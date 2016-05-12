package it.mesis.util.model;

import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;

import java.util.GregorianCalendar;
import java.util.List;

public class MonthlyBookings {
	
	private static final int MAX_WEEK = 6;	//nr settimane max di un mese (0..5)
	private static final int MAX_DAY = 7;	//nr giorni max di una settimana (0..6) 

	private YearMonth yearMonth;
	private TipoDonaPuntoPrel tipoDonazPuntoPrel;
	private int lastDay;
	private boolean updateable;
	private boolean donor;
	private AgendaKey agendaKey;
	private Booking[][] bookingsWeek;
	
	public MonthlyBookings(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, List<Agenda> listAgenda, boolean updateable, AgendaKey agendaKey, boolean donor) {
		super();
		this.yearMonth = yearMonth;
		this.tipoDonazPuntoPrel = tipoDonazPuntoPrel;
		this.updateable = updateable;
		this.agendaKey = agendaKey;
		this.donor = donor;
		loadList(listAgenda);
	}
	
	private void loadList(List<Agenda> listAgenda) {
		
		YearMonthDay dayPreno = agendaKey != null
			? new YearMonthDay(agendaKey.getDataorapren()) 
			: null;	//giorno in cui c'è una prenotazione attiva
			
		boolean sameTipoDonaPuntoPrel = agendaKey == null 
				? true 
				: tipoDonazPuntoPrel.isSameTipoDonaPuntoPrel(agendaKey.getMacchina());
		
		bookingsWeek = new Booking[MAX_WEEK][MAX_DAY];	//sei settimane x 7 giorni
		
		for (Agenda agenda : listAgenda) {
			
			YearMonthDay day = new YearMonthDay(agenda.getId().getDataorapren());
			
			if (lastDay < day.getDay())
				lastDay = day.getDay();
			
			Booking booking = bookingsWeek[day.getWeekNr()][day.getWeekDay()];
			if (booking == null) {
				
				/**
				 * Il giorno è aggiornabile se:
				 * E' un donatore
				 * and
				 * L' agenda è aggiornabile
				 * and
				 * (
				 * 	non esiste una precedente prenotazione
				 * 	or
				 * 	esiste una precedente prenotazione ed il giorno dell'agenda è uguale a quello della prenotazione per lo stesso tipo di prelievo e lo stesso tipo di donazione
				 * )
				 * and
				 * il giorno corrente è aggiornabile
				 * 
				 */
				
				
				boolean upd = donor && 
						updateable && 
						sameTipoDonaPuntoPrel &&
						(dayPreno == null || (dayPreno.equals(day))) &&
						Agenda.isUpdateable(agenda.getId().getDataorapren())
						;
				
				booking = new Booking(day, upd, donor);
				bookingsWeek[day.getWeekNr()][day.getWeekDay()] = booking;
			}
			booking.add(agenda);
		}
		
		buildVoid(dayPreno, sameTipoDonaPuntoPrel);
	}

	/**
	 * per i giorni del mese, crea un booking vuoto se per quel giorno non c'è niente dal DB,
	 * per quelli caricati non permette la modifica se non c'è disponibilità, a meno dell'unico giorno in cui è presente la
	 * prenotazione del donatore che sta operando
	 */
	private void buildVoid(YearMonthDay dayPreno, boolean sameTipoDonaPuntoPrel) {
		
		GregorianCalendar gc = new GregorianCalendar(yearMonth.getYear(), yearMonth.getMonth(), 1);
		for (int d = 1; d <= 31; d++) {
			
			YearMonthDay day = new YearMonthDay(gc.getTime());
			
			if (bookingsWeek[day.getWeekNr()][day.getWeekDay()] == null) {
				bookingsWeek[day.getWeekNr()][day.getWeekDay()] = new Booking(day, false, true);
			} else if (dayPreno != null) {
				Booking b = bookingsWeek[day.getWeekNr()][day.getWeekDay()];
				b.setMyPreno(sameTipoDonaPuntoPrel && dayPreno.equals(day) ? agendaKey : null);
			} else if (dayPreno == null) {
				Booking b = bookingsWeek[day.getWeekNr()][day.getWeekDay()];
				if (b.isValid() && b.isUpdateable()) {
					b.setUpdateable(b.getFree() > 0);
				}
			}
			
			gc.add(GregorianCalendar.DAY_OF_YEAR, 1);
			if (gc.get(GregorianCalendar.MONTH) != yearMonth.getMonth())
				break;
		}
	}
	
	public AgendaKey getAgendaKey() {
		return agendaKey;
	}

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public TipoDonaPuntoPrel getTipoDonaPuntoPrel() {
		return  tipoDonazPuntoPrel;
	}

	public Booking[][] getBookingsWeek() {
		return bookingsWeek;
	}
	
  	public Booking[] getWeekDays(int week) {
		return week >= 0 && week < MAX_WEEK ? bookingsWeek[week] : null;
	}

	public Booking getBooking(int week, int weekDay) {
		
		if (week < 0 || week >= MAX_WEEK || weekDay < 0 || weekDay >= MAX_DAY)
			return null;	//fuori range
		
		Booking retValue = bookingsWeek[week][weekDay];
		if (retValue != null)
			return retValue;
		
		return new Booking(YearMonthDay.VOID, false, true);
		
	}
}
