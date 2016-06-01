package it.mesis.util.model;

import it.mesis.avis.enu.MembershipState;
import it.mesis.avis.model.Agenda;
import it.mesis.utility.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class DonaStatus {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private String codinternodonat;
	private boolean idoneo;
	private Set<DonaStatusType> status;
	private MembershipState membershipState;
	private Agenda agenda;
	
	private String cognomeenome;
	private String luogonascita;
	private java.sql.Timestamp datadinascita;
	private String provdinascita;
	private String codicefiscale;
	private String sesso;
//	private String msg;
	private int dayBefore;
	private Date lowLimit;
	private Date upLimit;

	public DonaStatus(String codinternodonat, boolean idoneo,
			MembershipState membershipState, String cognomeenome,
			String luogonascita, java.sql.Timestamp datadinascita,
			String provdinascita, String codicefiscale,
			String sesso, int dayBefore) {
		super();
		this.codinternodonat = codinternodonat;
		this.idoneo = idoneo;
		this.membershipState = membershipState;
		this.cognomeenome = cognomeenome;
		this.luogonascita = luogonascita;
		this.datadinascita = datadinascita;
		this.provdinascita = provdinascita;
		this.codicefiscale = codicefiscale;
		this.sesso = sesso;
		status = new HashSet<DonaStatusType>();
		this.dayBefore = dayBefore;
		
		setBookablePeriod();
	}
	
	public String getCodinternodonat() {
		return codinternodonat;
	}
	public void setCodinternodonat(String codinternodonat) {
		this.codinternodonat = codinternodonat;
	}

	public boolean isIdoneo() {
		return idoneo;
	}

	public void setIdoneo(boolean idoneo) {
		this.idoneo = idoneo;
	}

	public Set<DonaStatusType> getStatus() {
		return status;
	}
	
	public void addStatus(DonaStatusType donaStatusType) {
		status.add(donaStatusType);
	}
	
	public MembershipState getMembershipState() {
		return membershipState;
	}
	
	public String getCognomeenome() {
		return cognomeenome;
	}

	public String getLuogonascita() {
		return luogonascita;
	}

	public java.sql.Timestamp getDatadinascita() {
		return datadinascita;
	}

	public String getProvdinascita() {
		return provdinascita;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}
	
	public String getSesso() {
		return sesso;
	}
	
//	public String getMsg() {
//		return msg;
//	}

//	public void setMsg(String msg) {
//		this.msg = msg;
//	}

	/**
	 * 
	 * @return lista dei tipi donazioni attivi (non sospesi) del donatore
	 */
	public List<Integer> getListTipoDona() {
		
		List<Integer> retValue = new ArrayList<Integer>();
		for (DonaStatusType donaStatusType : status) {
			if (!donaStatusType.isSuspended())
				retValue.add(donaStatusType.getType().getIndex());
		}
		return retValue;
	}
	
	@Override
	public String toString() {
		return "DonaStatus [idoneo=" + idoneo
				+ ", stato assoc.=" + membershipState 
				+ ", stato=" + status
				+ "]";
	}
	
//	/**
//	 * 
//	 * @return true se il donatore ha tutte le caratteristiche per prenotare
//	 */
//	public boolean prenoWeb() {
//		return prenoWeb(Utility.parseInteger(UserServiceImpl.GG_RANGE_PRENO));
//	}
	
	/**
	 * 
	 * @return true se il donatore ha tutte le caratteristiche per prenotare
	 */
	public boolean prenoWeb() {
		return idoneo 
				&& MembershipState.DONATORE.equals(membershipState)
				&& isMinPrenoOk() 
				&& isLtTwoYears()
				&& !getListTipoDona().isEmpty()
				;
	}
	
	public boolean isMinPrenoOk() {
		return dayDue() - dayBefore <= 0;
	}
	
	public boolean isLtTwoYears() {
		Date last = getLast();
		if (last == null)
			return false;
		
		int gg = TimeUtil.getDeltaDay(last, new Date());
		
		return gg < 365*2;
	}
	
	public String getMsg() {
		return StringUtils.join(getReasons(), ", ");
	}
	
	private List<String> getReasons() {
		
		List<String> reasons = new LinkedList<String>();
		
		if (!idoneo)
			reasons.add("Non idoneo");
		
		if (!MembershipState.DONATORE.equals(membershipState))
			reasons.add("Non è un Donatore");
		
		if (!isMinPrenoOk())
			reasons.add("Troppo presto per prenotare (non prima di 30 gg) prossima donazione tra " + dayDue() + " giorni");
		
		if (!isLtTwoYears())
			reasons.add("Non dona da più di due anni");
		
		if (getListTipoDona().isEmpty())
			reasons.add("Non ha nessun tipo donazione assegnato");

		return reasons;
	}
	
	/**
	 * 
	 * @return la data minima tra le prossime date possibili di donazione
	 */
	public Date getMinNext() {
		Date retValue = null;
		for (DonaStatusType donaStatusType : status) {
			if (donaStatusType.getNext() != null && (retValue == null || donaStatusType.getNext().compareTo(retValue) < 1))
				retValue = donaStatusType.getNext();
		}
		return retValue;
	}
	
	/**
	 * 
	 * @return ultima data di donazione
	 */
	public Date getLast() {
		Date retValue = null;
		for (DonaStatusType donaStatusType : status) {
			if (donaStatusType.getLast() != null && (retValue == null || donaStatusType.getLast().compareTo(retValue) > 0))
				retValue = donaStatusType.getLast();
		}
		return retValue;
	}
	
	/**
	 * 
	 * @return nr di giorni rimasti per la prossima donazione
	 */
	public int dayDue() {
		Date minNext = getMinNext();
		return minNext == null || minNext.compareTo(new Date()) <= 0
			? 0
			: TimeUtil.getDeltaDay(new Date(), minNext);
	}
	
	/**
	 * 
	 * @param bookingDate
	 * @return true se la data passata è interna al periodo valido di prenotazione
	 */
	public boolean isBookable(Date bookingDate) {
		return bookingDate.compareTo(lowLimit) >= 0 &&  bookingDate.compareTo(upLimit) <= 0;
	}
	
	private void setBookablePeriod() {
		GregorianCalendar gc = new GregorianCalendar();
		
		if (dayDue() <= dayBefore) {
			lowLimit = gc.getTime();	//limite inferiore per accettazione prenotazione
			gc.add(GregorianCalendar.DAY_OF_WEEK, dayBefore);
			upLimit = gc.getTime();		//limite superiore per accettazione prenotazione
		} else {
			upLimit = getMinNext();		//limite superiore per accettazione prenotazione
			gc.setTime(upLimit);
			gc.add(GregorianCalendar.DAY_OF_YEAR, -dayBefore);
			lowLimit = gc.getTime();	//limite inferiore per accettazione prenotazione
		}
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public Agenda getAgenda() {
		return agenda;
	}
	
	public boolean hasAgenda() {
		return agenda != null;
	}
	
	public String getRefDonatore() {
		
		StringBuffer sb = new StringBuffer();
		if (cognomeenome != null && !cognomeenome.isEmpty())
			sb.append(cognomeenome);
		if (luogonascita != null && !luogonascita.isEmpty())
			sb.append(" nat" + getOA() + " a " + getRefLuogoNascita());
		if (datadinascita != null)
			sb.append(" il " + sdf.format(datadinascita) + " ");
		if (codicefiscale != null && !codicefiscale.isEmpty())
			sb.append(" c.f. " + codicefiscale);
		
		return sb.toString().trim();
	}
	
	public String getRefLuogoNascita() {
		StringBuffer sb = new StringBuffer();
		if (luogonascita != null && !luogonascita.isEmpty())
			sb.append(luogonascita);
		if (provdinascita != null && !provdinascita.isEmpty())
			sb.append("(" + provdinascita + ") ");
		
		return sb.toString();
	}
	
	private String getOA() {
		return "F".equalsIgnoreCase(sesso) ? "a" : "o";
	}

}
