package it.mesis.avis.model;

//import java.sql.Timestamp;
import it.mesis.utility.TimeUtil;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import org.hibernate.envers.Audited;
//import org.hibernate.envers.NotAudited;

//@Audited
@Entity
@Table(name = "AGENDA")
public class Agenda {
	
	@EmbeddedId
	private AgendaKey id;

//	@NotAudited
	@ManyToOne(optional = true)
    @JoinColumn(name="CODINTERNODONAT")
    private Donatore donatore;
	
	@Column(name = "NOTAPREN")
	private String notapren;
	
	
	public AgendaKey getId() {
		return id;
	}
	public void setId(AgendaKey id) {
		this.id = id;
	}
	
	public Donatore getDonatore() {
		return donatore;
	}
	public void setDonatore(Donatore donatore) {
		this.donatore = donatore;
	}

	public String getNotapren() {
		return notapren;
	}
	public void setNotapren(String notapren) {
		this.notapren = notapren;
	}
	
	/**
	 * L'agenda non è aggiornabile (o cancellabile) se l'ora di prenotazione è nel passato
	 * L'agenda è aggiornabile (o cancellabile) se l'ora corrente è prima delle 7 del mattino 
	 * 	e l'ora di prenotazione è nel futuro.
	 * L'agenda non è aggiornabile (o cancellabile) se l'ora corrente è dopo delle 7 del mattino e la
	 * data di prenotazione è nella giornata corrente in un ora qualsiasi
	 * 
	 * @return true se modificabile
	 */
	public boolean isUpdateable() {
		return getId() == null ? true : isUpdateable(getId().getDataorapren());
	}
	
	/**
	 * 
	 * @param preno
	 * @return true se una prenotazione fatta nella data passata può essere modificabile
	 */
	public static boolean isUpdateable(Date preno) {
		if (preno == null)
			return true;
		
		GregorianCalendar gc = new GregorianCalendar();

		if (preno.compareTo(gc.getTime()) <= 0)
			return false;	//it is in the past
		
		int hh = gc.get(GregorianCalendar.HOUR_OF_DAY);
		if (hh >= 7) {
			TimeUtil.setMaxHour(gc);	//fine giornata odierna (24hh - 1 MILLISECOND)
		}

		return preno.compareTo(gc.getTime()) > 0;
	}
}
