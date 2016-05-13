package it.mesis.util.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import it.mesis.avis.enu.PrenoState;
import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;

/**
 * 
 * disponibilitÓ e prenotazioni di un singolo giorno 
 *
 */
public class Booking {
	
	private List<Agenda> agendaList;
	private int busy;
	private boolean updateable;
	private boolean donor;
	private YearMonthDay day;
	private AgendaKey agendaKey;
	
	private static final SimpleDateFormat HH_MM = new SimpleDateFormat("HH:mm");
	
//	public Booking(YearMonthDay day, boolean updateable) {
//		this(day, updateable, true);
//	}
	
	public Booking(YearMonthDay day, boolean updateable, boolean donor) {
		super();
		this.day = day;
		this.updateable = updateable;
		this.donor = donor;
		this.agendaList = new ArrayList<Agenda>();
	}

	public void add(Agenda agenda) {
		agendaList.add(agenda);
		if (agenda.getDonatore() != null)
			busy++;
	}

	public List<Agenda> getAgendaList() {
		return agendaList;
	}

	public int getBusy() {
		return busy;
	}
	
	public int getTotal() {
		return agendaList.size();
	}
	
	public int getFree() {
		return agendaList.size() - busy;
	}
	
	
	public String getBusyFreeMyHour() {
		if (donor)
			return getFreeOrMyHour();	//nr posti disponibili o l'ora se prenotata
		else 
			return getBusyTotal();		//nr posti occupati / totale
	}
	
	/**
	 * 
	 * @return nr posti disponibili o l'ora se prenotata
	 */
	public String getFreeOrMyHour() {
		return agendaKey != null ? HH_MM.format(agendaKey.getDataorapren()) : (agendaList.size() > 0 ? String.valueOf(agendaList.size() - busy ) : "");
	}
	
	/**
	 * 
	 * @return nr posti occupati / totale
	 */
	public String getBusyTotal() {
		return agendaList.size() > 0 ? String.valueOf(busy) + "/" + String.valueOf(agendaList.size()) : "";
	}

	
	public boolean isUpdateable() {
		return updateable;
	}
	
	public void setUpdateable(boolean updateable) {
		this.updateable = updateable;
	}
	
	public boolean isDonor() {
		return donor;
	}

	public void setDonor(boolean donor) {
		this.donor = donor;
	}

	public YearMonthDay getDay() {
		return day;
	}
	
	public boolean isValid() {
		return day.isValid();
	}
	
	
//	updateable
	
	public PrenoState getState() {
		
		if (!isValid() || getTotal() == 0)
			return PrenoState.INDISPONIBILE;
		
		if (donor) {
			
			if (agendaKey != null) 
				return PrenoState.MIA_PRENO;
			
			if (updateable && getFree() > 0)
				return PrenoState.LIBERO;
			
			return !updateable && getFree() > 0 ? PrenoState.LIBERO_NON_PRENO : PrenoState.OCCUPATO;
		} else {
			return busy > 0 ? PrenoState.LIBERO : PrenoState.LIBERO_NON_PRENO;
		}
	}
	
	public String getFunctionJS() {

		if (!isValid() || getTotal() == 0)
			return "";
		
		if (!donor) {
			return "onclick=\"donors(" + getDay().getDay() + ");\"";
		}
		
		if (updateable) {
			if (agendaKey != null)
				return "onclick=\"disdetta();\"";
			else if (getFree() > 0)
				return "onclick=\"prenota(" + getDay().getDay() + ");\"";
		}

		return "";
	}
	
	@Override
	public String toString() {
		return "" + getTotal();
	}

	public void setMyPreno(AgendaKey agendaKey) {
		this.agendaKey = agendaKey;
	}

	public AgendaKey getAgendaKey() {
		return agendaKey;
	}
	
	
	
}
