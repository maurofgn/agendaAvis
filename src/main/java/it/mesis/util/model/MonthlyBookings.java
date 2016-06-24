package it.mesis.util.model;

import it.mesis.avis.bean.jpa.AgendaEntity;
import it.mesis.avis.bean.jpa.AgendaEntityKey;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * prenotazioni mensili
 *
 */
public class MonthlyBookings {
	
	private static final int MAX_WEEK = 6;	//nr settimane max di un mese (0..5)
	private static final int MAX_DAY = 7;	//nr giorni max di una settimana (0..6) 

	private YearMonth yearMonth;
	private TipoDonaPuntoPrel tipoDonazPuntoPrel;
	private int lastDay;
	private YearMonthDay dayPreno;			//giorno di prenotazione
	
//	private boolean updateable;
//	private boolean donor;
//	private AgendaKey agendaKey;
	
	private DonaStatus donaStatus;
	
	private Booking[][] bookingsWeek;
	
	public MonthlyBookings(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, List<AgendaEntity> listAgenda, DonaStatus donaStatus) {
		super();
		this.yearMonth = yearMonth;
		this.tipoDonazPuntoPrel = tipoDonazPuntoPrel;
		
//		this.updateable = updateable;
//		this.agendaKey = agendaKey;
//		this.donor = donor;
		
		this.donaStatus = donaStatus;
		
		loadList(listAgenda);
	}
	
	private void loadList(List<AgendaEntity> listAgenda) {
		
		
		AgendaEntityKey agendaKey = getAgendaKey();
		
		dayPreno = agendaKey != null
			? new YearMonthDay(agendaKey.getDataorapren()) 
			: null;	//giorno in cui c'è una prenotazione attiva
			
		boolean sameTipoDonaPuntoPrel = agendaKey == null 
				? true 
				: tipoDonazPuntoPrel.isSameTipoDonaPuntoPrel(agendaKey.getMacchina());
		
		bookingsWeek = new Booking[MAX_WEEK][MAX_DAY];	//sei settimane x 7 giorni
		
		for (AgendaEntity agenda : listAgenda) {
			
			YearMonthDay day = new YearMonthDay(agenda.getId().getDataorapren());
			
			if (lastDay < day.getDay())
				lastDay = day.getDay();
			
			Booking booking = bookingsWeek[day.getWeekNr()][day.getWeekDay()];
			if (booking == null) {
				
				/**
				 * Il giorno è aggiornabile se:
				 * E' un donatore
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
				boolean upd = donaStatus != null && 
//						updateable && 
						sameTipoDonaPuntoPrel &&
						(dayPreno == null || (dayPreno.equals(day))) &&
						AgendaEntity.isUpdateable(agenda.getId().getDataorapren()) &&
						donaStatus.isBookable(agenda.getId().getDataorapren())		//verifica che la data dell'agenda sia compresa all'interno del range accettabile
						;
				
				booking = new Booking(day, upd, donaStatus != null);
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
				b.setMyPreno(sameTipoDonaPuntoPrel && dayPreno.equals(day) ? getAgendaKey() : null);
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
	
	public AgendaEntityKey getAgendaKey() {
		return donaStatus != null && donaStatus.getAgenda() != null ? donaStatus.getAgenda().getId() : null;
	}

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public TipoDonaPuntoPrel getTipoDonaPuntoPrel() {
		return  tipoDonazPuntoPrel;
	}

	/**
	 * 
	 * @return prenotazioni del mese
	 */
	public Booking[][] getBookingsWeek() {
		return bookingsWeek;
	}

	/**
	 * 
	 * @param week
	 * @return prenotazioni della settimana
	 */
  	public Booking[] getWeekDays(int week) {
		return week >= 0 && week < MAX_WEEK ? bookingsWeek[week] : null;
	}

  	/**
  	 * 
  	 * @param week nr settimana
  	 * @param weekDay giorno della settimana
  	 * @return premotazioni del giorno
  	 */
	public Booking getBooking(int week, int weekDay) {
		
		if (week < 0 || week >= MAX_WEEK || weekDay < 0 || weekDay >= MAX_DAY)
			return null;	//fuori range
		
		return bookingsWeek[week][weekDay] != null ? bookingsWeek[week][weekDay] : new Booking(YearMonthDay.VOID, false, true);
	}
	
	/**
	 * 
	 * @return true se esiste una prenotazione e questa non è nel mese corrente
	 */
	public boolean getPrenoNotInMonth() {
		return dayPreno != null && !dayPreno.getYearMonth().equals(yearMonth);
	}
}
