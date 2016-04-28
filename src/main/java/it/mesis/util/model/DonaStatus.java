package it.mesis.util.model;

import it.mesis.avis.enu.MembershipState;
import it.mesis.avis.model.Agenda;
import it.mesis.avis.service.UserServiceImpl;
import it.mesis.utility.TimeUtil;
import it.mesis.utility.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DonaStatus {
	
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

	public DonaStatus(String codinternodonat, boolean idoneo,
			MembershipState membershipState, String cognomeenome,
			String luogonascita, java.sql.Timestamp datadinascita,
			String provdinascita, String codicefiscale,
			String sesso) {
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
	
	/**
	 * 
	 * @return true se il donatore ha tutte le caratteristiche per prenotare
	 */
	public boolean prenoWeb() {
		return prenoWeb(Utility.parseInteger(UserServiceImpl.GG_RANGE_PRENO));
	}
	
	/**
	 * 
	 * @param dayBefore giorni precedenti la prossima donazione prima dei quali non è possibile prenotare (troppo precedente)
	 * @return true se il donatore ha tutte le caratteristiche per prenotare
	 */
	public boolean prenoWeb(int dayBefore) {
		return idoneo && MembershipState.DONATORE.equals(membershipState)
				&& isMinPrenoOk(dayBefore) && isLtTwoYears()
				&& !getListTipoDona().isEmpty()
				;
	}
	
	
	
	public boolean isMinPrenoOk(int dayBefore) {
		return dayDue() - dayBefore <= 0;
	}
	
	public boolean isLtTwoYears() {
		Date last = getLast();
		if (last == null)
			return false;
		
		int gg = TimeUtil.getDeltaDay(last, new Date());
		
		return gg < 365*2;
	}
	
//	/**
//	 * 
//	 * @return data minima di prenotazione
//	 */
//	public Date getMinPreno(int dayBefore) {
//		Date minPreno = getMinNext(); 	//data prossima donazione
//		if (minPreno != null) {
//			GregorianCalendar gc = new GregorianCalendar();
//			gc.setTime(minPreno);
//			gc.add(GregorianCalendar.DAY_OF_YEAR, -dayBefore);
//			minPreno = gc.getTime();
//			return minPreno;
//		}
//		else
//			return minPreno;
//	}
	
	public String getMsg(int dayBefore) {
		
		StringBuffer sb = new StringBuffer();
		
		if (!idoneo)
			sb.append("Non idoneo");
		
		if (!MembershipState.DONATORE.equals(membershipState))
			sb.append(" Non è un Donatore");
		
		if (!isMinPrenoOk(dayBefore))
			sb.append(" Troppo presto per prenotare (non prima di 30 gg)");
		
		if (!isLtTwoYears())
			sb.append(" Non dona da più di due anni");
		
		if (getListTipoDona().isEmpty())
			sb.append(" Non ha nessun tipo donazione assegnato");
		
		return sb.toString().trim();
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
	 * @return ultima data di nonazione
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		StringBuffer sb = new StringBuffer();
		if (cognomeenome != null && !cognomeenome.isEmpty())
			sb.append(cognomeenome);
		if (luogonascita != null && !luogonascita.isEmpty())
			sb.append(" nat" + getOA() + " a " + luogonascita);
		if (provdinascita != null && !provdinascita.isEmpty())
			sb.append("(" + provdinascita + ") ");
		if (datadinascita != null)
			sb.append(" il " + sdf.format(datadinascita) + " ");
		if (codicefiscale != null && !codicefiscale.isEmpty())
			sb.append(" c.f. " + codicefiscale);
		return sb.toString().trim();
	}
	
	private String getOA() {
		return "F".equalsIgnoreCase(sesso) ? "a" : "o";
	}

}
